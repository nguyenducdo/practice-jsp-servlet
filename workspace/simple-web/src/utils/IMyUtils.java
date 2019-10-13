package utils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.Connection;

import bean.UserAccount;

public interface IMyUtils {
	
	void			storeConnection(ServletRequest request, Connection conn);
	
	Connection		getStoredConnection(ServletRequest request);
	
	void 			storeLoginedUser(HttpSession session, UserAccount userAccount);
	
	UserAccount 	getLoginedUser(HttpSession session);
	
	void 			storeUserCookie(ServletResponse response, UserAccount userAccount); //store userName
	
	String 			getUserNameInCookie(ServletRequest request);
	
	void 			deleteUserInCookie(ServletResponse response);
}
