package conn;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class MySqlConnUtils {
	public static Connection getMySqlConnection() throws ClassNotFoundException, SQLException {
		String hostName = "localhost";
		String dbName = "db-simple-web";
		String userName = "root";
		String password = "1098";
		return getMySqlConnection(hostName, dbName, userName, password);
	}
	
	public static Connection getMySqlConnection(String hostName, String dbName, String userName, String password) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://"+hostName+":3306/" + dbName;
		Connection conn = (Connection) DriverManager.getConnection(url, userName, password);
		return conn;
	}
}
