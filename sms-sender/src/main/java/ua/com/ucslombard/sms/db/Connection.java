package ua.com.ucslombard.sms.db;

import java.sql.*;

public class Connection {
	
	private static java.sql.Connection connection = null;
	
	public static java.sql.Connection getConnection() {
		if (connection == null) {
			try {
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			} catch(Exception e) {
				e.printStackTrace();
			}
			
			try {
				connection = DriverManager.getConnection("jdbc:odbc:LO","","");
			} catch(Exception e) { e.printStackTrace(); }
		}
		return connection;
	}

}
