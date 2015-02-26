package ua.com.ucslombard.sms.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Request {
	
	public static ResultSet get() {
		Statement statement = null;
		ResultSet resultSet = null;
		
		try {
			statement = Connection.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			resultSet = statement.executeQuery(Quaries.SELECT_DATA);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return resultSet;
	}
}
