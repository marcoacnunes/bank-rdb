package pt.rumos.repository;

import java.util.List;
import java.util.Optional;

import pt.rumos.model.Account;
import pt.rumos.model.Client;

public interface AccountRepository {

    Optional<Account> save(Account account);
    
    Optional<Client> saveAccountClient(Client client, Account account);
    
    List<Client> findAccountClients(Integer accountId);

    List<Account> findAll();

    Optional<Account> findById(Integer id);

    Optional<Account> findByNib(String nib);

    void deleteById(Integer id);

}