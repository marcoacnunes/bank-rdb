package pt.rumos.repository;

import java.util.List;
import java.util.Optional;
import pt.rumos.model.Account;

public interface AccountRepository {

    Optional<Account> save(Account account);

    List<Account> findAll();

    Optional<Account> findById(Integer id);

    Optional<Account> findByNib(String nib);

    void deleteById(Integer id);

}