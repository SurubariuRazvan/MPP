package com.jderu;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;


@javax.persistence.Entity
@Table(name = "Trip", uniqueConstraints = {@UniqueConstraint(name = "Unique(\"destinationID\", \"departure\")", columnNames = {"destinationID", "departure"})})
public class Trip implements com.jderu.Entity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Column(name = "destinationID")
    private int destinationID;
    @Column(name = "departure")
    private Timestamp departure;
    @Column(name = "free_seats")
    private int freeSeats;

    public Trip() {
    }

    public Trip(Integer id, int destinationID, Timestamp departure, int freeSeats) {
        this.id = id;
        this.destinationID = destinationID;
        this.departure = departure;
        this.freeSeats = freeSeats;
    }

    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDestinationID() {
        return destinationID;
    }

    public void setDestinationID(Integer destinationID) {
        this.destinationID = destinationID;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trip trip = (Trip) o;
        return Objects.equals(destinationID, trip.destinationID) &&
                Objects.equals(departure, trip.departure);
    }

    @Override
    public int hashCode() {
        return Objects.hash(destinationID, departure);
    }
}
