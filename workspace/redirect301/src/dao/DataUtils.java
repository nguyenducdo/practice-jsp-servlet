package dao;

import java.util.HashMap;
import java.util.Map;

public class DataUtils {
	private static Map<String, String> urlMapping;
	public static Map<String,String> getUrlMapping(){
		if(urlMapping==null) {
			urlMapping = new HashMap<String, String>();
			urlMapping.put("http://localhost:8080/redirect301/document/123/java-servlet",
                    "http://localhost:8080/redirect301/article/123/java-servlet-tutorial");
			
			urlMapping.put("http://localhost:8080/redirect301/document/111/java-io-tutorial",
                    "http://localhost:8080/redirect301/article/111/java-io-tutorial");
		}
		return urlMapping;
	}
}
