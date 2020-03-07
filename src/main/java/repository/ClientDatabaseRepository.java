package repository;

import domain.Client;
import validation.CRUDValidator;

import java.sql.*;

public class ClientDatabaseRepository extends AbstractDatabaseRepository<Integer, Client> {
    public ClientDatabaseRepository(CRUDValidator<Client> validator, Connection c) {
        super(validator, c);
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
        return "INSERT INTO \"Client\" (id, name) " +
                "VALUES (" + entity.getId()
                + ",'" + entity.getName()
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
}
