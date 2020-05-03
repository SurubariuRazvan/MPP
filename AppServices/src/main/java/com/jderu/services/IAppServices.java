package com.jderu.services;

import com.jderu.BookedTripDTO;
import com.jderu.TripDTO;
import com.jderu.User;

import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.util.List;

public interface IAppServices {
    List<BookedTripDTO> search(String destinationName, Timestamp departure) throws AppServiceException;

    void reserve(int tripID, String clientName, int seatNumber) throws AppServiceException;

    List<TripDTO> showTrips() throws AppServiceException, RemoteException;

    Integer getTripIDByDestinationAndDeparture(String destination, Timestamp departure) throws AppServiceException;

    User login(String username, String password, IAppObserver client) throws AppServiceException;

    void logout(Integer userID) throws AppServiceException;
}
