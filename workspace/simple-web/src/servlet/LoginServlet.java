package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;

import bean.UserAccount;
import utils.DBUtils;
import utils.MyUtils;

@WebServlet(urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/loginView.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userName = req.getParameter("userName");
		String password = req.getParameter("password");
		boolean remember = "Y".equals(req.getParameter("rememberMe"));
		
		UserAccount user = null;
		boolean hasError = false;
		String errorMessage = null;
		if(userName==null || userName.isEmpty() || password==null || password.isEmpty()) {
			hasError = true;
			errorMessage = "Required username and password!";
		}else {
			try {
				user = DBUtils.findUser(MyUtils.getStoredConnection(req), userName, password);
				if(user==null) {
					hasError = true;
					errorMessage = "Username or Password invalid";
				}
			} catch (SQLException e) {
				e.printStackTrace();
				hasError = true;
				errorMessage = e.getMessage();
			}
		}
		
		if(hasError) {
			req.setAttribute("error", errorMessage);
			req.setAttribute("user", new UserAccount(userName, null, password));
			RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/views/loginView.jsp");
			rd.forward(req, resp);
		}else {
			MyUtils.storeLoginedUser(req.getSession(), user);
			if(remember) {
				MyUtils.storeUserCookie(resp, user);
			}else {
				MyUtils.deleteUserNameInCookie(resp);
			}
			resp.sendRedirect(req.getContextPath() + "/userInfo");
		}
	}
	
	
}
