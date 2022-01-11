package pt.rumos.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import pt.rumos.database.MySQL;
import pt.rumos.database.Operation;
import pt.rumos.model.Card;
import pt.rumos.model.CreditCard;
import pt.rumos.model.DebitCard;

public class CardRepositoryImpl implements CardRepository {


	@Override
	public Optional<Card> save(Card card) {
		
		CreditCard creditCard = null;
		DebitCard debitCard = null;
		String sql = null;
		
		if(card.getClass().equals(CreditCard.class)) {
			
			creditCard = (CreditCard) card;
			sql = "INSERT INTO card (client_id, pin, account_id, plafond, daily_withdrawals) VALUES ('"	+ card.getClient().getId() 			+ "', '" 
																										+ card.getPin() 					+ "', '"
																										+ card.getAccount().getId() 		+ "', '"
																										+ creditCard.getPlafond() 			+ "', '"		
																										+ creditCard.getDailyWithdrawals() 	+ "');";
			MySQL.execute(sql, Operation.INSERT);
			Integer id = MySQL.getMaxId("client");
			return findById(id);
		}
		
		if(card.getClass().equals(DebitCard.class)) {
			
			debitCard = (DebitCard) card;
			sql = "INSERT INTO card (client_id, pin, account_id, last_withdrawal) VALUES ('"	+ card.getClient().getId() 			+ "', '" 
																								+ card.getPin() 					+ "', '"
																								+ card.getAccount().getId() 		+ "', '"
																								+ debitCard.getLastWithdrawal() 	+ "');";
			MySQL.execute(sql, Operation.INSERT);
			Integer id = MySQL.getMaxId("client");
			return findById(id);
		}
		return Optional.empty();
	}

	@Override
	public List<Card> findAll() {
		
		String sql = "SELECT * FROM card;";
		ResultSet rs = MySQL.execute(sql, Operation.SELECT);
		return extractList(rs);
	}

	@Override
	public Optional<Card> findById(Integer id) {
		
		String sql = "SELECT * FROM card WHERE id LIKE " + id + ";";
		ResultSet rs = MySQL.execute(sql, Operation.SELECT);
		return extractObject(rs);
	}

	@Override
	public Optional<Card> findByClientId(Integer clientId) {
		
		String sql = "SELECT * FROM card WHERE clientId LIKE " + clientId + ";";
		ResultSet rs = MySQL.execute(sql, Operation.SELECT);
		return extractObject(rs);
	}

	@Override
	public Optional<Card> findByAccountId(Integer accountId) {
		
		String sql = "SELECT * FROM card WHERE accountId LIKE " + accountId + ";";
		ResultSet rs = MySQL.execute(sql, Operation.SELECT);
		return extractObject(rs);
	}

	@Override
	public void deleteById(Integer id) {
		
		String sql = "DELETE FROM card WHERE id LIKE '" + id + "';";
		MySQL.execute(sql, Operation.DELETE);		
	}
	
	private List<Card> extractList(ResultSet rs) {
		
		List<Card> cards = new ArrayList<Card>();
		
		try {
			while (rs.next()) {
				Card card = buildObject(rs);
				cards.add(card);
			}
			return cards;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
	}
	
	private Optional<Card> extractObject(ResultSet rs) {

		try {
			if(rs.next()) {
				Card card = buildObject(rs);
				return Optional.of(card);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Optional.empty();
	}

	
	private Card buildObject(ResultSet rs) throws SQLException {
		
		Optional<Card> cardOptional = findById(rs.getInt(1));
		Card card = cardOptional.get();
		
		card.setId(card.getId());
		card.setClient(card.getClient());
		card.setPin(card.getPin());
		card.setAccount(card.getAccount());
		return card;
	}
}
