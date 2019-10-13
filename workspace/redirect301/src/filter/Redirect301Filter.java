package filter;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DataUtils;
@WebFilter("/*")
public class Redirect301Filter implements Filter{

	@Override
	public void destroy() {
	}
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		Map<String, String> urlMapping = DataUtils.getUrlMapping();
		String requestUrl = req.getRequestURL().toString();
		String newUrl = urlMapping.get(requestUrl);
		System.out.println("Incomming URL = " + requestUrl);
		if(newUrl!=null) {
			// 301
			resp.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
			resp.setHeader("Location", newUrl);
			
			//302
//			resp.sendRedirect(newUrl);
		}
		
		chain.doFilter(request, response);
	}

	

}
