package validation;

import domain.User;

public class UserValidator implements CRUDValidator<User> {
    public UserValidator() {
    }

    @Override
    public void validate(User entity) throws ValidationException {

    }
}
