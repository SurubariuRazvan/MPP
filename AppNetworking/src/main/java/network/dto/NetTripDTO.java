package network.dto;

import java.io.Serializable;
import java.sql.Timestamp;


public class NetTripDTO implements Serializable {
    private String destinationName;
    private Timestamp departure;

    public NetTripDTO(String destinationName, Timestamp departure) {
        this.destinationName = destinationName;
        this.departure = departure;
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
}
