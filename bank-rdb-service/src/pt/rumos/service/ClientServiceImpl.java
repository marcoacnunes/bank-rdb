package pt.rumos.service;

import java.util.List;

import pt.rumos.exception.ServiceException;
import pt.rumos.model.Client;
import pt.rumos.repository.ClientRepository;
import pt.rumos.repository.ClientRepositoryImpl;

public class ClientServiceImpl implements ClientService {

    private ClientRepository clientRepository = new ClientRepositoryImpl();

    @Override
    public Client save(Client client) {
    	
    	if (client.getName() == null || client.getName().isBlank()) throw new ServiceException("Client name cannot be null");
    	if (client.getNif() == null || client.getNif().isBlank()) throw new ServiceException("Client nif cannot be null");
//    	if (client.getPassword() == null || client.getPassword().isBlank()) throw new ServiceException("Client password cannot be null");
//    	if (client.getDob() == null) throw new ServiceException("Client date of birth cannot be null");
//    	if (client.getPhone() == null || client.getPhone().isBlank()) throw new ServiceException("Client phone cannot be null");
//    	if (client.getMobile() == null || client.getMobile().isBlank()) throw new ServiceException("Client mobile cannot be null");
//    	if (client.getEmail() == null || client.getEmail().isBlank()) throw new ServiceException("Client email cannot be null");
//    	if (client.getOccupation() == null || client.getOccupation().isBlank()) throw new ServiceException("Client occupation cannot be null");

    	return clientRepository.save(client).orElseThrow(() -> new ServiceException("There was a problem while saving Client."));
    }
    
    @Override
    public List<Client> getAll() {
    	return clientRepository.findAll();
    }
    
    @Override
    public Client getById(Integer id) {
    	return clientRepository.findById(id).orElseThrow(() -> new ServiceException("Client with ID: " + id + " not found."));
    }

    @Override
    public Client getByNif(String nif) {
    	return clientRepository.findByNif(nif).orElseThrow(() -> new ServiceException("Client with NIF: " + nif + " not found."));
    }

    @Override
    public void deleteByNif(String nif) {
    	clientRepository.deleteByNif(nif);
    }


}
