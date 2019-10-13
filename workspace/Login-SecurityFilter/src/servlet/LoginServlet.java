package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.UserAccount;
import utils.AppUtils;
import utils.DataDAO;
@WebServlet(urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("views/loginView.jsp");
		rd.forward(req, resp);
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userName = req.getParameter("userName");
		String password = req.getParameter("password");
		UserAccount loginedUser = DataDAO.findUser(userName, password);
		if(loginedUser==null) {
			String errorMessage = "Invalid username or password";
			req.setAttribute("error", errorMessage);
			RequestDispatcher rd = req.getRequestDispatcher("views/loginView.jsp");
			rd.forward(req, resp);
			return;
		}
		AppUtils.storeLoginedUser(req.getSession(), loginedUser);
		int redirectId = -1;
		try {
			redirectId = Integer.parseInt(req.getParameter("redirectId"));
		}catch(NumberFormatException ex) {
		}
		String requestUri = AppUtils.getRedirectAfterLoginUrl(req.getSession(), redirectId);
		if(requestUri!=null) {
			resp.sendRedirect(requestUri);
		}else {
			resp.sendRedirect(req.getContextPath() + "/userInfo");
		}
	}

}
