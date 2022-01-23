package pt.rumos.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import pt.rumos.database.MySQL;
import pt.rumos.database.Operation;
import pt.rumos.model.Account;
import pt.rumos.model.Client;

public class AccountRepositorySQLImpl implements AccountRepository {
	
	@Override
	public Optional<Account> save(Account account) {
		if(account.getId() == null) {
			String queryInsertAccount = "INSERT INTO account (nib, primary_owner_id, balance)"
						+ " VALUES ('"	+ account.getNib() 						+ "', '" 
										+ account.getPrimaryOwner().getId() 	+ "', '" 
										+ account.getBalance() 					+ "');";
			
			MySQL.execute(queryInsertAccount, Operation.INSERT);
			Integer id = MySQL.getMaxId("account");
			
			for (Client secondary : account.getSecondaryOwners()) {
	            String queryInsertAccountClient = "INSERT INTO account_client (account_id, client_id) VALUES ("
						                    + "'" + id 					+ "',"
						                    + "'" + secondary.getId() 	+ "');";
	            
	            MySQL.execute(queryInsertAccountClient, Operation.INSERT);
			}
			return findById(id);
		
		} else {
			String queryUpdateAccount  = "UPDATE account SET "
		                    + "nib = '"         		+ account.getNib()              	+ "',"
		                    + "primary_owner_id = '"    + account.getPrimaryOwner().getId() + "',"
		                    + "balance = '"     		+ account.getBalance()          	+ "' "
		                    + "WHERE id = " 			+ account.getId() 					+ ";";
			
            MySQL.execute(queryUpdateAccount, Operation.UPDATE);
            Integer id = MySQL.getMaxId("account");
            
            String queryDeleteAllAccountClients = "DELETE FROM account_client WHERE account_id = " + account.getId() + ";";
            MySQL.execute(queryDeleteAllAccountClients, Operation.DELETE);
            
            for (Client secondary : account.getSecondaryOwners()) {
                String queryUpdateAccountClients = "INSERT INTO account_client (account_id, client_id) VALUES ("
							                        + "'" + account.getId() 	+ "',"
							                        + "'" + secondary.getId() 	+ "');";
                
                MySQL.execute(queryUpdateAccountClients, Operation.INSERT);
            }
            return findById(id);
        }
	}
	
	@Override
	public List<Client> findSecondaryClients(Integer accountId) {
		String query = "SELECT c.id, c.name, c.nif, c.password, c.date_of_birth, c.phone, c.mobile, c.email, c.occupation FROM account_client AS ac "
			+	"JOIN client AS c ON ac.client_id = c.id "
			+	"WHERE ac.account_id = " + accountId + ";";
		
		ResultSet rs = MySQL.execute(query, Operation.SELECT);
		
		try {
			return extractClientsOfAccount(rs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
	}
	
	@Override
	public List<Account> findAll() {
		String sql = "SELECT * FROM account;";
		ResultSet rs = MySQL.execute(sql, Operation.SELECT);
		return extractList(rs);
	}

	@Override
	public Optional<Account> findById(Integer id) {
		String sql = "Select * from account "
				+ "INNER JOIN client ON account.primary_owner_id = client.id "
				+ "where account.id = " + id + ";";
		ResultSet rs = MySQL.execute(sql, Operation.SELECT);
		
		try {
			if(rs.next()) {
				Account account = new Account();
				account.setId(rs.getInt(1));
				account.setNib(rs.getString(2));
				account.setBalance(rs.getDouble(4));
				Client primaryOwner = new Client();
				primaryOwner.setId(rs.getInt(5));
				primaryOwner.setName(rs.getString(6));
				primaryOwner.setNif(rs.getString(7));;
				primaryOwner.setPassword(rs.getString(8));;
				primaryOwner.setDateOfBirth(rs.getDate(9).toLocalDate());
				primaryOwner.setPhone(rs.getString(10));
				primaryOwner.setMobile(rs.getString(11));
				primaryOwner.setEmail(rs.getString(12));
				primaryOwner.setOccupation(rs.getString(13));
				account.setPrimaryOwner(primaryOwner);
				account.setSecondaryOwners(findSecondaryClients(id));
				return Optional.of(account);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Optional.empty();
	}

	@Override
	public Optional<Account> findByNib(String nib) {
		String sql = "SELECT * FROM account WHERE nib LIKE '" + nib + "';";
		ResultSet rs = MySQL.execute(sql, Operation.SELECT);
		return extractObject(rs);
	}

	@Override
	public void deleteById(Integer id) {
		String sql = "DELETE FROM account WHERE id LIKE '" + id + "';";
		MySQL.execute(sql, Operation.DELETE);
	}
	
	private List<Account> extractList(ResultSet rs) {
		List<Account> accounts = new ArrayList<Account>();
		
		try {
			while (rs.next()) {
				Account account = buildObject(rs);
				accounts.add(account);
			}
			return accounts;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
	}

	private Optional<Account> extractObject(ResultSet rs) {
		try {
			if(rs.next()) {
				Account account = buildObject(rs);
				return Optional.of(account);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Optional.empty();
	}
	
	private Account buildObject(ResultSet rs) throws SQLException {
		Account account = new Account();
		account.setId(rs.getInt(1));
		account.setNib(rs.getString(2));
		Client client = new Client();
		client.setId(rs.getInt(3));
		account.setPrimaryOwner(client);
		account.setBalance(rs.getDouble(4));
		return account;
	}
	
	private List<Client> extractClientsOfAccount(ResultSet rs) throws SQLException {
		List<Client> clients = new ArrayList<Client>();
		
		try {
			while (rs.next()) {
				Client client = buildClient(rs);
				clients.add(client);
			}
			return clients;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
	}

	private Client buildClient(ResultSet rs) throws SQLException {
		Client client = new Client();
		client.setId(rs.getInt(1));
		client.setName(rs.getString(2));
		client.setNif(rs.getString(3));
		client.setPassword(rs.getString(4));
		client.setDateOfBirth(rs.getDate(5).toLocalDate());
		client.setPhone(rs.getString(6));
		client.setMobile(rs.getString(7));
		client.setEmail(rs.getString(8));
		client.setOccupation(rs.getString(9));
		return client;
	}
}

