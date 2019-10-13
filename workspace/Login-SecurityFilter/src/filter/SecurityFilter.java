package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.UserAccount;
import request.UserRoleRequestWrapper;
import utils.AppUtils;
import utils.SecurityUtils;

@WebFilter(urlPatterns = {"/*"})
public class SecurityFilter implements Filter{
	

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}
	
	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)arg0;
		HttpServletResponse response = (HttpServletResponse)arg1;
		
		String servletPath = request.getServletPath();
		if(servletPath.equals("/login")) {
			chain.doFilter(arg0, arg1);
			return;
		}
		HttpSession session = request.getSession();
		UserAccount loginedUser = AppUtils.getLoginedUser(session);
		HttpServletRequest wrapRequest = request;
		if(loginedUser!=null) {
			wrapRequest = new UserRoleRequestWrapper(loginedUser.getUserName(), loginedUser.getRoles(), request);
		}
		if(SecurityUtils.isSecurityPage(request)) {
			if(loginedUser==null) {
				int redirectId = AppUtils.storeRedirectAfterLoginUrl(session, request.getRequestURI());
				response.sendRedirect(request.getContextPath() + "/login?redirectId="+redirectId);
				return;
			}
			if(!SecurityUtils.hasPermission(wrapRequest)) {
				RequestDispatcher rd = request.getRequestDispatcher("/views/accessDeniedView.jsp");
				rd.forward(request, response);
				return;
			}
		}
		chain.doFilter(arg0, arg1);
	}

}
