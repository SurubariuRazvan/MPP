package service;

import domain.User;
import repository.database.DatabaseUserRepository;

public class LoginService {
    DatabaseUserRepository users;

    public LoginService(DatabaseUserRepository users) {
        this.users = users;
    }

    public User login(String username, String password) {
        User user = users.findByUsername(username);
        if (user == null || User.hash(password).equals(user.getPasswordHash()))
            return user;
        return null;
    }
}
