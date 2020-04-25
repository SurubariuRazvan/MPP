package server;

import app.BookedTripDTO;
import app.TripDTO;
import app.*;
import domain.*;
import io.grpc.stub.StreamObserver;
import repository.*;
import services.AppServiceException;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.LinkedHashMap;


public class AppServicesImpl extends AppServiceGrpc.AppServiceImplBase {

    private final BookedTripRepository bookedTripRepo;
    private final ClientRepository clientRepo;
    private final DestinationRepository destinationRepo;
    private final TripRepository tripRepo;
    private static final LinkedHashMap<Integer, StreamObserver<ReserveResponse>> observers = new LinkedHashMap<>();
    private final UserRepository userRepo;

    private java.sql.Timestamp googleToSql(com.google.protobuf.Timestamp time) {
        return java.sql.Timestamp.from(Instant.ofEpochSecond(time.getSeconds()));
    }

    private com.google.protobuf.Timestamp sqlToGoogle(java.sql.Timestamp time) {
        return com.google.protobuf.Timestamp.newBuilder().setSeconds(time.getTime() / 1000L).build();
    }

    public AppServicesImpl(UserRepository userRepo, BookedTripRepository bookedTripRepo, ClientRepository clientRepo, DestinationRepository destinationRepo, TripRepository tripRepo) {
        this.userRepo = userRepo;
        this.bookedTripRepo = bookedTripRepo;
        this.clientRepo = clientRepo;
        this.destinationRepo = destinationRepo;
        this.tripRepo = tripRepo;
    }

    @Override
    public void search(SearchRequest request, StreamObserver<SearchResponse> responseObserver) {
        var builder = SearchResponse.newBuilder();
        for (var a : bookedTripRepo.search(request.getDestinationName(), googleToSql(request.getDeparture())))
            builder.addList(BookedTripDTO.newBuilder().setClientID(a.getClientID()).setClientName(a.getClientName()).setSeatNumber(a.getSeatNumber()).build());
        responseObserver.onNext(builder.build());
    }

    private void notifyUsers(String destinationName, Timestamp departure, Integer seatNumber, String clientName) {
        for (var observer : observers.values())
            observer.onNext(ReserveResponse.newBuilder()
                    .setType(ResponseType.Data)
                    .setDestinationName(destinationName)
                    .setDeparture(sqlToGoogle(departure))
                    .setSeatNumber(seatNumber)
                    .setClientName(clientName)
                    .build());
    }

    @Override
    public StreamObserver<ReserveRequest> reserve(StreamObserver<ReserveResponse> responseObserver) {
        return new StreamObserver<>() {
            @Override
            public void onNext(ReserveRequest value) {
                if (value.getType().equals(Type.StartConnection) && !observers.containsKey(value.getUserID()))
                    observers.put(value.getUserID(), responseObserver);
                else if (value.getType().equals(Type.EndConnection) && observers.containsKey(value.getUserID()))
                    observers.remove(value.getUserID());
                else {
                    Trip trip = tripRepo.findOne(value.getTripID());
                    if (trip == null) {
                        responseObserver.onNext(ReserveResponse.newBuilder().setType(ResponseType.Error).setErrorMessage("No trip with the given id").build());
                        return;
                    }

                    BookedTrip bookedTrip = bookedTripRepo.findOne(new BookedTripID(value.getTripID(), value.getSeatNumber()));
                    if (bookedTrip != null) {
                        responseObserver.onNext(ReserveResponse.newBuilder().setType(ResponseType.Error).setErrorMessage("Seat is already booked for the specified trip").build());
                        return;
                    }
                    Client client = clientRepo.findByName(value.getClientName());
                    if (client == null) {
                        clientRepo.save(new Client(null, value.getClientName()));
                        client = clientRepo.findByName(value.getClientName());
                    }

                    Destination destination = destinationRepo.findOne(trip.getDestinationID());

                    bookedTripRepo.save(new BookedTrip(new BookedTripID(value.getTripID(), value.getSeatNumber()), client.getId()));
                    trip.setFreeSeats(trip.getFreeSeats() - 1);
                    tripRepo.update(trip);
                    notifyUsers(destination.getName(), trip.getDeparture(), value.getSeatNumber(), value.getClientName());
                }
            }

            @Override
            public void onError(Throwable t) {
                t.printStackTrace();
                responseObserver.onError(t);
            }

            @Override
            public void onCompleted() {
                responseObserver.onCompleted();
            }
        };
    }

    @Override
    public void showTrips(ShowTripsRequest request, StreamObserver<ShowTripsResponse> responseObserver) {
        var builder = ShowTripsResponse.newBuilder();
        for (var a : tripRepo.getAllTrips())
            builder.addList(TripDTO.newBuilder()
                    .setDestinationName(a.getDestinationName())
                    .setDeparture(sqlToGoogle(a.getDeparture()))
                    .setFreeSeats(a.getFreeSeats())
                    .build());
        responseObserver.onNext(builder.build());
    }

    @Override
    public void getTripIDByDestinationAndDeparture(GetTripIDByDestinationAndDepartureRequest request, StreamObserver<GetTripIDByDestinationAndDepartureResponse> responseObserver) {
        Integer tripID = tripRepo.getTripIDByDestinationAndDeparture(request.getDestination(), googleToSql(request.getDeparture()));
        responseObserver.onNext(GetTripIDByDestinationAndDepartureResponse.newBuilder().setTripID(tripID).build());
    }

    @Override
    public void login(LoginRequest request, StreamObserver<LoginResponse> responseObserver) {
        User user = userRepo.findByUsername(request.getUsername());
        if (user == null) {
            responseObserver.onError(new AppServiceException("Wrong credentials"));
            return;
        }
        if (observers.containsKey(user.getId())) {
            responseObserver.onError(new AppServiceException("User already logged in"));
            return;
        }
        if (User.hash(request.getPassword()).equals(user.getPasswordHash())) {
            //client sends a reserve stream to log in with StartConnection type
            responseObserver.onNext(LoginResponse.newBuilder().setId(user.getId()).setUsername(user.getUsername()).setPasswordHash(user.getPasswordHash()).build());
            return;
        }
        responseObserver.onError(new AppServiceException("Wrong credentials"));
    }

    @Override
    public void logout(LogoutRequest request, StreamObserver<LogoutResponse> responseObserver) {
        if (observers.containsKey(request.getUserID()))
            //client sends a reserve stream to log out with EndConnection type
            responseObserver.onNext(LogoutResponse.newBuilder().setUserID(request.getUserID()).build());
        else
            responseObserver.onError(new AppServiceException("User isn't logged in"));
    }
}