package utils;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import config.SecurityConfig;

public class SecurityUtils {
	 public static boolean isSecurityPage(HttpServletRequest request) {
		 String urlPattern = UrlPatternUtils.getURLPattern(request);
		 for(String role : SecurityConfig.getAllAppRoles()) {
			 List<String> urlPatterns = SecurityConfig.getURLPatternsForRole(role);
			 if(urlPatterns!=null && urlPatterns.contains(urlPattern)) {
				 return true;
			 }
		 }
		 return false;
	 }
	 
	 public static boolean hasPermission(HttpServletRequest request) {
		 for(String role : SecurityConfig.getAllAppRoles()) {
			 List<String> urlPatterns =	SecurityConfig.getURLPatternsForRole(role);
			 if(!urlPatterns.contains(UrlPatternUtils.getURLPattern(request))) {
				 continue;
			 }
			 if(request.isUserInRole(role)){
				 return true;
			 }
		 }
		 return false;
	 }
}
