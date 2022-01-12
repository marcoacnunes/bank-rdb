package pt.rumos.repository;

import java.util.List;
import java.util.Optional;

import pt.rumos.model.Account;
import pt.rumos.model.Client;

public interface AccountRepository {

    Optional<Account> save(Account account);
    
    Optional<Client> saveSecondaryClient(Account account);
    
    List<Client> findSecondaryClients(Account account);

    List<Account> findAll();

    Optional<Account> findById(Integer id);

    Optional<Account> findByNib(String nib);

    void deleteById(Integer id);

}