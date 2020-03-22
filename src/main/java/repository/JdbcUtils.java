package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by grigo on 3/4/17.
 */
public class JdbcUtils {
    private Properties jdbcProps;
    private Connection instance = null;

    public JdbcUtils(Properties props) {
        jdbcProps = props;
    }

    private Connection getNewConnection() {
        String driver = jdbcProps.getProperty("driver");
        String url = jdbcProps.getProperty("url");
        String user = jdbcProps.getProperty("user");
        String pass = jdbcProps.getProperty("pass");
        Connection con = null;
        try {
            Class.forName(driver);
            if (user != null && pass != null)
                con = DriverManager.getConnection(url, user, pass);
            else
                con = DriverManager.getConnection(url);
        } catch (ClassNotFoundException e) {
            System.out.println("Error loading driver " + e);
        } catch (SQLException e) {
            System.out.println("Error getting connection " + e);
        }
        return con;
    }

    public Connection getConnection() {
        try {
            if (instance == null || instance.isClosed())
                instance = getNewConnection();

        } catch (SQLException e) {
            System.out.println("Error DB " + e);
        }
        return instance;
    }
}
