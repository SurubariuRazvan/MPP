package repository.database;

import domain.Destination;
import repository.DestinationRepository;
import validation.CRUDValidator;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseDestinationRepository extends AbstractDatabaseRepository<Integer, Destination> implements DestinationRepository {
    public DatabaseDestinationRepository(CRUDValidator<Destination> validator, Properties props) {
        super(validator, props, Destination.class);
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
