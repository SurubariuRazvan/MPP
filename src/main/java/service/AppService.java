package service;

import domain.*;
import repository.BookedTripDatabaseRepository;
import repository.ClientDatabaseRepository;
import repository.DestinationDatabaseRepository;
import repository.TripDatabaseRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public class AppService {
    private BookedTripDatabaseRepository bookedTripRepo;
    private ClientDatabaseRepository clientRepo;
    private DestinationDatabaseRepository destinationRepo;
    private TripDatabaseRepository tripRepo;

    public AppService(BookedTripDatabaseRepository bookedTripRepo, ClientDatabaseRepository clientRepo, DestinationDatabaseRepository destinationRepo, TripDatabaseRepository tripRepo) {
        this.bookedTripRepo = bookedTripRepo;
        this.clientRepo = clientRepo;
        this.destinationRepo = destinationRepo;
        this.tripRepo = tripRepo;
    }


    public List<BookedTripDTO> search(String destinationName, Timestamp departure) {
        return bookedTripRepo.search(destinationName, departure);
    }

    public void reserve(int tripID, String clientName, int seatNumber) throws AppServiceException {
        Trip trip = tripRepo.findOne(tripID);
        if (trip == null)
            throw new AppServiceException("No trip with the given id");

        BookedTrip bookedTrip = bookedTripRepo.findOne(new BookedTripID(tripID, seatNumber));
        if (bookedTrip != null)
            throw new AppServiceException("Seat is already booked for the specified trip");

        Client client = clientRepo.findByName(clientName);
        if (client == null) {
            clientRepo.save(new Client(null, clientName));
            client = clientRepo.findByName(clientName);
        }

        bookedTripRepo.save(new BookedTrip(new BookedTripID(tripID, seatNumber), client.getId()));
        trip.setFreeSeats(trip.getFreeSeats() - 1);
        tripRepo.update(trip);
    }

    public List<TripDTO> showTrips() {
        return tripRepo.getAllTrips();
    }

    public void addTripDTO() {
        tripRepo.save(new Trip(20, 2, Timestamp.valueOf(LocalDateTime.now()), 12));
    }

    public Integer getTripIDByDestinationAndDeparture(String destination, Timestamp departure) {
        return tripRepo.getTripIDByDestinationAndDeparture(destination, departure);
    }
}
