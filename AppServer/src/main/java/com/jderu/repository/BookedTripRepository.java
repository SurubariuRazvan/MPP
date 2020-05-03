package com.jderu.repository;

import com.jderu.BookedTrip;
import com.jderu.BookedTripDTO;
import com.jderu.BookedTripID;

import java.sql.Timestamp;
import java.util.List;

public interface BookedTripRepository extends CRUDRepository<BookedTripID, BookedTrip> {
    List<BookedTripDTO> search(String destinationName, Timestamp departure);

}
