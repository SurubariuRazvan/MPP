package com.jderu.repository.validation;

import com.jderu.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientValidator implements CRUDValidator<Client> {
    public ClientValidator() {
    }

    @Override
    public void validate(Client entity) throws ValidationException {

    }
}
