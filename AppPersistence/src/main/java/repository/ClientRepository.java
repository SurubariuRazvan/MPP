package repository;

import domain.Client;

public interface ClientRepository extends CRUDRepository<Integer, Client> {
    Client findByName(String name);
}
