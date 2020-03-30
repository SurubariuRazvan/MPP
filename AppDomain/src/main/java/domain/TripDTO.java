package domain;

import java.io.Serializable;
import java.sql.Timestamp;

public class TripDTO implements Serializable {
    String destinationName;
    Timestamp departure;
    int freeSeats;

    public TripDTO(String destinationName, Timestamp departure, int freeSeats) {
        this.destinationName = destinationName;
        this.departure = departure;
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
