package pt.rumos.repository;

import java.util.List;
import java.util.Optional;

import pt.rumos.model.Client;

public interface ClientRepository {

    Optional<Client> save(Client client);

    List<Client> findAll();

    Optional<Client> findById(Integer id);
    
    Optional<Client> findByNif(String nif);

    void deleteByNif(String nif);
}
