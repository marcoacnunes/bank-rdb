package pt.rumos.service;

import java.util.List;
import pt.rumos.model.Account;
import pt.rumos.model.Client;

public interface ClientService {
    
    Client save(Client client);

    Client saveAccountClient(Client client, Account account);
    
    List<Client> getAll();
    
    List<Client> getAccountClients(Integer accountId);

    Client getById(Integer id);
    
    Client getByNif(String nif);

    void deleteByNif(String nif);

	
}