package com.jderu.repository.validation;

import com.jderu.Trip;
import org.springframework.stereotype.Component;

@Component
public class TripValidator implements CRUDValidator<Trip> {
    public TripValidator() {
    }

    @Override
    public void validate(Trip entity) throws ValidationException {

    }
}
