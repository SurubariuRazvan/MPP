package domain;

import java.sql.Timestamp;
import java.util.Objects;

public class Trip implements Entity<Integer> {
    private Integer id;
    private int destinationID;
    private Timestamp departure;
    private int freeSeats;

    public Trip(Integer id, Integer destinationID, Timestamp departure, int freeSeats) {
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
