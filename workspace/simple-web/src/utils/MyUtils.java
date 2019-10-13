package utils;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.Connection;

import bean.UserAccount;

public class MyUtils {
	public static final String ATT_CONNECTION = "ATTRIBUTE_FOR_CONNECTION";
	public static final String ATT_LOGINED_USER = "loginedUser";
	private static final String ATT_USER_NAME = "ATTRIBUTE_FOR_STORE_USERNAME_IN_COOKIE";
	public static void storeConnection(ServletRequest request, Connection conn) {
		request.setAttribute(ATT_CONNECTION, conn);
	}
	
	public static Connection getStoredConnection(ServletRequest request) {
		return (Connection) request.getAttribute(ATT_CONNECTION);
	}
	
	public static void storeLoginedUser(HttpSession session, UserAccount userAccount) {
		session.setAttribute(ATT_LOGINED_USER, userAccount);
	}
	
	public static UserAccount getLoginedUser(HttpSession session) {
		return (UserAccount) session.getAttribute(ATT_LOGINED_USER);
	}
	
	public static void storeUserCookie(HttpServletResponse response, UserAccount userAccount) { //Luu user name vao cookie
		System.out.println("Store user cookie");
		Cookie cookie = new Cookie(ATT_USER_NAME, userAccount.getUserName());
		cookie.setMaxAge(60*60*24);
		response.addCookie(cookie);
	}
	
	public static String getUserNameInCookie(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if(cookies!=null) {
			for(Cookie cookie : cookies) {
				if(ATT_USER_NAME.equals(cookie.getName())) {
					return cookie.getValue();
				}
			}
		}
		return null;
	}
	
	public static void deleteUserNameInCookie(HttpServletResponse response) {
		Cookie cookie = new Cookie(ATT_USER_NAME, null);
		cookie.setMaxAge(0);
		response.addCookie(cookie);
	}
}
