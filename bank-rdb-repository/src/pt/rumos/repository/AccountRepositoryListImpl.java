package pt.rumos.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import pt.rumos.model.Account;

public class AccountRepositoryListImpl implements AccountRepository {

    private List<Account> accounts = new ArrayList<Account>();
    private static int id = 1;
    
    @Override
    public Optional<Account> save(Account account) {
    	
		account.setId(id);
		id++;
		account.setNib(generateAndCheckIfUniqueNib());
		System.out.println("Account NIB is: " + account.getNib());
		accounts.add(account);
			
		return Optional.of(account);
    }
    
	private String generateAndCheckIfUniqueNib() {
		
		Random random = new Random();
		String nib = String.format("%06d", random.nextInt(1000000));

		boolean nibExists = false;
		
		do {
			for(Account account : findAll()) {
				
				if(account.getNib().equals(nib)){
					nibExists = true;
				}
			}	
		}while(nibExists);
		return nib;
	}

	@Override
    public List<Account> findAll() {
    	return accounts;
    }

    @Override
    public Optional<Account> findById(Integer id) {
		
    	for (Account account : accounts) {
		    
			if (account.getId().equals(id)) {
				return Optional.of(account);
			}
		}	
    	return Optional.empty();
    }

    @Override
    public Optional<Account> findByNib(String nib) {
    	
    	for (Account account : accounts) {
		    
			if (account.getNib().equals(nib)) {
				return Optional.of(account);
			}
		}
    	return Optional.empty();
    }

    @Override
    public void deleteById(Integer id) {
    	
    	Optional<Account> account = findById(id);
			
		if(account.isPresent()) {
			accounts.remove(account.get());
		}
    }
}

