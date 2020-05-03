package com.jderu.services;


import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.Timestamp;

public interface IAppObserver extends Remote {
    void updateWindows(String destinationName, Timestamp departure, int seatNumber, String clientName) throws RemoteException;
}
