package com.dodo.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DBUtils {
	private static ResourceBundle rBundle = ResourceBundle.getBundle("Connection");
	public static Connection getConnection() {
		
		Connection conn = null;
		try {
			Class.forName(rBundle.getString("driver"));
			String url = rBundle.getString("url");
			String user = rBundle.getString("user");
			String pass = rBundle.getString("pass");
			conn = DriverManager.getConnection(url, user, pass);
			conn.setAutoCommit(false);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn = null;
		}
		return conn;
	}
	
	public static void closeConnection(Connection conn, PreparedStatement ps, ResultSet rs) {
		try {
			if (conn != null) conn.close();
			if (ps != null)	ps.close();
			if (rs != null) rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void rollback(Connection conn) {
		try {
			conn.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
