package pt.rumos.service;

import java.util.List;
import java.util.Random;

import pt.rumos.exception.ServiceException;
import pt.rumos.model.Account;
import pt.rumos.model.Client;
import pt.rumos.repository.AccountRepository;
import pt.rumos.repository.AccountRepositoryImpl;

public class AccountServiceImpl implements AccountService{
	
	AccountRepository accountRepository = new AccountRepositoryImpl();

	@Override
	public Account save(Account account) {
		account.setNib(generateAndCheckIfUniqueNib());
		return accountRepository.save(account).orElseThrow(() -> new ServiceException("There was a problem while saving Account."));
	}
	
    @Override
    public Client saveAccountClient(Client client, Account account) {
    	return accountRepository.saveAccountClient(client, account).orElseThrow(() -> new ServiceException("There was a problem saving Client"));
    }
    
    @Override
    public List<Client> getAccountClients(Integer accountId) {
    	return accountRepository.findAccountClients(accountId);
    }
	
	@Override
	public List<Account> getAll() {
		return accountRepository.findAll();
	}

	@Override
	public Account getById(Integer id) {
		return accountRepository.findById(id).orElseThrow(() -> new ServiceException("Account with ID: " + id + " not found"));
	}

	@Override
	public Account getByNib(String nib) {
		return accountRepository.findByNib(nib).orElseThrow(() -> new ServiceException("Account with NIB: " + nib + " not found"));
	}

	@Override
	public void deleteById(Integer id) {
		accountRepository.deleteById(id);
	}

	private String generateAndCheckIfUniqueNib() {
		
		Random random = new Random();
		String nib = String.format("%06d", random.nextInt(1000000));

		boolean nibExists = false;
		
		do {
			for(Account account : accountRepository.findAll()) {
				
				if(account.getNib().equals(nib)){
					nibExists = true;
				}
			}	
		}while(nibExists);
		return nib;
	}

}
