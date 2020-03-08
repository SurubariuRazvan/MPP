package repository;

import domain.Client;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import validation.ClientValidator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class ClientDatabaseRepositoryTest {
    static ClientDatabaseRepository clientDatabaseRepository;
    private static final Logger logger = LogManager.getLogger();

    @BeforeAll
    static void getConnection() throws SQLException {
        Connection c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/MPP-app", "postgres", "793582");
        if (c.isClosed())
            logger.error("Connection failed");
        else
            logger.info("Connected");
        clientDatabaseRepository = new ClientDatabaseRepository(new ClientValidator(), c);
        logger.info("Repository created");
    }

    @Test
    void readEntity() {
    }

    @Test
    void findOneString() {
        clientDatabaseRepository.findOne(3);
        System.out.println(clientDatabaseRepository.findOne(3));
    }

    @Test
    void findAllString() {
        clientDatabaseRepository.findAll();
        for (Client client : clientDatabaseRepository.findAll())
            System.out.println(client);
    }

    @Test
    void insertString() {
        clientDatabaseRepository.save(new Client(3, "maria"));
    }

    @Test
    void updateString() {
        clientDatabaseRepository.update(new Client(3, "marcel"));
    }

    @Test
    void deleteString() {
        clientDatabaseRepository.delete(3);
    }

}