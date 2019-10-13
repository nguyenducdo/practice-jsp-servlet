package filter;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;

import conn.ConnectionUtils;
import utils.MyUtils;

@WebFilter(filterName = "jdbcFilter", urlPatterns = {"/*"})
public class JDBCFilter implements Filter{
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}
	
	@Override
	public void destroy() {
	}
	
	private boolean needJDBC(HttpServletRequest request) {
		String servletPath = request.getServletPath();
		String pathInfo = request.getPathInfo();
		String urlPattern = servletPath;
		if(pathInfo != null) {
			urlPattern = servletPath + "/*";
		}
		
		Map<String, ? extends ServletRegistration> servletRegisMap = request.getServletContext().getServletRegistrations();
		for(ServletRegistration sr : servletRegisMap.values()) {
			Collection<String> mapping = sr.getMappings();
			if(mapping.contains(urlPattern)) {
				return true;
			}
		}
		
		return false;
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		Connection conn = null;
		if(needJDBC(request)) {
			try {
				conn = ConnectionUtils.getConnection();
				conn.setAutoCommit(false);
				MyUtils.storeConnection(request,conn);
				chain.doFilter(request, response);
				conn.commit();
			} catch (ClassNotFoundException | SQLException e) {
				ConnectionUtils.rollBackQuietly(conn);
				e.printStackTrace();
				throw new ServletException();
			}finally {
				ConnectionUtils.closeQuietly(conn);
			}
		}else {
			chain.doFilter(request, response);
		}
	}

}
