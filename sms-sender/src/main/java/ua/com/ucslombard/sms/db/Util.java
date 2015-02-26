package ua.com.ucslombard.sms.db;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Util {
	
	public static List<Map<String, Object>> convertResultSet2List(ResultSet resultSet) {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		
		ResultSetMetaData metaData;
		
		try {
			metaData = resultSet.getMetaData();
			int colCount = metaData.getColumnCount();
			 
			while (resultSet.next()) {
				Map<String, Object> columns = new HashMap<String, Object>();
				
				for (int i = 1; i <= colCount; i++) {
					columns.put(metaData.getColumnLabel(i), resultSet.getObject(i));
				}
				 
				result.add(columns);
			} 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
}
