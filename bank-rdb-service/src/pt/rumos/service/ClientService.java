package pt.rumos.service;

import java.util.List;
import pt.rumos.model.Account;
import pt.rumos.model.Client;

public interface ClientService {
    
    Client save(Client client);

    List<Client> getAll();

    Client getById(Integer id);
    
    Client getByNif(String nif);

    void deleteByNif(String nif);

	Client saveSecondaryOwner(Client client, Account account);

}