package pt.rumos.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import pt.rumos.exception.ServiceException;
import pt.rumos.model.Card;

class CardServiceImplTest {
	
	CardService cardService = new CardServiceImpl();

	@Test
	public void testSave_SaveCard() {

	}
	
	@Test
	public void testSave_ClientWith2CreditCards() {
		//Maximum of 1 Credit Card per Client
	}
	
	@Test
	public void testSave_ClientWith2DebitCards() {
		//Maximum of 1 Debit Card per Client
	}
	
	@Test
	public void testSave_AccountWith3CreditCards() {
		//Maximum of 2 Credit Cards per Account
	}
	
	@Test
	public void testSave_AccountWith5DebitCards() {
		//Maximum of 4 Debit Cards per Account
	}
	
	//*****

	@Test
	public void testGetAll_NotEmpty() {
		List<Card> cards = cardService.getAll();
	
		assertTrue(cards != null);
		assertTrue(!cards.isEmpty());
	}
	
	//*****	

	@Test
	public void testGetById_ValidId() {
		Card card = new Card();
		card.setId(8);
	
		assertTrue(card.getId() != null);
	}

	@Test
	public void testGetById_InvalidId() {
		Integer id = 1;
		
		ServiceException exception = assertThrows(ServiceException.class, () -> {
			cardService.getById(id);
	    });
	
		assertEquals("Card with ID: " + id + " not found.", exception.getMessage());
	}

	//*****	

	@Test
	public void testGetByClientId_ValidId() {
		Integer id = 32;
		
		Card card = cardService.getByClientId(id);
		
		assertTrue(card.getId() != null);
	}
	
	@Test
	public void testGetByClientId_InvalidId() {
		Integer id = 1;
		
		ServiceException exception = assertThrows(ServiceException.class, () -> {
		       cardService.getByClientId(id);
	    });
	
		assertEquals("Card with Client ID: " + id + " not found.", exception.getMessage());
	}

	//*****	
	
	@Test
	public void testGetByAccountId_ValidId() {
		Integer id = 11;
		
		Card card = cardService.getByAccountId(id);
		
		assertTrue(card.getId() != null);
	}
	
	@Test
	public void testGetByAccountId_InvalidId() {
		Integer id = 1;
		
		ServiceException exception = assertThrows(ServiceException.class, () -> {
		       cardService.getByClientId(id);
	    });
	
		assertEquals("Card with Account ID: " + id + " not found.", exception.getMessage());
	}
	
	@Test
	public void testDeleteById_ValidId() {
		cardService.deleteById(8);
	}

	@Test
	public void testDeleteById_InvalidId() {
		Card card = new Card();
		card.setId(1);

		ServiceException exception = assertThrows(ServiceException.class, () -> {
			cardService.deleteById(card.getId());
		});

		assertEquals("Card with ID: " + card.getId() + " not found.", exception.getMessage());
	}
}
