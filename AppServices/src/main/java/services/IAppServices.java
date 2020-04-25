package services;

import domain.BookedTripDTO;
import domain.TripDTO;
import domain.User;

import java.sql.Timestamp;
import java.util.List;

public interface IAppServices {
    List<BookedTripDTO> search(String destinationName, Timestamp departure) throws AppServiceException;

    void reserve(int tripID, String clientName, int seatNumber) throws AppServiceException;

    List<TripDTO> showTrips() throws AppServiceException;

    Integer getTripIDByDestinationAndDeparture(String destination, Timestamp departure) throws AppServiceException;

    User login(String username, String password) throws AppServiceException;

    void logout(Integer userID) throws AppServiceException;
}
