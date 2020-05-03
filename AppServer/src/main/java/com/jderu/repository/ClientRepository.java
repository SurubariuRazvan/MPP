package com.jderu.repository;

import com.jderu.Client;

public interface ClientRepository extends CRUDRepository<Integer, Client> {
    Client findByName(String name);
}
