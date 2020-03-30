package domain;

import java.io.Serializable;

public class BookedTripDTO implements Serializable {
    private int clientID;
    private String clientName;
    private int seatNumber;

    public BookedTripDTO(int clientID, String clientName, int seatNumber) {
        this.clientID = clientID;
        this.clientName = clientName;
        this.seatNumber = seatNumber;
    }

    public int getClientID() {
        return clientID;
    }

    public String getClientName() {
        return clientName;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    @Override
    public String toString() {
        return "BookedTripDTO{" +
                "clientName='" + clientName + '\'' +
                ", seatNumber=" + seatNumber +
                '}';
    }
}
