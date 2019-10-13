package recaptcha;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.net.ssl.HttpsURLConnection;

public class VerifyUtils {
	public static boolean verify(String gRecaptchResponse) {
		
		if(gRecaptchResponse==null || gRecaptchResponse.isEmpty()) {
			return false;
		}
		
		try {
			URL verifyUrl = new URL(MyConstant.SITE_VERIFY_URL);
			HttpsURLConnection conn = (HttpsURLConnection) verifyUrl.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("User-Agent", "Mozilla/5.0");
			conn.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
			String postParams = "secret="+MyConstant.SECRET_KEY+"&response="+gRecaptchResponse;
			
			conn.setDoOutput(true);
			OutputStream os = conn.getOutputStream();
			os.write(postParams.getBytes());
			os.flush();
			os.close();
			
			int responseCode = conn.getResponseCode();
			System.out.println("Response code = " + responseCode);
			
			InputStream is = conn.getInputStream();
			JsonReader jReader = Json.createReader(is);
			JsonObject jObject = jReader.readObject();
			jReader.close();
			System.out.println("Response: " + jObject);
			
			boolean success = jObject.getBoolean("success");
			return success;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} 
	}
}
