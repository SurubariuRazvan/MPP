package com.jderu.repository.database;

import com.jderu.repository.ClientRepository;
import com.jderu.repository.JPARepository.ClientJPARepository;
import com.jderu.repository.validation.CRUDValidator;
import com.jderu.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class DatabaseClientRepository extends AbstractJPARepository<Integer, Client> implements ClientRepository {
    private final ClientJPARepository repo;


    @Autowired
    public DatabaseClientRepository(@Qualifier("clientValidator") CRUDValidator<Client> validator, ClientJPARepository repo) {
        super(validator, Client.class, repo);
        this.repo = repo;
    }


    public Client findByName(String name) {
        if (name == null)
            throw new IllegalArgumentException("username is null");
        logger.info("Find one " + Client.class + " " + name);
        return repo.findByName(name);
    }
}
