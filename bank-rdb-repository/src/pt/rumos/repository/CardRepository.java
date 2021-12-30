package pt.rumos.repository;

import java.util.List;
import java.util.Optional;
import pt.rumos.model.Account;
import pt.rumos.model.Card;
import pt.rumos.model.Client;

public interface CardRepository {

	Optional<Card> save(Card card, Account account, Client client);

    List<Card> findAll();

    Optional<Card> findById(Integer id);

    Optional<Card> findByClientId(Integer clientId);

    Optional<Card> findByAccountId(Integer accountId);

    void deleteById(Integer id);
}
