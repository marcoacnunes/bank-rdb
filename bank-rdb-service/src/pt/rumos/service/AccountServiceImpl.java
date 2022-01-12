package pt.rumos.service;

import java.util.List;
import java.util.Optional;
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
    public Client saveSecondaryClient(Account account) {
    	
    	List<Client> secondaryClients = getSecondaryClients(account);
    	if((secondaryClients.size() -1) == 4) throw new ServiceException("Number of Secondary Clients of this Account has been reached!");
    	
    	return accountRepository.saveSecondaryClient(account).orElseThrow(() -> new ServiceException("There was a problem saving Client"));
    }
    
    @Override
    public List<Client> getSecondaryClients(Account account) {
    	return accountRepository.findSecondaryClients(account);
    }
	
	@Override
	public List<Account> getAll() {
		return accountRepository.findAll();
	}

	@Override
	public Account getById(Integer id) {
		Account acc = accountRepository.findById(id).orElseThrow(() -> new ServiceException("Account with ID: " + id + " not found"));
		acc.getPrimaryOwner().getName();
		return acc;
	}

	@Override
	public Account getByNib(String nib) {
		return accountRepository.findByNib(nib).orElseThrow(() -> new ServiceException("Account with NIB: " + nib + " not found"));
	}

	@Override
	public void deleteById(Integer id) {
		getById(id);
		accountRepository.deleteById(id);
	}

	private String generateAndCheckIfUniqueNib() {
		
		boolean nibExists = false;
		String nib;
		
		do {
			Random random = new Random();
			nib = String.format("%06d", random.nextInt(1000000));
			
			Optional<Account> existingAccount = accountRepository.findByNib(nib);
			
			if(existingAccount.isPresent()) {
				nibExists = true;
			}
		} while (nibExists);

		return nib;
	}
}
