package pt.rumos.repository;

import java.util.List;
import java.util.Optional;
import pt.rumos.model.Account;
import pt.rumos.model.Client;

public interface ClientRepository {

    Optional<Client> save(Client client);

    Optional<Client> saveAccountClient(Client client, Account account);
    
    List<Client> findAll();

    List<Client> findAccountClients(Integer accountId);
    
    Optional<Client> findById(Integer id);
    
    Optional<Client> findByNif(String nif);

    void deleteByNif(String nif);
    

}
