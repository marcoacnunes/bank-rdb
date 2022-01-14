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
//		if(account.getId() == null) {
			account.setNib(generateAndCheckIfUniqueNib());
//		}
			
		if(account.getSecondaryOwners().size() > 4) throw new ServiceException("Limit Number of Secondary Clients has been reached.");
		return accountRepository.save(account).orElseThrow(() -> new ServiceException("There was a problem while saving Account."));
	}
	
    @Override
    public List<Client> getSecondaryClients(Integer accountId) {
    	return accountRepository.findSecondaryClients(accountId);
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
