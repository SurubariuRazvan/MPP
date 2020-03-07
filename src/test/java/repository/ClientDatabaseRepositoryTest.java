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
        Connection c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/MPP-app-test", "postgres", "793582");
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
        logger.info("findOne START");
        clientDatabaseRepository.findOne(3);
        System.out.println(clientDatabaseRepository.findOne(3));
        logger.info("findOne FINISH");
    }

    @Test
    void findAllString() {
        logger.info("findAll START");
        clientDatabaseRepository.findAll();
        for (Client client : clientDatabaseRepository.findAll())
            System.out.println(client);
        logger.info("findAll FINISH");
        logger.error("cumva asta merge");
    }

    @Test
    void insertString() {
        logger.info("insert START");
        clientDatabaseRepository.save(new Client(3, "maria"));
        logger.info("insert FINISH");
    }

    @Test
    void updateString() {
        logger.info("update START");
        clientDatabaseRepository.update(new Client(3, "marcel"));
        logger.info("update FINISH");
    }

    @Test
    void deleteString() {
        logger.info("delete START");
        clientDatabaseRepository.delete(3);
        logger.info("delete FINISH");
    }

}