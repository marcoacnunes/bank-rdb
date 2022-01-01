package pt.rumos.service;

import java.util.List;

import pt.rumos.model.Account;
import pt.rumos.model.Client;

public interface AccountService {
 
    Account save(Account account);
    
    Client saveAccountClient(Client client, Account account);
    
    List<Client> getAccountClients(Integer accountId);
    	
    List<Account> getAll();

    Account getById(Integer id);

    Account getByNib(String nib);

    void deleteById(Integer id);

}
