package pack;

import java.io.File;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(urlPatterns = {"*.png","*.jpg","*.gif"}, initParams = {@WebInitParam(name="notFoundImage",value="/images/image-not-found.png")})
public class ImageFilter implements Filter{
	
	private String notFoundImage;
	
	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		
		
		String realRootPath = req.getServletContext().getRealPath("");
		String servletPath = req.getServletPath();
		String imgRealPath = realRootPath + servletPath;
		System.out.println("image real path = " + imgRealPath);
		File file = new File(imgRealPath);
		if(!file.exists()) {
			HttpServletResponse resp = (HttpServletResponse) response;
			resp.sendRedirect(req.getContextPath() + notFoundImage);
//			resp.sendRedirect(req.getServletContext().getContextPath() + notFoundImage); // Giong nhau
			
//			RequestDispatcher rd = req.getServletContext().getRequestDispatcher(notFoundImage);
//			rd.forward(request, response);
		}else {
			chain.doFilter(request, response);
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		notFoundImage = filterConfig.getInitParameter("notFoundImage");
	}
	
}
