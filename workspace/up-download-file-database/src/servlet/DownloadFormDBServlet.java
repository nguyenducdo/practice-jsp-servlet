package servlet;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import dao.DBUtils;
import model.Attachment;

@WebServlet(urlPatterns = {"/download"})
public class DownloadFormDBServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Connection conn = null;
		try {
			conn = DBUtils.getConnection();
			conn.setAutoCommit(false);
			int id = Integer.parseInt(req.getParameter("id"));
			Attachment attachment = getAttachment(conn, id);
			if(attachment == null) {
				resp.getWriter().write("No data found");
				return;
			}
			String mimeType = this.getServletContext().getMimeType(attachment.getFile_name());
			resp.setHeader("Content-Type", mimeType);
			Blob file_data = attachment.getFile_data();
			resp.setHeader("Content-Length", String.valueOf(file_data.length()));
			resp.setHeader("Content-Disposition", "inline; filename=\""+attachment.getFile_name()+"\"");
			
			InputStream is = file_data.getBinaryStream();
			byte[] bytes = new byte[1024];
			int byteNumber;
			
			while((byteNumber = is.read(bytes))!=-1) {
				resp.getOutputStream().write(bytes, 0, byteNumber);
			}
			is.close();
			
		} catch (ClassNotFoundException | SQLException | NumberFormatException e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeConnection(conn);
		}
	}
	
	private Attachment getAttachment(Connection conn, int id) throws SQLException {
		String query = "select id, file_name, file_data, description from attachment where id=?";
		PreparedStatement ps = (PreparedStatement) conn.prepareStatement(query);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			String file_name = rs.getString("file_name");
			Blob file_data = rs.getBlob("file_data");
			String description = rs.getString("description");
			return new Attachment(id, file_name, file_data, description);
		}
		
		return null;
	}
	
}
