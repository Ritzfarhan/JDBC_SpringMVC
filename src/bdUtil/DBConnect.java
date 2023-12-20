package bdUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
	public static Connection openConnection() { 
		Connection conn = null;
	
		String dbURL = "jdbc:mysql://localhost:3306/ip23db";
		String username = "root"; 
		String password = "";
				
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				conn = DriverManager.getConnection (dbURL, username, password);
			} 
			catch (SQLException ex) {
				
			} 
			catch (ClassNotFoundException ex) {
				ex.printStackTrace();
			}
			
			return conn;
	}
}
