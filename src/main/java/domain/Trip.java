package domain;

import java.util.Date;
import java.util.Objects;

public class Trip extends Entity<Integer> {
    Integer destinationID;
    Date departure;
    Integer freeSeats;

    public Trip(Integer id, Integer destinationID, Date departure, Integer freeSeats) {
        super(id);
        this.destinationID = destinationID;
        this.departure = departure;
        this.freeSeats = freeSeats;
    }

    public Integer getDestinationID() {
        return destinationID;
    }

    public void setDestinationID(Integer destinationID) {
        this.destinationID = destinationID;
    }

    public Date getDeparture() {
        return departure;
    }

    public void setDeparture(Date departure) {
        this.departure = departure;
    }

    public Integer getFreeSeats() {
        return freeSeats;
    }

    public void setFreeSeats(Integer freeSeats) {
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
