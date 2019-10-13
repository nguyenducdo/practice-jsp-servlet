package com.dodo.utils;

import javax.servlet.http.HttpSession;

public class AppUtil {
	private static AppUtil appUtil = null;
	public static AppUtil getInstance() {
		if(appUtil==null) {
			appUtil = new AppUtil();
		}
		return appUtil;
	}
	
	public void putValue(HttpSession session, String key, Object value) {
		session.setAttribute(key, value);
	}
	
	public Object getValue(HttpSession session, String key) {
		return session.getAttribute(key);
	}
	
	public void removeValue(HttpSession session, String key) {
		session.removeAttribute(key);
	}
}
