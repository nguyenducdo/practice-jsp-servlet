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
import com.sun.xml.internal.messaging.saaj.packaging.mime.util.QEncoderStream;

import bean.Product;
import utils.DBUtils;
import utils.MyUtils;

@WebServlet(urlPatterns = {"/editProduct"})
public class EditProductServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String code = req.getParameter("code");
		boolean hasError = false;
		String errorMessage = null;
		Product product = null;
		if(code!=null && !code.isEmpty()) {
			Connection conn = MyUtils.getStoredConnection(req);
			try {
				product = DBUtils.findProduct(conn, code);
			} catch (SQLException e) {
				e.printStackTrace();
				hasError = true;
				errorMessage = e.getMessage();
			}
			if(product == null) {
				hasError = true;
				errorMessage = "Product not found";
			}
		}else {
			hasError = true;
			errorMessage = "Code param is empty";
		}
		
		if(hasError) {
			resp.sendRedirect(req.getContextPath() + "/productList");
			return;
		}else {
			req.setAttribute("product", product);
			req.setAttribute("error", errorMessage);
			RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/views/editProductView.jsp");
			rd.forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String priceStr = req.getParameter("price");
		String code = req.getParameter("code");
		boolean hasError = false;
		String errorMessage = null;
		
		if(priceStr.isEmpty() || name.isEmpty()) {
			hasError = true;
			errorMessage = "Please enter info in all field";
		}
		float price = 0;
		try {
			price = Float.parseFloat(priceStr);
		}catch(NumberFormatException e) {
			e.printStackTrace();
			hasError = true;
			errorMessage = e.getMessage();
		}
		
		if(!hasError) { // Thong tin nhap vaoo hop le
			Connection conn = MyUtils.getStoredConnection(req);
			Product product = new Product(code, name, price);
			try {
				DBUtils.updateProduct(conn, product);
			} catch (SQLException e) {
				e.printStackTrace();
				hasError = true;
				errorMessage = e.getMessage();
			}
		}
		
		if(!hasError) { //Update thanh cong, khong co them error nao
			resp.sendRedirect(req.getContextPath() + "/productList");
		}else {
			RequestDispatcher rd = req.getRequestDispatcher("WEB_INF/views/editProductView.jsp");
			rd.forward(req, resp);
		}
		
	}
	
	
}
