package pt.rumos.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import pt.rumos.exception.ServiceException;
import pt.rumos.model.Account;
import pt.rumos.model.Client;
import pt.rumos.model.CreditCard;

class CardServiceImplTest {
	
	CardService cardService = new CardServiceImpl();

//	@Test
//	public void testSave_SaveCreditCard() {
//		Client client = new Client();
//		client.setId(32);
//		Account account = new Account();
//		account.setId(13);
//		CreditCard creditCard = new CreditCard();
//		creditCard.setClient(client);
//		creditCard.setAccount(account);
//		
//		Card card = cardService.save(creditCard);
//		
//		assertTrue(card.getId() != null);
//	}
//	
//	@Test
//	public void testSave_SaveDebitCard() {
//		Client client = new Client();
//		client.setId(32);
//		Account account = new Account();
//		account.setId(13); 
//		DebitCard debitCard = new DebitCard();
//		debitCard.setClient(client);
//		debitCard.setAccount(account);
//		
//		Card card = cardService.save(debitCard);
//		
//		assertTrue(card.getId() != null);
//	}
//	
//	@Test
//	public void testSave_ClientWith2CreditCards() {
//													//Limit of 1 Credit Card per Client			
//		Client client = new Client();				//Client already has 1 credit Card
//		client.setId(32); 							
//		Account account = new Account();
//		account.setId(13);
//		CreditCard creditCard = new CreditCard();
//		creditCard.setClient(client);
//		creditCard.setAccount(account);
//		
//		ServiceException exception = assertThrows(ServiceException.class, () -> {
//			cardService.save(creditCard);
//	    });
//	
//		assertEquals("Sorry! Not processed! The bank only allows 1 Credit Card per Client.", exception.getMessage());
//	}
//	
//	@Test
//	public void testSave_ClientWith2DebitCards() {
//													//Limit of 1 Debit Card per Client
//		Client client = new Client();				//Client already has 1 debit Card
//		client.setId(33); 						
//		Account account = new Account();
//		account.setId(12);
//		DebitCard debitCard = new DebitCard();
//		debitCard.setClient(client);
//		debitCard.setAccount(account);
//		
//		ServiceException exception = assertThrows(ServiceException.class, () -> {
//			cardService.save(debitCard);
//	    });
//	
//		assertEquals("Sorry! Not processed! The bank only allows 1 Debit Card per Client.", exception.getMessage());
//	}
//	
//	@Test
//	public void testSave_AccountWith3CreditCards() {
//		Account account = new Account();					//Limit of 2 Credit Cards per Account
//		account.setId(12);									//Account already has 2 credit Cards
//		Client client = new Client();						
//		client.setId(44);
//		CreditCard creditCard = new CreditCard();
//		creditCard.setClient(client);
//		creditCard.setAccount(account);
//		
//		ServiceException exception = assertThrows(ServiceException.class, () -> {
//			cardService.save(creditCard);
//	    });
//	
//		assertEquals("Sorry! Not processed! The bank only allows 2 Credit Card per Account.", exception.getMessage());
//		
//	}
//	
	@Test
	public void testSave_AccountWith5DebitCards() {
		Account account = new Account();					//Limit of 4 Debit Cards per Account
		account.setId(12);									//Account already has 4 credit Cards
		Client client = new Client();						
		client.setId(44);
		CreditCard creditCard = new CreditCard();
		creditCard.setClient(client);
		creditCard.setAccount(account);
		
		ServiceException exception = assertThrows(ServiceException.class, () -> {
			cardService.save(creditCard);
	    });
	
		assertEquals("Sorry! Not processed! The bank only allows 2 Credit Card per Account.", exception.getMessage());
	}
//	
//	//*****
//
//	@Test
//	public void testGetAll_NotEmpty() {
//		List<Card> cards = cardService.getAll();
//	
//		assertTrue(cards != null);
//		assertTrue(!cards.isEmpty());
//	}
//	
//	//*****	
//
//	@Test
//	public void testGetById_ValidId() {
//		Card card = new Card();
//		card.setId(8);
//	
//		assertTrue(card.getId() != null);
//	}
//
//	@Test
//	public void testGetById_InvalidId() {
//		Integer id = 1;
//		
//		ServiceException exception = assertThrows(ServiceException.class, () -> {
//			cardService.getById(id);
//	    });
//	
//		assertEquals("Card with ID: " + id + " not found.", exception.getMessage());
//	}
//
//	//*****	
//
//	@Test
//	public void testGetByClientId_ValidId() {
//		Integer id = 32;
//		
//		Card card = cardService.getByClientId(id);
//		
//		assertTrue(card.getId() != null);
//	}
//	
//	@Test
//	public void testGetByClientId_InvalidId() {
//		Integer id = 1;
//		
//		ServiceException exception = assertThrows(ServiceException.class, () -> {
//		       cardService.getByClientId(id);
//	    });
//	
//		assertEquals("Card with Client ID: " + id + " not found.", exception.getMessage());
//	}
//
//	//*****	
//	
//	@Test
//	public void testGetByAccountId_ValidId() {
//		Integer id = 11;
//		
//		Card card = cardService.getByAccountId(id);
//		
//		assertTrue(card.getId() != null);
//	}
//	
//	@Test
//	public void testGetByAccountId_InvalidId() {
//		Integer id = 1;
//		
//		ServiceException exception = assertThrows(ServiceException.class, () -> {
//		       cardService.getByClientId(id);
//	    });
//	
//		assertEquals("Card with Account ID: " + id + " not found.", exception.getMessage());
//	}
//	
//	@Test
//	public void testDeleteById_ValidId() {
//		cardService.deleteById(8);
//	}
//
//	@Test
//	public void testDeleteById_InvalidId() {
//		Card card = new Card();
//		card.setId(1);
//
//		ServiceException exception = assertThrows(ServiceException.class, () -> {
//			cardService.deleteById(card.getId());
//		});
//
//		assertEquals("Card with ID: " + card.getId() + " not found.", exception.getMessage());
//	}
}
