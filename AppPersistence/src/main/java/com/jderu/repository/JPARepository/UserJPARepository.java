package com.jderu.repository.JPARepository;

import com.jderu.User;
import org.springframework.data.repository.CrudRepository;

public interface UserJPARepository extends CrudRepository<User, Integer> {
    User findByUsername(String username);
}
