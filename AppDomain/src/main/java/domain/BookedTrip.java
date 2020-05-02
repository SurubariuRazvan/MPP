package domain;

public class BookedTrip implements Entity<BookedTripID> {
    private BookedTripID id;

    private int ClientID;

    public BookedTrip(int tripID, int seatNumber, int clientID) {
        this.id = new BookedTripID(tripID, seatNumber);
        this.ClientID = clientID;
    }

    public BookedTrip(BookedTripID bookedTripID, int clientID) {
        this.id = bookedTripID;
        this.ClientID = clientID;
    }

    @Override
    public BookedTripID getId() {
        return this.id;
    }

    @Override
    public void setId(BookedTripID bookedTripID) {
        this.id = bookedTripID;
    }

    public int getTripID() {
        return this.id.getTripID();
    }

    public void setTripID(int tripID) {
        this.id.setTripID(tripID);
    }

    public int getSeatNumber() {
        return this.id.getSeatNumber();
    }

    public void setSeatNumber(int seatNumber) {
        this.id.setSeatNumber(seatNumber);
    }

    public int getClientID() {
        return ClientID;
    }

    public void setClientID(int clientID) {
        ClientID = clientID;
    }
}

