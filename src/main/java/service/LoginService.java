package service;

import domain.User;
import repository.UserDatabaseRepository;

public class LoginService {
    UserDatabaseRepository users;

    public LoginService(UserDatabaseRepository users) {
        this.users = users;
    }

    public User login(String username, String password) {
        User user = users.findByUsername(username);
        if (user == null || User.hash(password).equals(user.getPasswordHash()))
            return user;
        return null;
    }
}
