package pt.rumos.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.Test;

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
	
//	@Test
//	public void testSave_NullClient() {
//			
//		RuntimeException exception = assertThrows(RuntimeException.class, () -> {
//	        accountService.save(new Account());
//	    });
//		assertEquals("Sql statement not executed!", exception.getMessage());
//	}
	
//	@Test
//	public void testSave_invalidAccount() {
//		
//		Account account = new Account();
//		Client client = new Client();
//		account.setPrimaryOwner(client);
//		
//		ServiceException exception = assertThrows(ServiceException.class, () -> {
//	       accountService.save(account);
//	    });
//		assertEquals("There was a problem while saving Account.", exception.getMessage());
//	
//	}

//	@Test
//	public void testSaveSecondaryClient() {
//		
//		Client client = new Client();
//		client.setId(33);
//		Account secondaryClientAccount = new Account();
//		secondaryClientAccount.setId(22);
//		secondaryClientAccount.getSecondaryOwners().add(client);
//		
//		secondaryClientAccount = accountService.saveSecondaryClient(secondaryClientAccount);
//		assertEquals("Limit Number of Secondary Clients of this Account has been reached!", exception.getMessage());
//		assertTrue(secondaryClientAccount.getId() == 22);
//		
//	}
	
//	@Test
//	public void testSaveSecondaryClient() {
//		
//		Client client = new Client();
//		client.setId(33);
//		final Account savedSecondary = new Account();
//		savedSecondary.setId(22);
//		savedSecondary.getSecondaryOwners().add(client);
//		
//		savedSecondary = accountService.saveSecondaryClient(savedSecondary);
//		
//		assertEquals("Limit Number of Secondary Clients of this Account has been reached!", exception.getMessage());
//		assertTrue(savedSecondary.getId() == 22);
//		
//	}
//
//	@Test
//	public void testGetAccountClients_NotEmpty() {
//
//		Account account = new Account();
//		account.setId(12);//Existing account ID in DB
//		
//		List<Client> clients = accountService.getSecondaryClients(account);
//		assertTrue(clients != null);
//		assertTrue(!clients.isEmpty());
//	}
//	
//	@Test
//	public void testGetAccountClients_IsEmpty() {
//		
//		Account account = new Account();
//		account.setId(12);
//		
//		List<Client> clients = accountService.getSecondaryClients(account);
//		
//		assertTrue(clients.isEmpty());
//	}
//
//	@Test
//	public void testGetAll_NotEmpty() {
//		
//		List<Account> accounts = accountService.getAll();
//		assertTrue(accounts != null);
//		assertTrue(!accounts.isEmpty());
//	}
	
//
//	@Test
//	public void testGetById_ValidId() {
//		
//		Account account = accountService.getAll().get(accountService.getAll().size() -1);
//		account = accountService.getById(account.getId());
//		assertTrue(account.getNib() != null);
//		assertTrue(account.getId() != null);
//	}
	
//	@Test
//	public void testGetById_InvalidId() {
//		
//		Integer id = accountService.getAll().size();
//		
//		ServiceException exception = assertThrows(ServiceException.class, () -> {
//	       accountService.getById(id);
//	    });
//		assertEquals("Account with ID: " + id + " not found", exception.getMessage());
//	}
//
//	@Test
//	public void testGetByNib_ValidNib() {
//		
//		Account account = accountService.getAll().get(accountService.getAll().size() -1);
//		account = accountService.getByNib(account.getNib());
//		assertTrue(account.getNib() != null);
//		assertTrue(account.getId() != null);
//	}
	
//	@Test
//	public void testGetByNib_InvalidNib() {
//		
//		String nib = "1";
//		
//		ServiceException exception = assertThrows(ServiceException.class, () -> {
//			accountService.getByNib(nib);
//		});
//		assertEquals("Account with NIB: " + nib + " not found", exception.getMessage());
//	}
//
//	@Test
//	public void testDeleteById_ValidId() {
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
//	public void testDeleteById_InvalidId() {
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
