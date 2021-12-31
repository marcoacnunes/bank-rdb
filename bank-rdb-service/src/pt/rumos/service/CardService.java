package pt.rumos.service;

import java.util.List;
import pt.rumos.model.Card;

public interface CardService {
    
    Card save(Card card,  Integer accountId, Integer clientId);
    
    List<Card> getAll();

    Card getById(Integer id);

    Card getByClientId(Integer clientId);

    Card getByAccountId(Integer accountId);

    void deleteById(Integer id);
    
}
