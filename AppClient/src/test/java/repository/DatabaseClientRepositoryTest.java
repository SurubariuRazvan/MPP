package repository;

import domain.Client;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import repository.database.DatabaseClientRepository;

class DatabaseClientRepositoryTest {
    private static final Logger logger = LogManager.getLogger();
    static DatabaseClientRepository databaseClientRepository;

    @BeforeAll
    static void getConnection() {
        ApplicationContext context = new ClassPathXmlApplicationContext("App.xml");
        databaseClientRepository = context.getBean(DatabaseClientRepository.class);
        logger.info("Repository created");
    }

    @Test
    void readEntity() {
    }

    @Test
    void findOneString() {
        databaseClientRepository.findOne(3);
        System.out.println(databaseClientRepository.findOne(3));
    }

    @Test
    void findAllString() {
        databaseClientRepository.findAll();
        for(Client client : databaseClientRepository.findAll())
            System.out.println(client);
    }

    @Test
    void insertString() {
        databaseClientRepository.save(new Client(3, "maria"));
    }

    @Test
    void updateString() {
        databaseClientRepository.update(new Client(3, "marcel"));
    }

    @Test
    void deleteString() {
        databaseClientRepository.delete(3);
    }

}