package domain;

public class BookedTripDTO{
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

    @Override
    public String toString() {
        return "BookedTripDTO{" +
                "clientName='" + clientName + '\'' +
                ", seatNumber=" + seatNumber +
                '}';
    }
}
