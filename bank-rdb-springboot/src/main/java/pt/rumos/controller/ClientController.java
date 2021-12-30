package pt.rumos.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pt.rumos.model.Client;
import pt.rumos.service.ClientService;
import pt.rumos.service.ClientServiceImpl;

@RestController
public class ClientController {

	private ClientService clientService = new ClientServiceImpl();
	
	@RequestMapping(value="/client", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Client> getAll() throws Exception {
		return clientService.getAll();
	}
	
	
}
