package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import recaptcha.VerifyUtils;
@WebServlet("/doLogin")
public class DoLoginServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String userName = req.getParameter("userName");
			String password = req.getParameter("password");
			if(!"tom".equals(userName) || !"tom001".equals(password)) {
				throw new MyException("Username or Password invalid");
			}
			String gRecaptchaResponse = req.getParameter("g-recaptcha-response");
			System.out.println("gRecaptchaResponse = " + gRecaptchaResponse);
			if(!VerifyUtils.verify(gRecaptchaResponse)) {
				throw new MyException("Captcha invalid");
			}else {
				req.getSession().setAttribute("loginedUser", userName);
				resp.sendRedirect(req.getContextPath() + "/userInfo");
				return;
			}
		}catch(MyException e) {
			System.out.println(e.getMessage());
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}
		
	}
	
	private class MyException extends Exception{
		private static final long serialVersionUID = 1L;
		private String message;

		public MyException(String message) {
			super();
			this.message = message;
		}

		@Override
		public String getMessage() {
			return message;
		}
		
	}
	
}
