package repository;

import domain.BookedTrip;
import domain.BookedTripDTO;
import domain.BookedTripID;

import java.sql.Timestamp;
import java.util.List;

public interface BookedTripRepository extends CRUDRepository<BookedTripID, BookedTrip> {
    List<BookedTripDTO> search(String destinationName, Timestamp departure);

}
