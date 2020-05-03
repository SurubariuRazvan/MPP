package com.jderu.repository;

import com.jderu.User;

public interface UserRepository extends CRUDRepository<Integer, User> {
    User findByUsername(String username);
}
