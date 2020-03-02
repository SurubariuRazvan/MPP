package repository;

import domain.Client;
import validation.CRUDValidator;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

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
    protected String findOneString(Integer integer) {
        return null;
    }

    @Override
    protected String findAllString() {
        return null;
    }

    @Override
    protected String insertString(Client entity) {
        return null;
    }

    @Override
    protected String deleteString(Integer integer) {
        return null;
    }

    @Override
    protected String updateString(Client entity) {
        return null;
    }
}
