package com.jderu.repository.validation;

import com.jderu.BookedTrip;
import org.springframework.stereotype.Component;

@Component
public class BookedTripValidator implements CRUDValidator<BookedTrip> {
    public BookedTripValidator() {
    }

    @Override
    public void validate(BookedTrip entity) throws ValidationException {

    }
}
