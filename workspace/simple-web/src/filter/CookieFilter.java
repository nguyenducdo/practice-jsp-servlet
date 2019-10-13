package filter;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.UserAccount;
import utils.DBUtils;
import utils.MyUtils;
@WebFilter(filterName = "cookieFilter", urlPatterns = {"/*"})
public class CookieFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}
	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		
		HttpSession session = request.getSession();
		UserAccount user = MyUtils.getLoginedUser(session);
		if(user==null) {
			String userName = MyUtils.getUserNameInCookie(request);
			if(userName!=null) {
				try {
					user = DBUtils.findUser(MyUtils.getStoredConnection(req), userName);
					MyUtils.storeLoginedUser(session, user);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		chain.doFilter(request, response);
	}


}
