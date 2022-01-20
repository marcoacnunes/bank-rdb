package pt.rumos.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import pt.rumos.exception.ServiceException;
import pt.rumos.model.Client;

class ClientServiceImplTest {
	
	private ClientService clientService = new ClientServiceImpl();

//	@Test
//	public void testSave() {
//		
//		Client client = new Client();
//		client.setName("carlos");
//		client.setNif("123456789");
//		client.setPassword("password");
//		client.setDateOfBirth(LocalDate.parse("2020-01-01"));
//		client.setPhone("123456789");
//		client.setMobile("123456789");
//		client.setEmail("carlos@gmail.com");
//		client.setOccupation("student");
//		
//		client = clientService.save(client);
//		
//		assertTrue(client.getId() != null);
//	}
//	
//	@Test
//	public void testSave_NullName() {
//		
//		Client client = new Client();
//		ServiceException exception = assertThrows(ServiceException.class, () -> {
//		       clientService.save(client);
//		});
//		
//		assertEquals("Client name cannot be null", exception.getMessage());
//	}
//	
//	@Test
//	public void testSave_NullNif() {
//		
//		Client client = new Client();
//		client.setName("carlos");
//		client.setPassword("password");
//		client.setDateOfBirth(LocalDate.parse("2020-01-01"));
//		client.setPhone("123456789");
//		client.setMobile("123456789");
//		client.setEmail("carlos@gmail.com");
//		client.setOccupation("student");
//		
//		ServiceException exception = assertThrows(ServiceException.class, () -> {
//		       clientService.save(client);
//		});
//		
//		assertEquals("Client nif cannot be null", exception.getMessage());
//	}
//	
//	@Test
//	public void testSave_NullPassword() {
//		
//		Client client = new Client();
//		client.setName("carlos");
//		client.setNif("123456789");
//		client.setDateOfBirth(LocalDate.parse("2020-01-01"));
//		client.setPhone("123456789");
//		client.setMobile("123456789");
//		client.setEmail("carlos@gmail.com");
//		client.setOccupation("student");
//		
//		ServiceException exception = assertThrows(ServiceException.class, () -> {
//		       clientService.save(client);
//		});
//		
//		assertEquals("Client password cannot be null", exception.getMessage());
//	}
//	
//	@Test
//	public void testSave_NullDateOfBirth() {
//		
//		Client client = new Client();
//		client.setName("carlos");
//		client.setNif("123456789");
//		client.setPassword("password");
//		client.setPhone("123456789");
//		client.setMobile("123456789");
//		client.setEmail("carlos@gmail.com");
//		client.setOccupation("student");
//		
//		ServiceException exception = assertThrows(ServiceException.class, () -> {
//		       clientService.save(client);
//		});
//		
//		assertEquals("Client date of birth cannot be null", exception.getMessage());
//	}
//	
//	@Test
//	public void testSave_NullPhone() {
//		
//		Client client = new Client();
//		client.setName("carlos");
//		client.setNif("123456789");
//		client.setPassword("password");
//		client.setDateOfBirth(LocalDate.parse("2020-01-01"));
//		client.setMobile("123456789");
//		client.setEmail("carlos@gmail.com");
//		client.setOccupation("student");
//		
//		ServiceException exception = assertThrows(ServiceException.class, () -> {
//		       clientService.save(client);
//		});
//		
//		assertEquals("Client phone cannot be null", exception.getMessage());
//	}
//	
//	@Test
//	public void testSave_NullMobile() {
//		
//		Client client = new Client();
//		client.setName("carlos");
//		client.setNif("123456789");
//		client.setPassword("password");
//		client.setDateOfBirth(LocalDate.parse("2020-01-01"));
//		client.setPhone("123456789");
//		client.setEmail("carlos@gmail.com");
//		client.setOccupation("student");
//		
//		ServiceException exception = assertThrows(ServiceException.class, () -> {
//		       clientService.save(client);
//		});
//		
//		assertEquals("Client mobile cannot be null", exception.getMessage());
//	}
//	
//	@Test
//	public void testSave_NullEmail() {
//		
//		Client client = new Client();
//		client.setName("carlos");
//		client.setNif("123456789");
//		client.setPassword("password");
//		client.setDateOfBirth(LocalDate.parse("2020-01-01"));
//		client.setPhone("123456789");
//		client.setMobile("123456789");
//		client.setOccupation("student");
//		
//		ServiceException exception = assertThrows(ServiceException.class, () -> {
//		       clientService.save(client);
//		});
//		
//		assertEquals("Client email cannot be null", exception.getMessage());
//	}
//	
//	@Test
//	public void testSave_NullOccupation() {
//		
//		Client client = new Client();
//		client.setName("carlos");
//		client.setNif("123456789");
//		client.setPassword("password");
//		client.setDateOfBirth(LocalDate.parse("2020-01-01"));
//		client.setPhone("123456789");
//		client.setMobile("123456789");
//		client.setEmail("carlos@gmail.com");
//		
//		ServiceException exception = assertThrows(ServiceException.class, () -> {
//		       clientService.save(client);
//		});
//		
//		assertEquals("Client occupation cannot be null", exception.getMessage());
//	}
//	
//	
//*****
//
//	@Test
//	public void testGetAll_NotEmpty() {
//		
//		List<Client> clients = clientService.getAll();
//		assertTrue(clients != null);
//		assertTrue(!clients.isEmpty());
//	}
//	
//*****	
//
//	@Test
//	public void testGetById_ValidId() {
//		
//		Client client = clientService.getAll().get(clientService.getAll().size() -1);
//		assertTrue(client.getId() != null);
//	}
	
//	@Test
//	public void testGetById_InvalidId() {
//		
//		Integer id = clientService.getAll().size();
//		
//		ServiceException exception = assertThrows(ServiceException.class, () -> {
//	       clientService.getById(id);
//	    });
//		assertEquals("Client with ID: " + id + " not found.", exception.getMessage());
//	}
//
//	@Test
//	void testGetByNif() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testDeleteByNif() {
//		fail("Not yet implemented");
//	}

}
