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
	
	private MySQL mySQL = new MySQL();

	@Override
	public Optional<Card> save(Card card, Integer accountId, Integer clientId) {
		
		CreditCard creditCard = null;
		DebitCard debitCard = null;
		String sql = null;
		
		if(card.getClass().equals(CreditCard.class)) {
			
			creditCard = (CreditCard) card;
			sql = "INSERT INTO card (plafond, daily_withdrawals) VALUES ('"	+ creditCard.getPlafond() 			+ "', '" 
																			+ creditCard.getDailyWithdrawals() 	+ "', ');";
			mySQL.execute(sql, Operation.INSERT);

		}
		
		if(card.getClass().equals(DebitCard.class)) {
			
			debitCard = (DebitCard) card;
			sql = "INSERT INTO card (last_withdrawal) VALUES ('" + debitCard.getLastWithdrawal() + "');";
			mySQL.execute(sql, Operation.INSERT);
		}
		
		sql = "INSERT INTO card (client_id, pin, account_id) VALUES ('"	+ card.getClientId() 	+ "', '" 
																		+ card.getPin() 		+ "', '"
																		+ card.getAccountId() 	+ "');";
	
		mySQL.execute(sql, Operation.INSERT);
		Integer id = mySQL.getMaxId("card");
		return findById(id);
	}

	@Override
	public List<Card> findAll() {
		
		String sql = "SELECT * FROM card;";
		ResultSet rs = mySQL.execute(sql, Operation.SELECT);
		return extractList(rs);
	}

	@Override
	public Optional<Card> findById(Integer id) {
		
		String sql = "SELECT * FROM card WHERE id LIKE " + id + ";";
		ResultSet rs = mySQL.execute(sql, Operation.SELECT);
		return extractObject(rs);
	}

	@Override
	public Optional<Card> findByClientId(Integer clientId) {
		
		String sql = "SELECT * FROM card WHERE clientId LIKE " + clientId + ";";
		ResultSet rs = mySQL.execute(sql, Operation.SELECT);
		return extractObject(rs);
	}

	@Override
	public Optional<Card> findByAccountId(Integer accountId) {
		
		String sql = "SELECT * FROM card WHERE accountId LIKE " + accountId + ";";
		ResultSet rs = mySQL.execute(sql, Operation.SELECT);
		return extractObject(rs);
	}

	@Override
	public void deleteById(Integer id) {
		
		String sql = "DELETE FROM card WHERE id LIKE '" + id + "';";
		mySQL.execute(sql, Operation.DELETE);		
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

		Card card = new Card();
		card.setId(rs.getInt(1));
		card.setClientId(rs.getInt(2));
		card.setPin(rs.getString(3));
		card.setAccountId(rs.getInt(4));
		return card;
	}
}
