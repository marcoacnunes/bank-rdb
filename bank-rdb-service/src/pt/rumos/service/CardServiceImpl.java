package pt.rumos.service;

import java.util.List;

import pt.rumos.exception.ServiceException;
import pt.rumos.model.Card;
import pt.rumos.model.CreditCard;
import pt.rumos.model.DebitCard;
import pt.rumos.repository.CardRepository;
import pt.rumos.repository.CardRepositoryImpl;

public class CardServiceImpl implements CardService {
	
	private CardRepository cardRepository = new CardRepositoryImpl();

	@Override
	public Card save(Card card) {
		Integer cardClientId = card.getClient().getId();
		Integer cardAccountId = card.getAccount().getId();
		int creditCardCounter = 0;
		int debitCardCounter = 0;
		
		for (Card existingCard : cardRepository.findAll()) {
			if(existingCard.getClass().equals(CreditCard.class) && existingCard.getClient().getId().equals(cardClientId)) {
				creditCardCounter += 1;
			}else if(existingCard.getClass().equals(DebitCard.class) && existingCard.getClient().getId().equals(cardClientId)) {
				debitCardCounter += 1;
			}
		}
		
		if(creditCardCounter == 1) throw new ServiceException("Sorry! Not processed! The bank only allows 1 Credit Card per Client.");
		if(debitCardCounter == 1) throw new ServiceException("Sorry! Not processed! The bank only allows 1 Debit Card per Client.");
		
		for (Card existingCard : cardRepository.findAll()) {
			if(existingCard.getClass().equals(CreditCard.class) && existingCard.getAccount().getId().equals(cardAccountId)) {
				creditCardCounter += 1;
				
			}else if(existingCard.getClass().equals(DebitCard.class) && existingCard.getAccount().getId().equals(cardAccountId)) {
				debitCardCounter += 1;
			}
		}
		
		if(creditCardCounter == 2) throw new ServiceException("Sorry! Not processed! The bank only allows 2 Credit Card per Account.");
		if(debitCardCounter == 4) throw new ServiceException("Sorry! Not processed! The bank only allows 4 Debit Card per Account.");
		
	    return cardRepository.save(card).orElseThrow(() -> new ServiceException("There was an error while saving Card"));
	}
	
	@Override
	public List<Card> getAll() {
		return cardRepository.findAll();
	}

	@Override
	public Card getById(Integer id) {
		return cardRepository.findById(id).orElseThrow(() -> new ServiceException("Card with ID: " + id + " not found."));
	}

	@Override
	public Card getByClientId(Integer clientId) {
		return cardRepository.findByClientId(clientId).orElseThrow(() -> new ServiceException("Card with Client ID: " + clientId + " not found."));
	}

	@Override
	public Card getByAccountId(Integer accountId) {
		return cardRepository.findByAccountId(accountId).orElseThrow(() -> new ServiceException("Card with Account ID: " + accountId + " not found."));
	}

	@Override
	public void deleteById(Integer id) {
		cardRepository.deleteById(id);
	}
}
