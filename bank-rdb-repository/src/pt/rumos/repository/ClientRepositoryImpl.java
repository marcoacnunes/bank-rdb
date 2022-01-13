package pt.rumos.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import pt.rumos.database.MySQL;
import pt.rumos.database.Operation;
import pt.rumos.model.Client;

public class ClientRepositoryImpl implements ClientRepository {
	
	@Override
	public Optional<Client> save(Client client) {
		if(client.getId() == null) {
			String sql = "INSERT INTO client (name, nif, password,"
					+ " date_of_birth, phone, mobile, email, occupation)"
					+ " VALUES ('" 	+ client.getName() 			+ "', '" 
									+ client.getNif() 			+ "', '" 
									+ client.getPassword() 		+ "', '"
									+ LocalDate.now() 			+ "', '" //to be fixed
									+ client.getPhone() 		+ "', '" 
									+ client.getMobile() 		+ "', '" 
									+ client.getEmail() 		+ "', '"
									+ client.getOccupation() 	+ "');";

			MySQL.execute(sql, Operation.INSERT);
			Integer id = MySQL.getMaxId("client");
			return findById(id);
			
		}else {
			return update(client);
		}
	}

	public Optional<Client> update(Client client) {
		String query = "UPDATE client SET "
					+ "name 			= '" + client.getName()		 	+ "', "
					+ "nif 				= '" + client.getNif()		 	+ "', "
					+ "password 		= '" + client.getPassword()		+ "', "
					+ "date_of_birth	= '" + client.getDateOfBirth() 	+ "', "
					+ "phone 			= '" + client.getPhone() 		+ "', "
					+ "mobile 			= '" + client.getMobile() 		+ "', "
					+ "email 			= '" + client.getEmail() 		+ "', "
					+ "occupation 		= '" + client.getOccupation() 	+ "' "
					+ "WHERE id 		=  " + client.getId()			+ ";";
				
		MySQL.execute(query, Operation.UPDATE);
		return findById(client.getId());
	}
	
	@Override
	public List<Client> findAll() {
		String query = "SELECT * FROM client;";
		ResultSet rs = MySQL.execute(query, Operation.SELECT);
		return extractList(rs);
	}
	
	@Override
	public Optional<Client> findById(Integer id) {
		String query = "SELECT * FROM client WHERE id =" + id + ";";
		ResultSet rs = MySQL.execute(query, Operation.SELECT);
		return extractObject(rs);
	}

	@Override
	public Optional<Client> findByNif(String nif) {
		String query = "SELECT * FROM client WHERE nif LIKE '" + nif + "';";
		ResultSet rs = MySQL.execute(query, Operation.SELECT);
		return extractObject(rs);
	}

	@Override
	public void deleteByNif(String nif) {
		String sql = "DELETE FROM client WHERE nif LIKE '" + nif + "';";
		MySQL.execute(sql, Operation.DELETE);
	}
	
	private List<Client> extractList(ResultSet rs) {
		List<Client> clients = new ArrayList<Client>();
		
		try {
			while (rs.next()) {
				Client client = buildObject(rs);
				clients.add(client);
			}
			return clients;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
	}

	private Optional<Client> extractObject(ResultSet rs) {
		try {
			if(rs.next()) {
				Client client = buildObject(rs);
				return Optional.of(client);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Optional.empty();
	}
	
	private Client buildObject(ResultSet rs) throws SQLException {
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
