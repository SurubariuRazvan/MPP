package com.jderu.services;


import java.sql.Timestamp;

public interface IAppObserver {
    void updateWindows(String destinationName, Timestamp departure, int seatNumber, String clientName);
}
