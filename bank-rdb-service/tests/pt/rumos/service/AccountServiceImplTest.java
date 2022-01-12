package pt.rumos.service;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Test;

import pt.rumos.exception.ServiceException;
import pt.rumos.model.Account;
import pt.rumos.model.Client;

public class AccountServiceImplTest {

	private AccountService accountService = new AccountServiceImpl();
	
//	@Test
//	public void testSave() {
//		
//		Account account = new Account();
//		Client client = new Client();
//		client.setId(32);
//		account.setPrimaryOwner(client);
//		
//		Account savedAccount = accountService.save(account);
//		assertTrue(savedAccount.getNib() != null);
//		assertTrue(savedAccount.getId() != null);
//	}

	@Test
	void testSaveAccountClient() {
		fail("Not yet implemented");
	}
//
//	@Test
//	void testGetAccountClients() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testGetAll() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testGetById() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testGetByNib() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testDeleteById_validId() {
//		
//		Account account = new Account();
//		Client client = new Client();
//		client.setId(32);
//		account.setPrimaryOwner(client);
//		Account savedAccount = accountService.save(account);
//		accountService.deleteById(savedAccount.getId());
//	}
//	
//	@Test
//	public void testDeleteById_invalidId() {
//		
//		Account account = new Account();
//		Client client = new Client();
//		client.setId(32);
//		account.setPrimaryOwner(client);
//		Account savedAccount = accountService.save(account);
//		
//		ServiceException exception = assertThrows(ServiceException.class, () -> {
//	        accountService.deleteById(savedAccount.getId() +1);
//	    });
//		assertEquals("Account with ID: " + (savedAccount.getId() +1) + " not found", exception.getMessage());
//	}

}
