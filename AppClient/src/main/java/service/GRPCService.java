package service;

import app.*;
import controller.LoginController;
import domain.BookedTripDTO;
import domain.TripDTO;
import domain.User;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import services.AppServiceException;
import services.IAppServices;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class GRPCService implements IAppServices {

    private final LoginController loginController;
    private final AppServiceGrpc.AppServiceStub service;
    private StreamObserver<ReserveRequest> observer;

    public GRPCService(String ip, Integer port, LoginController loginController) {
        this.loginController = loginController;
        ManagedChannel channel = ManagedChannelBuilder.forAddress(ip, port).usePlaintext().build();
        service = AppServiceGrpc.newStub(channel);
        initObservers();
    }

    private java.sql.Timestamp googleToSql(com.google.protobuf.Timestamp time) {
        return java.sql.Timestamp.from(Instant.ofEpochSecond(time.getSeconds()));
    }

    private com.google.protobuf.Timestamp sqlToGoogle(java.sql.Timestamp time) {
        return com.google.protobuf.Timestamp.newBuilder().setSeconds(time.getTime() / 1000L).build();
    }

    @Override
    public List<BookedTripDTO> search(String destinationName, Timestamp departure) throws AppServiceException {
        final CountDownLatch latch = new CountDownLatch(1);
        final Throwable[] ex = {new Throwable()};
        List<BookedTripDTO> list = new LinkedList<>();
        service.search(SearchRequest.newBuilder().setDestinationName(destinationName).setDeparture(sqlToGoogle(departure)).build(), new StreamObserver<>() {
            @Override
            public void onNext(SearchResponse value) {
                for (var a : value.getListList())
                    list.add(new BookedTripDTO(a.getClientID(), a.getClientName(), a.getSeatNumber()));
                latch.countDown();
            }

            @Override
            public void onError(Throwable t) {
                ex[0] = t;
                latch.countDown();
            }

            @Override
            public void onCompleted() {
                latch.countDown();
            }
        });

        try {
            latch.await();
        } catch (InterruptedException interruptedException) {
            AppServiceException appEx = new AppServiceException();
            appEx.setStackTrace(interruptedException.getStackTrace());
            throw appEx;
        }

        if (ex[0].getMessage() == null)
            return list;
        else {
            AppServiceException appEx = new AppServiceException();
            appEx.setStackTrace(ex[0].getStackTrace());
            throw appEx;
        }
    }

    @Override
    public void reserve(int tripID, String clientName, int seatNumber) throws AppServiceException {
        observer.onNext(ReserveRequest.newBuilder()
                .setType(Type.RequestData)
                .setTripID(tripID)
                .setClientName(clientName)
                .setSeatNumber(seatNumber)
                .build());
    }

    private void initObservers() {
        observer = service.reserve(new StreamObserver<>() {
            @Override
            public void onNext(ReserveResponse value) {
                if (value.getType().equals(ResponseType.Data))
                    loginController.updateWindows(value.getDestinationName(), googleToSql(value.getDeparture()), value.getSeatNumber(), value.getClientName());
                else if (value.getType().equals(ResponseType.Error))
                    loginController.appController.controller.showError("Reserving Error", value.getErrorMessage());
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onCompleted() {

            }
        });
    }

    @Override
    public List<TripDTO> showTrips() throws AppServiceException {
        final CountDownLatch latch = new CountDownLatch(1);
        final Throwable[] ex = {new Throwable()};
        List<TripDTO> list = new LinkedList<>();
        service.showTrips(ShowTripsRequest.newBuilder().build(), new StreamObserver<>() {
            @Override
            public void onNext(ShowTripsResponse value) {
                for (var a : value.getListList())
                    list.add(new TripDTO(a.getDestinationName(), googleToSql(a.getDeparture()), a.getFreeSeats()));
                latch.countDown();
            }

            @Override
            public void onError(Throwable t) {
                ex[0] = t;
                latch.countDown();
            }

            @Override
            public void onCompleted() {
                latch.countDown();
            }
        });

        try {
            latch.await();
        } catch (InterruptedException interruptedException) {
            AppServiceException appEx = new AppServiceException();
            appEx.setStackTrace(interruptedException.getStackTrace());
            throw appEx;
        }

        if (ex[0].getMessage() == null)
            return list;
        else {
            AppServiceException appEx = new AppServiceException();
            appEx.setStackTrace(ex[0].getStackTrace());
            throw appEx;
        }
    }

    @Override
    public Integer getTripIDByDestinationAndDeparture(String destination, Timestamp departure) throws AppServiceException {
        final CountDownLatch latch = new CountDownLatch(1);
        final Throwable[] ex = {new Throwable()};
        final Integer[] tripID = new Integer[1];
        service.getTripIDByDestinationAndDeparture(GetTripIDByDestinationAndDepartureRequest.newBuilder().setDestination(destination).setDeparture(sqlToGoogle(departure)).build(), new StreamObserver<>() {
            @Override
            public void onNext(GetTripIDByDestinationAndDepartureResponse value) {
                tripID[0] = value.getTripID();
                latch.countDown();
            }

            @Override
            public void onError(Throwable t) {
                ex[0] = t;
                latch.countDown();
            }

            @Override
            public void onCompleted() {
                latch.countDown();
            }
        });

        try {
            latch.await();
        } catch (InterruptedException interruptedException) {
            AppServiceException appEx = new AppServiceException();
            appEx.setStackTrace(interruptedException.getStackTrace());
            throw appEx;
        }

        if (ex[0].getMessage() == null)
            return tripID[0];
        else {
            AppServiceException appEx = new AppServiceException();
            appEx.setStackTrace(ex[0].getStackTrace());
            throw appEx;
        }
    }

    @Override
    public User login(String username, String password) throws AppServiceException {
        final CountDownLatch latch = new CountDownLatch(1);
        final Throwable[] ex = {new Throwable()};
        User user = new User(null, null, null);
        service.login(LoginRequest.newBuilder().setUsername(username).setPassword(password).build(), new StreamObserver<>() {
            @Override
            public void onNext(LoginResponse value) {
                user.setId(value.getId());
                user.setUsername(value.getUsername());
                user.setPasswordHash(value.getPasswordHash());
                latch.countDown();
            }

            @Override
            public void onError(Throwable t) {
                ex[0] = t;
                latch.countDown();
            }

            @Override
            public void onCompleted() {
                latch.countDown();
            }
        });

        try {
            latch.await();
        } catch (InterruptedException interruptedException) {
            AppServiceException appEx = new AppServiceException();
            appEx.setStackTrace(interruptedException.getStackTrace());
            throw appEx;
        }

        if (ex[0].getMessage() == null) {
            observer.onNext(ReserveRequest.newBuilder()
                    .setType(Type.StartConnection)
                    .setUserID(user.getId())
                    .build());
            return user;
        } else
            return null;
    }

    @Override
    public void logout(Integer userID) throws AppServiceException {
        final CountDownLatch latch = new CountDownLatch(1);
        final Throwable[] ex = {new Throwable()};
        final Integer[] uID = new Integer[1];
        service.logout(LogoutRequest.newBuilder().setUserID(userID).build(), new StreamObserver<>() {
            @Override
            public void onNext(LogoutResponse value) {
                uID[0] = value.getUserID();
                latch.countDown();
            }

            @Override
            public void onError(Throwable t) {
                ex[0] = t;
                latch.countDown();
            }

            @Override
            public void onCompleted() {
                latch.countDown();
            }
        });

        try {
            latch.await();
        } catch (InterruptedException interruptedException) {
            AppServiceException appEx = new AppServiceException();
            appEx.setStackTrace(interruptedException.getStackTrace());
            throw appEx;
        }

        if (ex[0].getMessage() == null)
            observer.onNext(ReserveRequest.newBuilder()
                    .setType(Type.EndConnection)
                    .setUserID(uID[0])
                    .build());
        else {
            AppServiceException appEx = new AppServiceException();
            appEx.setStackTrace(ex[0].getStackTrace());
            throw appEx;
        }
    }
}
