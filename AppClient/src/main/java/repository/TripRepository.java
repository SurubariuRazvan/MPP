package repository;

import domain.Trip;
import domain.TripDTO;

import java.sql.Timestamp;
import java.util.List;

public interface TripRepository extends CRUDRepository<Integer, Trip> {
    List<TripDTO> getAllTrips();

    Integer getTripIDByDestinationAndDeparture(String destinationName, Timestamp departure);

}
