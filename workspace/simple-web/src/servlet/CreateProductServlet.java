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

import bean.Product;
import utils.DBUtils;
import utils.MyUtils;
@WebServlet(urlPatterns = {"/createProduct"})
public class CreateProductServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/views/createProductView.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String code = req.getParameter("code");
		String name = req.getParameter("name");
		String stringPrice = req.getParameter("price");
		boolean hasError = false;
		String errorMessage = null;
		if(code.isEmpty() || name.isEmpty() || stringPrice.isEmpty()) {
			hasError = true;
			errorMessage = "Required full info!";
		}
		
		String regex = "\\w+";
		if(!code.matches(regex)) {
			hasError = true;
			errorMessage = "Product code invalid!";		
		}
		
		float price = 0;
		try {
			price = Float.parseFloat(stringPrice);
		}catch(NumberFormatException e) {
			hasError = true;
			errorMessage = "Price format invalid";
		}
		
		Product product = new Product(code, name, price);
		if(!hasError) {
			Connection conn = MyUtils.getStoredConnection(req);
			try {
				DBUtils.insertProduct(conn, product);
			} catch (SQLException e) {
				e.printStackTrace();
				hasError=true;
				errorMessage = e.getMessage();
			}
		}
		if(!hasError) {
			resp.sendRedirect(req.getContextPath() + "/productList");
		}else {
			req.setAttribute("product", product);
			req.setAttribute("error", errorMessage);
			RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/views/createProductView.jsp");
			rd.forward(req, resp);
		}
		
		
	}
	
	
}
