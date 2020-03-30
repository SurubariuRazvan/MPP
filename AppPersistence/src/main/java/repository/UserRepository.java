package repository;

import domain.User;

public interface UserRepository extends CRUDRepository<Integer, User> {
    User findByUsername(String username);
}
