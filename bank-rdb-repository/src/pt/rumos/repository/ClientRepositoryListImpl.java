package pt.rumos.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import pt.rumos.model.Account;
import pt.rumos.model.Client;

public class ClientRepositoryListImpl implements ClientRepository {

	private List<Client> clients = new ArrayList<Client>();
	private static int id = 1;

	@Override
	public Optional<Client> save(Client client) {

		if(client.getId() != null) {
			return update(client);
		}else {
			return create(client);
		}
	}
	
	private Optional<Client> create(Client client) {
		
		clients.add(client);
		client.setId(id);
		id++;
		
		return Optional.of(client);
	}
	
	@Override
	public Optional<Client> saveAccountClient(Client client, Account account) {
		
		account.getSecondaryOwnersId().add(client.getId());
		return Optional.of(client);
	}

	private Optional<Client> update(Client client) { 				//to be tested
		Optional<Client> clientOpt = findByNif(client.getNif());
		
		Client existingClient = clientOpt.get();
		existingClient = client;
		
		
		
		return Optional.of(existingClient);
	}

	@Override
	public List<Client> findAll() {
		return clients;
	}

	@Override
	public List<Client> findAccountClients(Integer accountId) {
		return clients;
	}
	
	@Override
	public Optional<Client> findById(Integer id) {

		for (Client client : clients) {

			if (client.getId().equals(id)) {
				return Optional.of(client);
			}
		}
		return Optional.empty();
	}

	@Override
	public Optional<Client> findByNif(String nif) {

		for (Client client : clients) {

			if (client.getNif().equals(nif)) {
				return Optional.of(client);
			}
		}
		return Optional.empty();
	}

	@Override
	public void deleteByNif(String nif) {
	
		Optional<Client> client = findByNif(nif);
		
		if(client.isPresent()) {
			clients.remove(client.get());
		}
	}


}
