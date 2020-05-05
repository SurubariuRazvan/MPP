package com.jderu.repository.database;

import com.jderu.repository.JPARepository.TripJPARepository;
import com.jderu.repository.TripRepository;
import com.jderu.repository.validation.CRUDValidator;
import com.jderu.Trip;
import com.jderu.TripDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public class DatabaseTripRepository extends AbstractJPARepository<Integer, Trip> implements TripRepository {
    private final TripJPARepository repo;

    @Autowired
    public DatabaseTripRepository(@Qualifier("tripValidator") CRUDValidator<Trip> validator, TripJPARepository repo) {
        super(validator,Trip.class,repo);
        this.repo = repo;
    }

    public List<TripDTO> getAllTrips() {
        return repo.getAllTrips();
    }


    public Integer getTripIDByDestinationAndDeparture(String destinationName, Timestamp departure) {
        return repo.getTripIDByDestinationAndDeparture(destinationName, departure);
    }
}
