package repository;

import domain.Client;
import validation.CRUDValidator;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class ClientDatabaseRepository extends AbstractDatabaseRepository<Integer, Client> {
    public ClientDatabaseRepository(CRUDValidator<Client> validator, Properties props) {
        super(validator, props, Client.class);
    }

    @Override
    protected Client readEntity(ResultSet result) throws SQLException {
        int id = result.getInt("id");
        String name = result.getString("name");
        return new Client(id, name);
    }

    @Override
    protected String findOneString(Integer id) {
        return "SELECT * from \"Client\" where ID = " + id + ";";
    }

    @Override
    protected String findAllString() {
        return "SELECT * from \"Client\";";
    }

    @Override
    protected String insertString(Client entity) {
        return "INSERT INTO \"Client\" (name) " +
                "VALUES ('" + entity.getName()
                + "');";
    }

    @Override
    protected String deleteString(Integer id) {
        return "DELETE from \"Client\" where ID = " + id + ";";
    }

    @Override
    protected String updateString(Client entity) {
        return "UPDATE \"Client\" SET "
                + "name= '" + entity.getName()
                + "' where ID = " + entity.getId() + ";";
    }

    public Client findByName(String name) {
        if (name == null)
            throw new IllegalArgumentException("id is null");
        logger.info("Find one " + entityType.getName() + " " + name);
        Client entity = null;
        try {
            Statement stmt = dbUtils.getConnection().createStatement();
            ResultSet f = stmt.executeQuery(findByNameString(name));
            if (f.next())
                entity = readEntity(f);
            f.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entity;
    }

    private String findByNameString(String name) {
        return "SELECT * from \"Client\" where name = '" + name + "';";
    }
}
