package pt.rumos.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pt.rumos.model.Client;
import pt.rumos.service.ClientService;
import pt.rumos.service.ClientServiceImpl;

@RestController

public class ClientController {

	private ClientService clientService = new ClientServiceImpl();

	@RequestMapping(value = "/client", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Client> getAll() throws Exception {
		return clientService.getAll();
	}

	@RequestMapping(value = "/client/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Client getById(@PathVariable Integer id) throws Exception {
		return clientService.getById(id);
	}

	@RequestMapping(value = "/client", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Client createClient(@RequestBody Client client) throws Exception {
		return clientService.save(client);
	}

	@RequestMapping(value = "/client/{nif}", method = RequestMethod.DELETE)
	public void deleteClient(@PathVariable String nif) throws Exception {
		clientService.deleteByNif(nif);
	}
}
