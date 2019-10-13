package com.dodo.controller.login;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dodo.authentication.AuthenticationForLogin;
import com.dodo.constant.SystemConstant;
import com.dodo.model.User;
import com.dodo.service.ILoginService;
import com.dodo.utils.AppUtil;
@WebServlet("/login")
public class LoginController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@Inject
	ILoginService loginService;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("views/login/loginForm.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		AuthenticationForLogin authen = loginService.authenticate(username, password);
		String errorMessage = authen.getErrorMessage();
		User user = authen.getUser();
		RequestDispatcher rd = null;
		if(user == null) {
			req.setAttribute("errorMessage", errorMessage);
			rd = req.getRequestDispatcher("views/login/loginForm.jsp");
			rd.forward(req, resp);
		}else {
			AppUtil.getInstance().putValue(req.getSession(), SystemConstant.USER, user);
			String uri = (String) AppUtil.getInstance().getValue(req.getSession(), SystemConstant.LOGIN_FOR);
			if(uri==null) {
				resp.sendRedirect(req.getContextPath() + "/homepage");
			}else {
				resp.sendRedirect(uri);
				AppUtil.getInstance().removeValue(req.getSession(), SystemConstant.LOGIN_FOR);
			}
			
		}
	}
	
	
	
}
