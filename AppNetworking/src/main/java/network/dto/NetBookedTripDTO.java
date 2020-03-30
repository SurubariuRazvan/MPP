package network.dto;

import java.io.Serializable;


public class NetBookedTripDTO implements Serializable {
    Integer tripID;
    Integer seatNumber;

    public NetBookedTripDTO(Integer tripID, Integer seatNumber) {
        this.tripID = tripID;
        this.seatNumber = seatNumber;
    }

    public Integer getTripID() {
        return tripID;
    }

    public void setTripID(Integer tripID) {
        this.tripID = tripID;
    }

    public Integer getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(Integer seatNumber) {
        this.seatNumber = seatNumber;
    }
}
