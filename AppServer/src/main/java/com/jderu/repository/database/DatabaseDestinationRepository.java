package com.jderu.repository.database;

import com.jderu.repository.DestinationRepository;
import com.jderu.repository.JPARepository.DestinationJPARepository;
import com.jderu.repository.validation.CRUDValidator;
import com.jderu.Destination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class DatabaseDestinationRepository extends AbstractJPARepository<Integer, Destination> implements DestinationRepository {
    private DestinationJPARepository repo;

    @Autowired
    public DatabaseDestinationRepository(@Qualifier("destinationValidator") CRUDValidator<Destination> validator, DestinationJPARepository repo) {
        super(validator,Destination.class,repo);
        this.repo = repo;
    }
}
