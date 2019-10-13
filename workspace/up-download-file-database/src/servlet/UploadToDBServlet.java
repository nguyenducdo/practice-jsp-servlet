package servlet;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import dao.DBUtils;
@WebServlet(urlPatterns = {"/uploadToDB"})
@MultipartConfig(fileSizeThreshold = 1024*1024*2,
	maxFileSize = 1024*1024*10,
	maxRequestSize = 1024*1024*50
)
public class UploadToDBServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/views/uploadView.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Connection conn = null;
		try {
			conn = DBUtils.getConnection();
			conn.setAutoCommit(false);
			String description = req.getParameter("description");
			for(Part part : req.getParts()) {
				String fileName = getFileNameInPart(part);
				if(fileName!=null && !fileName.isEmpty()) {
					InputStream is = part.getInputStream();
					uploadToDB(conn, fileName, is, description);
				}
			}
			conn.commit();
			resp.sendRedirect(req.getContextPath() + "/result.jsp");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			req.setAttribute("errorMessage", "Error: " + e.getMessage());
			RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/views/uploadView.jsp");
			rd.forward(req, resp);
		}finally {
			DBUtils.closeConnection(conn);
		}
		
	}
	
	private String getFileNameInPart(Part part) {
		String contentDisp = part.getHeader("content-disposition");
		String[] items = contentDisp.split(";");
		for(String item : items) {
			if(item.trim().startsWith("filename")) {
				int index = item.indexOf('=');
				String url = item.substring(index+2,item.length()-1);
				url.replace('\\', '/');
				index = url.lastIndexOf('/');
				String fileName = url.substring(index+1);
				return fileName;
			}
		}
		return null;
	}
	
	private void uploadToDB(Connection conn, String file_name, InputStream file_data, String description) throws SQLException {
		String query = "insert into attachment(file_name,file_data,description) values(?,?,?)";
		PreparedStatement ps = (PreparedStatement) conn.prepareStatement(query);
		ps.setString(1, file_name);
		ps.setBlob(2, file_data);
		ps.setString(3, description);
		ps.executeUpdate();
	}
	
}
