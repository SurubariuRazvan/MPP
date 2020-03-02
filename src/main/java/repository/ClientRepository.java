package repository;

import domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client,Integer> {
    List<Client> findByName(String name);
    List<Client> findAll();
}
