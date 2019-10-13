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

@WebServlet("/showImage")
public class ShowImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public ShowImageServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Connection conn = DBUtils.getConnection();
			int id = Integer.parseInt(request.getParameter("id"));
			Attachment attachment = getAttachment(conn, id);
			if(attachment == null) {
				response.getWriter().write("file not found");
				return;
			}
			
			String mimeType = this.getServletContext().getMimeType(attachment.getFile_name());
			Blob file_data = attachment.getFile_data();
			
			response.setHeader("Content-Type", mimeType);
			response.setHeader("Content-Length", String.valueOf(file_data.length()));
			response.setHeader("Content-Disposition", "inline; filename=\""+attachment.getFile_name()+"\"");
			
			InputStream is = file_data.getBinaryStream();
			
			byte[] bytes = new byte[1024];
			int byteNumber;
			while((byteNumber = is.read(bytes))!=-1) {
				response.getOutputStream().write(bytes, 0, byteNumber);
			}
			is.close();
		} catch (ClassNotFoundException | SQLException | NumberFormatException e) {
			e.printStackTrace();
			response.getWriter().write(e.getMessage());
		}
	}
	
	private Attachment getAttachment(Connection conn, int id) throws SQLException {
		String query = "select id, file_name, file_data, description from attachment where id = ?";
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
