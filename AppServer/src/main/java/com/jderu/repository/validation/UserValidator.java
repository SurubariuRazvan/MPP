package com.jderu.repository.validation;

import com.jderu.User;
import org.springframework.stereotype.Component;

@Component
public class UserValidator implements CRUDValidator<User> {
    public UserValidator() {
    }

    @Override
    public void validate(User entity) throws ValidationException {

    }
}
