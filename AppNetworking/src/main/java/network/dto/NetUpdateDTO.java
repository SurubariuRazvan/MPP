package network.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class NetUpdateDTO implements Serializable {
    String destinationName;
    Timestamp departure;
    int seatNumber;
    String clientName;

    public NetUpdateDTO(String destinationName, Timestamp departure, int seatNumber, String clientName) {
        this.destinationName = destinationName;
        this.departure = departure;
        this.seatNumber = seatNumber;
        this.clientName = clientName;
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

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }
}
