package servlet;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
@WebServlet(urlPatterns = {"/uploadFile"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
       maxFileSize = 1024 * 1024 * 10, // 10MB
       maxRequestSize = 1024 * 1024 * 50) // 50MB
public class UploadFileServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public static final String SAVE_DIRECTORY = "uploadDir";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/views/uploadFile.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String description = req.getParameter("description");
        System.out.println("Description: " + description);
        
		String realPath = req.getServletContext().getRealPath("");
		realPath = realPath.replace('\\', '/');
		String savePath = null;
		if(realPath.endsWith("/")) {
			savePath = realPath + SAVE_DIRECTORY;
		}else {
			savePath = realPath + "/" + SAVE_DIRECTORY;
		}
		
		try {
			File saveFolder = new File(savePath);
			if(!saveFolder.exists()) {
				saveFolder.mkdir();
			}
			
			for(Part part : req.getParts()) {
				String fileName = getFileNameFromPart(part);
				if(fileName != null && !fileName.isEmpty()) {
					String filePath = savePath + "/" +fileName;
					System.out.println("Write attachment to file: " + filePath);
					part.write(filePath);
				}
			}
			resp.sendRedirect(req.getContextPath() + "/Result");
		}catch(IOException e) {
			e.printStackTrace();
			req.setAttribute("errorMessage","Error" + e.getMessage());
			RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/views/uploadFile.jsp");
			rd.forward(req, resp);
		}
	}
	
	private final String getFileNameFromPart(Part part) {
		
		
		String contentDisp = part.getHeader("content-disposition"); // format: disposition_type; parameter="value"; parameter="value"...
		String[] items = contentDisp.split(";");
		for(String item : items) {
			if(item.trim().startsWith("filename")) {
				int index = item.indexOf('=');
				String url = item.substring(index+2, item.length()-1); // filename="url"
				url = url.replace('\\', '/');
				index = url.lastIndexOf('/');
				String fileName = url.substring(index+1);
				return fileName;
			}
		}
		return null;
	}

}
