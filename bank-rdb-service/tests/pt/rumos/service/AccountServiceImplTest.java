package pt.rumos.service;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.Test;

import pt.rumos.exception.ServiceException;
import pt.rumos.model.Account;
import pt.rumos.model.Client;

public class AccountServiceImplTest {
	
	private AccountService accountService = new AccountServiceImpl();

	
	@Test
	public void testSave_SaveAccount() {
		
		Account account = new Account();
		Client client = new Client();
		client.setId(32);
		account.setPrimaryOwner(client);
		
		Account savedAccount = accountService.save(account);
		
		assertTrue(savedAccount.getNib() != null);
		assertTrue(savedAccount.getId() != null);
	}
	
	@Test
	public void testSave_NullClient() {
			
		RuntimeException exception = assertThrows(RuntimeException.class, () -> {
	        accountService.save(new Account());
	    });
		assertEquals("Sql statement not executed!", exception.getMessage());
	}
	
	@Test
	public void testSave_UpdateAccount() {
		
		Account account = new Account();
		Client client = new Client();
		client.setId(32);
		account.setPrimaryOwner(client);
		account.setId(23);
		account.setBalance(200.00);
		
		Account savedAccount = accountService.save(account);
		
		assertTrue(savedAccount.getBalance() == 200.00);
	}

	@Test
	public void testSave_SecondaryClientLimit() {
		//Limit number of Secondary Clients is 4
		Account account = new Account();
		account.setId(22);
		Client primaryClient = new Client();
		primaryClient.setId(33);
		account.setPrimaryOwner(primaryClient);
		Client secondaryClient1 = new Client();
		secondaryClient1.setId(33);
		account.getSecondaryOwners().add(secondaryClient1);
		Client secondaryClient2 = new Client();
		secondaryClient2.setId(35);
		account.getSecondaryOwners().add(secondaryClient2);
		Client secondaryClient3 = new Client();
		secondaryClient3.setId(36);
		account.getSecondaryOwners().add(secondaryClient3);
		Client secondaryClient4 = new Client();
		secondaryClient4.setId(37);
		account.getSecondaryOwners().add(secondaryClient4);
		Client secondaryClient5 = new Client();
		secondaryClient5.setId(41);
		account.getSecondaryOwners().add(secondaryClient5);

		
		ServiceException exception = assertThrows(ServiceException.class, () -> {
			Account savedAccount = accountService.save(account);
		});
		
		assertEquals("Limit Number of Secondary Clients has been reached.", exception.getMessage());
	}
	
	@Test
	public void testSave_SecondaryClient() {
		
		Account account = new Account();
		account.setId(23);
		Client primaryClient = new Client();
		primaryClient.setId(32);
		primaryClient.setName("JunitTestSecondary");
		account.setPrimaryOwner(primaryClient);
		Client secondaryClient = new Client();
		secondaryClient.setId(41);
		account.getSecondaryOwners().add(secondaryClient);
		
		Account savedAccountWithSecondaryClient = accountService.save(account);
		
		assertTrue(account.getSecondaryOwners().size() > 0);
	}

	@Test
	public void testGetAccountClients_NotEmpty() {

		Account account = new Account();
		account.setId(12);
		
		List<Client> clients = accountService.getSecondaryClients(account.getId());
		assertTrue(clients != null);
		assertTrue(!clients.isEmpty());
	}
	
	@Test
	public void testGetAccountClients_Empty() {
		
		Account account = new Account();
		account.setId(12);
		
		List<Client> clients = accountService.getSecondaryClients(account.getId());
		
		assertTrue(clients.isEmpty());
	}

	@Test
	public void testGetAll_NotEmpty() {
		
		List<Account> accounts = accountService.getAll();
		assertTrue(accounts != null);
		assertTrue(!accounts.isEmpty());
	}
	

	@Test
	public void testGetById_ValidId() {
		
		Account account = accountService.getAll().get(accountService.getAll().size() -1);
		account = accountService.getById(account.getId());
		assertTrue(account.getNib() != null);
		assertTrue(account.getId() != null);
	}
	
	@Test
	public void testGetById_InvalidId() {
		
		Integer id = accountService.getAll().size();
		
		ServiceException exception = assertThrows(ServiceException.class, () -> {
	       accountService.getById(id);
	    });
		assertEquals("Account with ID: " + id + " not found", exception.getMessage());
	}

	@Test
	public void testGetByNib_ValidNib() {
		
		Account account = accountService.getAll().get(accountService.getAll().size() -1);
		account = accountService.getByNib(account.getNib());
		assertTrue(account.getNib() != null);
		assertTrue(account.getId() != null);
	}
	
	@Test
	public void testGetByNib_InvalidNib() {
		
		String nib = "1";
		
		ServiceException exception = assertThrows(ServiceException.class, () -> {
			accountService.getByNib(nib);
		});
		assertEquals("Account with NIB: " + nib + " not found", exception.getMessage());
	}

	@Test
	public void testDeleteById_ValidId() {
		
		Account account = new Account();
		Client client = new Client();
		client.setId(32);
		account.setPrimaryOwner(client);
		Account savedAccount = accountService.save(account);
		accountService.deleteById(savedAccount.getId());
	}
	
	@Test
	public void testDeleteById_InvalidId() {
		
		Account account = new Account();
		Client client = new Client();
		client.setId(32);
		account.setPrimaryOwner(client);
		Account savedAccount = accountService.save(account);
		
		ServiceException exception = assertThrows(ServiceException.class, () -> {
	        accountService.deleteById(savedAccount.getId() +1);
	    });
		assertEquals("Account with ID: " + (savedAccount.getId() +1) + " not found", exception.getMessage());
	}

}
