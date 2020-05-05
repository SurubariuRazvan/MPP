package com.jderu.repository.validation;

import com.jderu.Destination;
import org.springframework.stereotype.Component;

@Component
public class DestinationValidator implements CRUDValidator<Destination> {
    public DestinationValidator() {
    }

    @Override
    public void validate(Destination entity) throws ValidationException {

    }
}
