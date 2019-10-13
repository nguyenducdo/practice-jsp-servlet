package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/userInfo")
public class UserInfoServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userName = (String) req.getSession().getAttribute("loginedUser");
		if(userName == null || userName.isEmpty()) {
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}
		
		RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/views/userInfoView.jsp");
		rd.forward(req, resp);
	}

}
