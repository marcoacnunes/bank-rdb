package pt.rumos.service;

import java.util.List;
import pt.rumos.model.Account;

public interface AccountService {
 
    Account save(Account account);

    List<Account> getAll();

    Account getById(Integer id);

    Account getByNib(String nib);

    void deleteById(Integer id);

}
