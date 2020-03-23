package repository;

import domain.Trip;
import domain.TripDTO;
import validation.CRUDValidator;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class TripDatabaseRepository extends AbstractDatabaseRepository<Integer, Trip> {
    public TripDatabaseRepository(CRUDValidator<Trip> validator, Properties props) {
        super(validator, props, Trip.class);
    }

    @Override
    protected Trip readEntity(ResultSet result) throws SQLException {
        int id = result.getInt("id");
        int destinationID = result.getInt("destinationID");
        Timestamp departure = result.getTimestamp("departure");
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
                + ",'" + entity.getDeparture()
                + "'," + entity.getFreeSeats()
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
            Statement stmt = dbUtils.getConnection().createStatement();
            ResultSet f = stmt.executeQuery(getAllTripsString());
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
                "FROM \"Trip\" inner join \"Destination\" on \"Trip\".\"destinationID\"=\"Destination\".\"id\"";
    }

    private TripDTO readDTO(ResultSet result) throws SQLException {
        String destinationName = result.getString("name");
        Timestamp departure = result.getTimestamp("departure");
        int freeSeats = result.getInt("freeSeats");
        return new TripDTO(destinationName, departure, freeSeats);
    }

    public Integer getTripIDByDestinationAndDeparture(String destinationName, Timestamp departure) {
        try {
            Statement stmt = dbUtils.getConnection().createStatement();
            ResultSet f = stmt.executeQuery(getTripByDestinationAndDeparture(destinationName, departure));
            if (f.next())
                return f.getInt("id");
            f.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getTripByDestinationAndDeparture(String destinationName, Timestamp departure) {
        return "SELECT \"Trip\".\"id\" as \"id\""
                + "from \"Trip\" inner join \"Destination\" on \"Trip\".\"destinationID\"=\"Destination\".\"id\" "
                + "where \"Destination\".\"name\" = '" + destinationName + "' and \"Trip\".\"departure\" = '" + departure + "';";
    }
}
