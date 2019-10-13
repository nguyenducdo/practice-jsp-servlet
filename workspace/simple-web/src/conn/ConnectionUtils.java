package conn;

import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class ConnectionUtils {
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		return MySqlConnUtils.getMySqlConnection();
	}
	
	public static void closeQuietly(Connection conn) {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void rollBackQuietly(Connection conn) {
		try {
			conn.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
