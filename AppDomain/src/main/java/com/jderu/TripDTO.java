package com.jderu;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class TripDTO implements Serializable {
    private Integer id;
    private String destinationName;
    private Timestamp departure;
    private Integer freeSeats;

    public TripDTO() {
    }

    public TripDTO(String destinationName, Timestamp departure, Integer freeSeats) {
        this.destinationName = destinationName;
        this.departure = departure;
        this.freeSeats = freeSeats;
    }

    public TripDTO(Integer id, String destinationName, Timestamp departure, Integer freeSeats) {
        this.id = id;
        this.destinationName = destinationName;
        this.departure = departure;
        this.freeSeats = freeSeats;
    }

    public TripDTO(String destinationName, Date departure, Integer freeSeats) {
        this.destinationName = destinationName;
        this.departure = new Timestamp(departure.getTime());
        this.freeSeats = freeSeats;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setFreeSeats(Integer freeSeats) {
        this.freeSeats = freeSeats;
    }

    public String getDestinationName() {
        return destinationName;
    }

    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }

    public Timestamp getDeparture() {
        return departure;
    }

    public void setDeparture(Timestamp departure) {
        this.departure = departure;
    }

    public int getFreeSeats() {
        return freeSeats;
    }

    public void setFreeSeats(int freeSeats) {
        this.freeSeats = freeSeats;
    }

    @Override
    public String toString() {
        return "Trip{" +
                "destinationName='" + destinationName + '\'' +
                ", departure=" + departure +
                ", freeSeats=" + freeSeats +
                '}';
    }
}
