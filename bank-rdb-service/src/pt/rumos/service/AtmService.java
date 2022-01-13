package pt.rumos.service;

import pt.rumos.model.Account;
import pt.rumos.model.Card;
import pt.rumos.model.Client;
import pt.rumos.model.CreditCard;

public interface AtmService {
    
	String checkCardPin(Card card);
	
	Card displayDebitCards(Client client);
	
	Card displayCreditCards(Client client);
	
    void withdraw(Account account, Card card);
    
	void transfer(Account account, Account receivingAccount);

	void deposit(Account account);

	void payPlafond(Account account, CreditCard creditCard, Double valueInputPlafond);
}