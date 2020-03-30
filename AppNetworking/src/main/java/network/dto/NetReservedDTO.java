package network.dto;

import java.io.Serializable;

public class NetReservedDTO implements Serializable {
    int tripID;
    String clientName;
    int seatNumber;

    public NetReservedDTO(int tripID, String clientName, int seatNumber) {
        this.tripID = tripID;
        this.clientName = clientName;
        this.seatNumber = seatNumber;
    }

    public int getTripID() {
        return tripID;
    }

    public void setTripID(int tripID) {
        this.tripID = tripID;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }
}
