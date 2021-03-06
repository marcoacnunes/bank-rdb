package pt.rumos.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import pt.rumos.model.Card;
import pt.rumos.model.CreditCard;
import pt.rumos.model.DebitCard;

public class CardRepositoryListImpl implements CardRepository {
	
	private ArrayList<Card> cards = new ArrayList<Card>();
	private static int id = 1;
	
	@Override
	public Optional<Card> save(Card card) {
		if(card.getClass().equals(CreditCard.class)) {
			return setCreditCard(card);
			
		}else if(card.getClass().equals(DebitCard.class)) {
			return setDebitCard(card);
		}
		return null;
	}

	private Optional<Card> setCreditCard(Card card) {
		CreditCard creditCard = (CreditCard) card;
		creditCard.setId(id);
		id++;
		creditCard.setPlafond(1000.00D);
		
		Random r = new Random();
	    String randomNumber = String.format("%04d", r.nextInt(1001));
	    creditCard.setPin(randomNumber);
	    cards.add(creditCard);
	    
		System.out.println("Credit Card ID is: " + creditCard.getId());
		System.out.println("Credit Card Plafond is: " + creditCard.getPlafond());
	    System.out.println("Credit Card Created! Your PIN number for this Credit Card is: " + creditCard.getPin());
		return Optional.of(creditCard);
	}
	
	public Optional<Card> setDebitCard(Card card) {
		DebitCard debitCard = (DebitCard) card;
		debitCard.setId(id);
		id++; 
		
		Random r = new Random();
		String randomNumber = String.format("%04d", r.nextInt(1001));
		debitCard.setPin(randomNumber);
		cards.add(debitCard);
		
		System.out.println("Your DebitCard id is: " + debitCard.getId());
		System.out.println("Debit Card Created! Your PIN number for this Debit Card is: " + debitCard.getPin());
		return Optional.of(debitCard);	
	}

	@Override
	public List<Card> findAll() {
		return cards;
	}

	@Override
	public Optional<Card> findById(Integer id) {
		for(Card card : cards) {
			if(card.getId().equals(id)) {
				return Optional.of(card);
			}
		}
		return Optional.empty();
	}

	@Override
	public Optional<Card> findByClientId(Integer clientId) {
		for(Card card : cards) {
			if(card.getClient().getId().equals(clientId)) {
				return Optional.of(card);
			}
		}
		return Optional.empty();
	}

	@Override
	public Optional<Card> findByAccountId(Integer accountId) {
		for(Card card : cards) {
			if(card.getAccount().getId().equals(accountId)) {
				return Optional.of(card);
			}
		}
		return Optional.empty(); 
	}

	@Override
	public void deleteById(Integer id) {
		Optional<Card> card = findById(id);
		
		if(card.isPresent()) {
			cards.remove(card.get());
		}
	}
}

