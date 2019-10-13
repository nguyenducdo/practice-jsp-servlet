package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.Connection;

import bean.Product;
import bean.UserAccount;
import utils.DBUtils;
import utils.MyUtils;
@WebServlet(urlPatterns = {"/productList"})
public class ProductListServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		UserAccount user = MyUtils.getLoginedUser(session);
		String errorMessage = null;
		if(user==null) {
			resp.sendRedirect(req.getContextPath() +"/login");
			return;
		}
		
		Connection conn = MyUtils.getStoredConnection(req);
		List<Product> productList = null;
		try {
			productList = DBUtils.queryProducts(conn);
			if(productList.isEmpty()) {
				errorMessage = "have no products";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			errorMessage = e.getMessage();
		}

		req.setAttribute("error", errorMessage);
		req.setAttribute("productList", productList);
		RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/views/productListView.jsp");
		rd.forward(req, resp);
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
