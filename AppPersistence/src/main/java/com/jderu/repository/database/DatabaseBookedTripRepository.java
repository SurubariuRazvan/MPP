package com.jderu.repository.database;

import com.jderu.repository.BookedTripRepository;
import com.jderu.repository.validation.CRUDValidator;
import com.jderu.repository.JPARepository.BookedTripJPARepository;
import com.jderu.BookedTrip;
import com.jderu.BookedTripDTO;
import com.jderu.BookedTripID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public class DatabaseBookedTripRepository extends AbstractJPARepository<BookedTripID,BookedTrip> implements BookedTripRepository {
    private final BookedTripJPARepository repo;

    @Autowired
    public DatabaseBookedTripRepository(@Qualifier("bookedTripValidator") CRUDValidator<BookedTrip> validator, BookedTripJPARepository repo) {
        super(validator,BookedTrip.class,repo);
        this.repo = repo;
    }

    public List<BookedTripDTO> search(String destinationName, Timestamp departure) {
        return repo.search(destinationName, departure);
    }
}