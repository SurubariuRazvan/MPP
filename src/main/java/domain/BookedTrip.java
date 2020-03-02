package domain;

public class BookedTrip extends Entity<BookedTrip.BookedTripID> {
    int takenSeats;

    public BookedTrip(int clientID, int tripID, int takenSeats) {
        super(new BookedTripID(clientID, tripID));
        this.takenSeats = takenSeats;
    }

    public int getClientID() {
        return super.getId().getClientID();
    }

    public void setClientID(int clientID) {
        super.getId().setClientID(clientID);
    }

    public int getTripID() {
        return super.getId().getTripID();
    }

    public void setTripID(int tripID) {
        super.getId().setTripID(tripID);
    }

    static class BookedTripID {
        int clientID;
        int tripID;

        public BookedTripID(int clientID, int tripID) {
            this.clientID = clientID;
            this.tripID = tripID;
        }

        public int getClientID() {
            return clientID;
        }

        public void setClientID(int clientID) {
            this.clientID = clientID;
        }

        public int getTripID() {
            return tripID;
        }

        public void setTripID(int tripID) {
            this.tripID = tripID;
        }
    }
}