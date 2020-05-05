package com.jderu.repository.JPARepository;

import com.jderu.Client;
import org.springframework.data.repository.CrudRepository;

public interface ClientJPARepository extends CrudRepository<Client, Integer> {
    Client findByName(String name);
}
