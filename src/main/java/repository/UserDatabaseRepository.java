package repository;

import domain.User;
import validation.CRUDValidator;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDatabaseRepository extends AbstractDatabaseRepository<Integer, User> {
    public UserDatabaseRepository(CRUDValidator<User> validator, Connection c) {
        super(validator, c, User.class);
    }

    @Override
    protected User readEntity(ResultSet result) throws SQLException {
        int id = result.getInt("id");
        String username = result.getString("username");
        String passwordHash = result.getString("passwordHash");
        return new User(id, username, passwordHash);
    }

    @Override
    protected String findOneString(Integer id) {
        return "SELECT * from \"User\" where ID = " + id + ";";
    }

    @Override
    protected String findAllString() {
        return "SELECT * from \"User\";";
    }

    @Override
    protected String insertString(User entity) {
        return "INSERT INTO \"User\" (username, \"passwordHash\") " +
                "VALUES ('" + entity.getUsername()
                + "','" + entity.getPasswordHash()
                + "');";
    }

    @Override
    protected String deleteString(Integer id) {
        return "DELETE from \"User\" where ID = " + id + ";";
    }

    @Override
    protected String updateString(User entity) {
        return "UPDATE \"User\" SET "
                + "username= '" + entity.getUsername()
                + "passwordHash= '" + entity.getPasswordHash()
                + "' where ID = " + entity.getId() + ";";
    }

    public User findByUsername(String username) {
        if (username == null)
            throw new IllegalArgumentException("id is null");
        logger.info("Find one " + entityType.getName() + " " + username);
        User entity = null;
        try {
            Statement stmt = c.createStatement();
            var f = stmt.executeQuery(findByUsernameString(username));
            if (f.next())
                entity = readEntity(f);
            f.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entity;
    }

    private String findByUsernameString(String username) {
        return "SELECT * from \"User\" where username = '" + username + "';";
    }
}
