package com.dodo.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dodo.constant.SystemConstant;
import com.dodo.model.User;
import com.dodo.utils.AppUtil;

public class PermissionFilter implements Filter{
	
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		String uri = req.getRequestURI();
		if(uri.contains("/admin")) {
			User user = (User) AppUtil.getInstance().getValue(req.getSession(), SystemConstant.USER);
			if(user!=null) {
				if(user.getRole().getCode().equalsIgnoreCase(SystemConstant.ROLE_ADMIN)) {
					chain.doFilter(request, response);
					return;
				}else {
					RequestDispatcher rd = req.getRequestDispatcher("/views/login/accessDeniedView.jsp");
					rd.forward(req, resp);
					return;
				}
			}else {
				AppUtil.getInstance().putValue(req.getSession(), SystemConstant.LOGIN_FOR, uri);
				resp.sendRedirect(req.getContextPath()+"/login");
				return;
			}
		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		
	}

}
