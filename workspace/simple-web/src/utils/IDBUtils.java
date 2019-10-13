package utils;

import java.util.List;

import com.mysql.jdbc.Connection;

import bean.Product;
import bean.UserAccount;

public interface IDBUtils {
	
	UserAccount 	findUser(Connection conn, String userName, String password);
	UserAccount 	findUser(Connection conn, String userName);
	
	List<Product> 	queryProduct(Connection conn);
	
	Product 		findProduct(Connection conn, String code);
	
	void 			updateProduct(Connection conn, Product product);
	
	void 			insertProduct(Connection conn, Product product);
	
	Product 		deleteProduct(Connection conn, String code);
}
