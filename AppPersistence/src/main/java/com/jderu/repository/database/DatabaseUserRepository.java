package com.jderu.repository.database;

import com.jderu.repository.JPARepository.UserJPARepository;
import com.jderu.repository.UserRepository;
import com.jderu.repository.validation.CRUDValidator;
import com.jderu.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class DatabaseUserRepository extends AbstractJPARepository<Integer, User> implements UserRepository {
    private final UserJPARepository repo;


    @Autowired
    public DatabaseUserRepository(@Qualifier("userValidator") CRUDValidator<User> validator, UserJPARepository repo) {
        super(validator,User.class,repo);
        this.repo = repo;
    }

    @Override
    public User findByUsername(String username) {
        if (username == null)
            throw new IllegalArgumentException("username is null");
        logger.info("Find one " + User.class + " " + username);
        return repo.findByUsername(username);
    }
}
