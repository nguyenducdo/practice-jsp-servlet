package dao;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class DBUtils {
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		String dbName = "db-simple-web";
		return getConnection(dbName);
	}
	
	public static Connection getConnection(String dbName) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		String hostName = "localhost";
		String userName = "root";
		String pass = "1098";
		Connection conn = null;
		conn = (Connection) DriverManager.getConnection("jdbc:mysql://"+hostName+":3306/" + dbName, userName, pass);
		return conn;
	}
	
	public static void closeConnection(Connection conn) {
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
