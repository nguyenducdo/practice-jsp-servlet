package com.dodo.controller.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dodo.constant.SystemConstant;
import com.dodo.utils.AppUtil;
@WebServlet("/logout")
public class LogoutController extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		AppUtil.getInstance().removeValue(req.getSession(), SystemConstant.USER);
		resp.sendRedirect(req.getContextPath()+"/homepage");
		
	}
	
	

}
