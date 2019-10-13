package pack;

import java.io.IOException;
import java.util.Collection;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/other/*"})
public class ExampleInfoServlet extends HttpServlet{

	
	private static final long serialVersionUID = -1143048614366888977L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ServletOutputStream out = resp.getOutputStream();
		out.println("<style> span {color:blue;} </style>");
		
		String requestURL = req.getRequestURL().toString();
		out.println("<br><span>requestURL:</span>");
		out.println(requestURL);
		
		
		out.println("<br><span>requestURI:</span>");
		out.println(req.getRequestURI());
		out.println("<br><span>contextPath:</span>");
		out.println(req.getContextPath());
		out.println("<br><span>servletPath:</span>");
		out.println(req.getServletPath());
		out.println("<br><span>pathInfo:</span>");
		out.println(req.getPathInfo());
		out.println("<br><span>queryString:</span>");
		out.println(req.getQueryString());
		out.println("<br><span>getParameter text1:</span>");
		out.println(req.getParameter("text1"));
		out.println("<br><span>getParameter text2:</span>");
		out.println(req.getParameter("text2"));
		
		 // Server Infos
        out.println("<br><br><b>Server info:</b>");
        
        out.println("<br><span>serverName:</span>");
		out.println(req.getServerName());
        out.println("<br><span>serverPort:</span>");
		out.println(req.getServerPort());
        
        // Client Infos
        out.println("<br><br><b>Client info:</b>");
        
        out.println("<br><span>remoteAddr:</span>");
		out.println(req.getRemoteAddr());
        out.println("<br><span>remoteHost:</span>");
		out.println(req.getRemoteHost());
        out.println("<br><span>remotePort:</span>");
		out.println(req.getRemotePort());
        out.println("<br><span>remoteUser:</span>");
		out.println(req.getRemoteUser());
        
        // Header Infos
        out.println("<br><br><b>headers:</b>");
        out.println("<br><b>request:</b>");
        Enumeration<String> headers = req.getHeaderNames();
        while(headers.hasMoreElements()) {
        	String header = headers.nextElement();
        	out.println("<br><span>" + header + "</span>: " + req.getHeader(header));
        }
        out.println("<br><b>response:</b>");
        Collection<String> headersResp = resp.getHeaderNames();
        for(String header : headersResp) {
        	out.println("<br><span>" + headersResp + "</span>: " + req.getHeader(header));
        }
        // Servlet Context info:
        out.println("<br><br><b>Servlet Context info:</b>");
		out.println(req.getServletContext().toString());
        
        // Vị trí của ứng dụng web trên ổ cứng (hard disk).
        out.println("<br><span>realPath:</span>");
		out.println(req.getServletContext().getRealPath(""));
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
	
	
}
