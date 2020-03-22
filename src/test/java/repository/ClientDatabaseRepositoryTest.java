package repository;

import domain.Client;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

class ClientDatabaseRepositoryTest {
    private static final Logger logger = LogManager.getLogger();
    static ClientDatabaseRepository clientDatabaseRepository;

    @BeforeAll
    static void getConnection() {
        ApplicationContext context = new ClassPathXmlApplicationContext("App.xml");
        clientDatabaseRepository = context.getBean(ClientDatabaseRepository.class);
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
        for(Client client : clientDatabaseRepository.findAll())
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