package com.jderu.repository.JPARepository;

import com.jderu.Trip;
import com.jderu.TripDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.sql.Timestamp;
import java.util.List;

public interface TripJPARepository extends CrudRepository<Trip, Integer> {

    @Query("SELECT new com.jderu.TripDTO(d.name,t.departure,t.freeSeats) FROM Trip t inner join Destination d on t.destinationID=d.id")
    List<TripDTO> getAllTrips();

    @Query("SELECT t.id FROM Trip t inner join Destination d on t.destinationID = d.id WHERE d.name = ?1 and t.departure = ?2")
    Integer getTripIDByDestinationAndDeparture(String destinationName, Timestamp departure);
}
