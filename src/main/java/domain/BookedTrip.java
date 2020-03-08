package domain;

public class BookedTrip extends Entity<BookedTripID> {
    int ClientID;

    public BookedTrip(int tripID, int seatNumber, int clientID) {
        super(new BookedTripID(tripID, seatNumber));
        this.ClientID = clientID;
    }

    public BookedTrip(BookedTripID bookedTripID, int clientID) {
        super(bookedTripID);
        this.ClientID = clientID;
    }

    public BookedTripID getBookedTripID() {
        return super.getId();
    }

    public void setBookedTripID(BookedTripID bookedTripID) {
        super.setId(bookedTripID);
    }

    public int getTripID() {
        return super.getId().getTripID();
    }

    public void setTripID(int tripID) {
        super.getId().setTripID(tripID);
    }

    public int getSeatNumber() {
        return super.getId().getSeatNumber();
    }

    public void setSeatNumber(int seatNumber) {
        super.getId().setSeatNumber(seatNumber);
    }

    public int getClientID() {
        return ClientID;
    }

    public void setClientID(int clientID) {
        ClientID = clientID;
    }
}

