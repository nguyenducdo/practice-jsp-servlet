package utils;

import java.util.Collection;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;
import javax.servlet.http.HttpServletRequest;

public class UrlPatternUtils {
	private static boolean hasURLPattern (ServletContext context, String urlPattern) {
		Map<String, ? extends ServletRegistration> mapServletRegis = context.getServletRegistrations();
		for(String servletName : mapServletRegis.keySet()) {
			ServletRegistration sr = mapServletRegis.get(servletName);
			Collection<String> map = sr.getMappings();
			if(map.contains(urlPattern)) {
				return true;
			}
		}
		
		return false;
	}
	
	public static String getURLPattern(HttpServletRequest request) {
		String pathInfo = request.getPathInfo();
		String servletPath = request.getServletPath();
		String urlPattern = null;
		if(pathInfo!=null) {
			urlPattern = servletPath + "/*";
			return urlPattern;
		}
		urlPattern = servletPath;
		ServletContext context = request.getServletContext();
		if(hasURLPattern(context,urlPattern)) {
			return urlPattern;
		}
		
		int index = servletPath.lastIndexOf('.');
		if(index!=-1) {
			String ext = servletPath.substring(index+1);
			urlPattern = "*." +ext;
			if(hasURLPattern(context, urlPattern)) {
				return urlPattern;
			}
		}
		return "/";
	}
}
