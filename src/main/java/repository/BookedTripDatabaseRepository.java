package repository;

import domain.BookedTrip;
import domain.BookedTripDTO;
import domain.BookedTripID;
import validation.CRUDValidator;
import validation.ValidationException;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BookedTripDatabaseRepository extends AbstractDatabaseRepository<BookedTripID, BookedTrip> {
    public BookedTripDatabaseRepository(CRUDValidator<BookedTrip> validator, Connection c) {
        super(validator, c, BookedTrip.class);
    }

    @Override
    protected BookedTrip readEntity(ResultSet result) throws SQLException {
        int tripID = result.getInt("tripID");
        int seatNumber = result.getInt("seatNumber");
        int clientID = result.getInt("clientID");
        return new BookedTrip(tripID, seatNumber, clientID);
    }

    @Override
    protected String findOneString(BookedTripID id) {
        return "SELECT * from \"BookedTrip\" where \"tripID\" = " + id.getTripID() + " and \"seatNumber\" = " + id.getSeatNumber() + ";";
    }

    @Override
    protected String findAllString() {
        return "SELECT * from \"BookedTrip\";";
    }

    @Override
    protected String insertString(BookedTrip entity) {
        return "INSERT INTO \"BookedTrip\" (\"tripID\", \"seatNumber\", \"clientID\") " +
                "VALUES (" + entity.getTripID()
                + "," + entity.getSeatNumber()
                + "," + entity.getClientID()
                + ");";
    }

    @Override
    protected String deleteString(BookedTripID id) {
        return "DELETE from \"BookedTrip\" where \"tripID\" = " + id.getTripID() + " and \"seatNumber\" = " + id.getSeatNumber() + ";";
    }

    @Override
    protected String updateString(BookedTrip entity) {
        return "UPDATE \"BookedTrip\" SET"
                + " \"clientID\"= " + entity.getClientID()
                + " where \"tripID\" = " + entity.getTripID() + " and \"seatNumber\" = " + entity.getSeatNumber() + ";";
    }

    public List<BookedTripDTO> search(String destinationName, LocalDateTime departure) {
        List<BookedTripDTO> entities = new ArrayList<>();
        try {
            Statement stmt = c.createStatement();
            var f = stmt.executeQuery(findByDestinationAndDeparture(destinationName, departure));
            while(f.next())
                entities.add(readDTO(f));
            f.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entities;
    }

    private BookedTripDTO readDTO(ResultSet result) throws SQLException {
        int clientID = result.getInt("id");
        String clientName = result.getString("name");
        int seatNumber = result.getInt("seatNumber");
        return new BookedTripDTO(clientID, clientName, seatNumber);
    }

    private String findByDestinationAndDeparture(String destinationName, LocalDateTime departure) {
        return "SELECT \"Client\".\"id\", \"Client\".\"name\", \"BookedTrip\".\"seatNumber\" "
                + "from \"BookedTrip\" inner join \"Client\" on \"BookedTrip\".\"clientID\" = \"Client\".\"id\" "
                + "inner join \"Trip\" on \"BookedTrip\".\"tripID\" = \"Trip\".\"id\" "
                + "inner join \"Destination\" on \"Trip\".\"destinationID\"=\"Destination\".\"id\" "
                + "where \"Destination\".\"name\" = '" + destinationName + "' and \"Trip\".\"departure\" = '" + departure + "';";
    }
}
