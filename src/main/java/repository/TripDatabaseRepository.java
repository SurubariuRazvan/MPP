package repository;

import domain.Trip;
import domain.TripDTO;
import validation.CRUDValidator;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TripDatabaseRepository extends AbstractDatabaseRepository<Integer, Trip> {
    public TripDatabaseRepository(CRUDValidator<Trip> validator, Connection c) {
        super(validator, c, Trip.class);
    }

    @Override
    protected Trip readEntity(ResultSet result) throws SQLException {
        int id = result.getInt("id");
        int destinationID = result.getInt("destinationID");
        Date departure = result.getDate("departure");
        int freeSeats = result.getInt("freeSeats");
        return new Trip(id, destinationID, departure, freeSeats);
    }

    @Override
    protected String findOneString(Integer id) {
        return "SELECT * from \"Trip\" where ID = " + id + ";";
    }

    @Override
    protected String findAllString() {
        return "SELECT * from \"Trip\";";
    }

    @Override
    protected String insertString(Trip entity) {
        return "INSERT INTO \"Trip\" (\"destinationID\", \"departure\", \"freeSeats\") " +
                "VALUES (" + entity.getDestinationID()
                + "," + entity.getDeparture()
                + "," + entity.getFreeSeats()
                + ");";
    }

    @Override
    protected String deleteString(Integer id) {
        return "DELETE from \"Trip\" where ID = " + id + ";";
    }

    @Override
    protected String updateString(Trip entity) {
        return "UPDATE \"Trip\" SET"
                + " \"destinationID\"= " + entity.getDestinationID()
                + ", \"departure\"= '" + entity.getDeparture()
                + "', \"freeSeats\"= " + entity.getFreeSeats()
                + " where ID = " + entity.getId() + ";";
    }

    public List<TripDTO> getAllTrips() {
        List<TripDTO> entities = new ArrayList<>();
        try {
            Statement stmt = c.createStatement();
            var f = stmt.executeQuery(getAllTripsString());
            while(f.next())
                entities.add(readDTO(f));
            f.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entities;
    }

    private String getAllTripsString() {
        return "SELECT \"name\", \"departure\", \"freeSeats\"" +
                "FROM \"Trip\" inner join \"Destination\" on \"Trip\".\"id\"=\"Destination\".\"id\"";
    }

    private TripDTO readDTO(ResultSet result) throws SQLException {
        String destinationName = result.getString("name");
        LocalDateTime departure = result.getTimestamp("departure").toLocalDateTime();
        int freeSeats = result.getInt("freeSeats");
        return new TripDTO(destinationName, departure, freeSeats);
    }
}
