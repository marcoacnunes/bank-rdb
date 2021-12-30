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

public class AccountRepositoryImpl implements AccountRepository {

	@Override
	public Optional<Account> save(Account account) {
		
		String query = "INSERT INTO account (nib, primary_owner_id, balance)"
					+ " VALUES ('"	+ account.getNib() 				+ "', '" 
									+ account.getPrimaryOwnerId() 	+ "', '" 
									+ account.getBalance() 			+ "');";
		
		MySQL.execute(query, Operation.INSERT);
		Integer id = MySQL.getMaxId("account");
		return findById(id);
	}
	
	@Override
	public List<Account> findAll() {
		
		String sql = "SELECT * FROM account;";
		ResultSet rs = MySQL.execute(sql, Operation.SELECT);
		return extractList(rs);
	}

	@Override
	public Optional<Account> findById(Integer id) {
		
		String sql = "SELECT * FROM account WHERE id = " + id + ";";
		ResultSet rs = MySQL.execute(sql, Operation.SELECT);
		return extractObject(rs);
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
		account.setPrimaryOwnerId(rs.getInt(3));
		account.setBalance(rs.getDouble(4));
		return account;
	}

}

