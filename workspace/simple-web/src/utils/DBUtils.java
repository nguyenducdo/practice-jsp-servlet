package utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import bean.Product;
import bean.UserAccount;

public class DBUtils {
	public static UserAccount findUser(Connection conn, String userName, String password) throws SQLException {
		String query = "select user_name, gender, password from user_account where user_name = ? and password = ? ";
		PreparedStatement ps = (PreparedStatement) conn.prepareStatement(query);
		ps.setString(1, userName);
		ps.setString(2, password);
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			UserAccount userAccount = new UserAccount();
			userAccount.setUserName(rs.getString("user_name"));
			userAccount.setPassword(rs.getString("password"));
			userAccount.setGender(rs.getString("gender"));
			return userAccount;
		}
		return null;
	}
	
	public static UserAccount findUser(Connection conn, String userName) throws SQLException {
		String query = "select user_name, gender, password from user_account where user_name = ?";
		PreparedStatement ps = (PreparedStatement) conn.prepareStatement(query);
		ps.setString(1, userName);
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			UserAccount userAccount = new UserAccount();
			userAccount.setUserName(rs.getString("user_name"));
			userAccount.setPassword(rs.getString("password"));
			userAccount.setGender(rs.getString("gender"));
			return userAccount;
		}
		return null;
	}
	
	public static List<Product> queryProducts(Connection conn) throws SQLException{
		String query = "select code, name, price from product";
		PreparedStatement ps = (PreparedStatement) conn.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		List<Product> productsList = new ArrayList<Product>();
		while(rs.next()) {
			String code = rs.getString("code");
			String name = rs.getString("name");
			float price = rs.getFloat("price");
			Product product = new Product(code, name, price);
			productsList.add(product);
		}
		return productsList;
	}
	
	public static Product findProduct(Connection conn, String code) throws SQLException {
		String sql = "Select a.Code, a.Name, a.Price from Product a where a.Code=?";
		 
        PreparedStatement pstm = (PreparedStatement) conn.prepareStatement(sql);
        pstm.setString(1, code);
 
        ResultSet rs = pstm.executeQuery();
 
        while (rs.next()) {
            String name = rs.getString("Name");
            float price = rs.getFloat("Price");
            Product product = new Product(code, name, price);
            return product;
        }
        return null;
	}
	
	public static void updateProduct(Connection conn, Product product) throws SQLException {
		String query = "update product set name=?, price=? where code=?";
		PreparedStatement ps = (PreparedStatement) conn.prepareStatement(query);
		ps.setString(1, product.getName());
		ps.setFloat(2, product.getPrice());
		ps.setString(3, product.getCode());
		ps.executeUpdate();
		
	}
	
	public static void insertProduct(Connection conn, Product product) throws SQLException {
		String query = "insert into product(code,name,price) values(?,?,?)";
		PreparedStatement ps = (PreparedStatement) conn.prepareStatement(query);
		ps.setString(1, product.getCode());
		ps.setString(2, product.getName());
		ps.setFloat(3, product.getPrice());
		ps.executeUpdate();
	}
	
	public static void deleteProduct(Connection conn, String code) throws SQLException {
		String query = "delete from product where code=?";
		PreparedStatement ps = (PreparedStatement) conn.prepareStatement(query);
		ps.setString(1, code);
		ps.executeUpdate();
	}
}
