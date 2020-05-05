package com.jderu.repository.JPARepository;

import com.jderu.BookedTrip;
import com.jderu.BookedTripDTO;
import com.jderu.BookedTripID;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.sql.Timestamp;
import java.util.List;

public interface BookedTripJPARepository extends CrudRepository<BookedTrip, BookedTripID> {
    @Query("SELECT new com.jderu.BookedTripDTO(c.id,c.name,b.id.seatNumber) FROM BookedTrip b inner join Client c on b.clientID=c.id inner join Trip t on b.id.tripID= t.id inner join Destination d on t.destinationID = d.id where d.name = ?1 and t.departure= ?2")
    List<BookedTripDTO> search(String destinationName, Timestamp departure);
}
