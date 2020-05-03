package com.jderu;

import javax.persistence.*;

@javax.persistence.Entity
@Table(name = "Booked_trip")
public class BookedTrip implements Entity<BookedTripID> {

    @Id
    @AttributeOverrides({
            @AttributeOverride(name = "tripID", column = @Column(name = "tripID")),
            @AttributeOverride(name = "seatNumber", column = @Column(name = "seat_number"))
    })
    private BookedTripID id;

    @Column(name = "clientID")
    private int clientID;

    public BookedTrip() {
    }

    public BookedTrip(int tripID, int seatNumber, int clientID) {
        this.id = new BookedTripID(tripID, seatNumber);
        this.clientID = clientID;
    }

    public BookedTrip(BookedTripID bookedTripID, int clientID) {
        this.id = bookedTripID;
        this.clientID = clientID;
    }

    @Override
    public BookedTripID getId() {
        return this.id;
    }

    @Override
    public void setId(BookedTripID bookedTripID) {
        this.id = bookedTripID;
    }

    public int getTripID() {
        return this.id.getTripID();
    }

    public void setTripID(int tripID) {
        this.id.setTripID(tripID);
    }

    public int getSeatNumber() {
        return this.id.getSeatNumber();
    }

    public void setSeatNumber(int seatNumber) {
        this.id.setSeatNumber(seatNumber);
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }
}

