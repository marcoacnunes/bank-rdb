package pt.rumos.service;

import java.util.List;
import pt.rumos.model.Account;
import pt.rumos.model.Card;
import pt.rumos.model.Client;

public interface CardService {
    
    Card save(Card card,  Account account, Client client);
    
    List<Card> getAll();

    Card getById(Integer id);

    Card getByClientId(Integer clientId);

    Card getByAccountId(Integer accountId);

    void deleteById(Integer id);
    
}
