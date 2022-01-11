package pt.rumos.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQL {
	
	private static final String URL = "jdbc:mysql://localhost:3306/rdb";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "pa$$w0rd";
	private static Connection connection;
	private static Statement stmt;
	
	private static void init() {
		
		try {
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			stmt = connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static int getMaxId(String tableName) {
		
		try {
			ResultSet rs = execute("SELECT max(id) FROM " + tableName + ";", Operation.SELECT);
			
			if(rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 1;
	}
	
	public static ResultSet execute(String query, Operation operation) {
		
		if(connection == null) {
			init();
		}
		
		try {
			switch (operation) {
				case SELECT:
					stmt.execute(query);
					return stmt.getResultSet();
				case INSERT:
					stmt.executeUpdate(query);
					return stmt.getResultSet();
				case UPDATE:
					stmt.executeUpdate(query);
					return stmt.getResultSet();
				case DELETE:
					stmt.executeUpdate(query);
					return stmt.getResultSet();
				default:
					break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		throw new RuntimeException("Sql statement not executed!");
	}

}
