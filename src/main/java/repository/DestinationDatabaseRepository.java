package repository;

import domain.Destination;
import validation.CRUDValidator;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DestinationDatabaseRepository extends AbstractDatabaseRepository<Integer, Destination> {
    public DestinationDatabaseRepository(CRUDValidator<Destination> validator, Connection c) {
        super(validator, c, Destination.class);
    }

    @Override
    protected Destination readEntity(ResultSet result) throws SQLException {
        int id = result.getInt("id");
        String name = result.getString("name");
        return new Destination(id, name);
    }

    @Override
    protected String findOneString(Integer id) {
        return "SELECT * from \"Destination\" where ID = " + id + ";";
    }

    @Override
    protected String findAllString() {
        return "SELECT * from \"Destination\";";
    }

    @Override
    protected String insertString(Destination entity) {
        return "INSERT INTO \"Destination\" (name) " +
                "VALUES ('" + entity.getName()
                + "');";
    }

    @Override
    protected String deleteString(Integer id) {
        return "DELETE from \"Destination\" where ID = " + id + ";";
    }

    @Override
    protected String updateString(Destination entity) {
        return "UPDATE \"Destination\" SET "
                + "name= '" + entity.getName()
                + "' where ID = " + entity.getId() + ";";
    }
}
