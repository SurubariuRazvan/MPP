package App;

import config.ApplicationContext;
import domain.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import repository.*;
import service.AppService;
import service.UserService;
import ui.UI;
import validation.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Main {
    private static Logger logger = LogManager.getLogger();

    private static UserService getUserService(String url, String user, String password) {
        Connection userConnection = null;
        UserService userService = null;
        try {
            userConnection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            logger.error("User connection failed");
            e.printStackTrace();
        }
        if (userConnection != null) {
            UserDatabaseRepository userRepo = new UserDatabaseRepository(new UserValidator(), userConnection);
            userService = new UserService(userRepo);
        }
        return userService;
    }

    private static AppService getAppService(String url, String user, String password) {
        Connection appConnection = null;
        AppService appService = null;
        try {
            appConnection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            logger.error("App connection failed");
            e.printStackTrace();
        }
        if (appConnection != null) {
            BookedTripDatabaseRepository bookedTripRepo = new BookedTripDatabaseRepository(new BookedTripValidator(), appConnection);
            ClientDatabaseRepository clientRepo = new ClientDatabaseRepository(new ClientValidator(), appConnection);
            DestinationDatabaseRepository destinationRepo = new DestinationDatabaseRepository(new DestinationValidator(), appConnection);
            TripDatabaseRepository tripRepo = new TripDatabaseRepository(new TripValidator(), appConnection);
            appService = new AppService(bookedTripRepo, clientRepo, destinationRepo, tripRepo);
        }
        return appService;
    }

    private static Properties getProperties() {
        Properties properties = null;
        try {
            properties = ApplicationContext.getPROPERTIES();
        } catch (Exception e) {
            logger.error("Missing properties");
            e.printStackTrace();
        }
        return properties;
    }

    public static void main(String[] args) {
        Properties properties = getProperties();
        if (properties != null) {
            UserService userService = getUserService(properties.getProperty("databaseUserUrl"), properties.getProperty("databaseUserUser"), properties.getProperty("databaseUserPassword"));
            AppService appService = getAppService(properties.getProperty("databaseAppUrl"), properties.getProperty("databaseAppUser"), properties.getProperty("databaseAppPassword"));

            if (userService != null && appService != null) {
                UI ui = new UI(userService, appService);
                ui.run();
            }
        }
    }
}
