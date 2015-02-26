package ua.com.ucslombard.sms.http;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.apache.commons.codec.binary.Base64OutputStream;
import org.apache.log4j.Logger;

public class Conversation {
	
	private static Logger log = Logger.getLogger(Conversation.class.getName());
	
	private static String host;
	private static String user;
	private static String password;
	
	public static void setHost(String host) {
		Conversation.host = host;
	}

	public static void setUser(String user) {
		Conversation.user = user;
	}

	public static void setPassword(String password) {
		Conversation.password = password;
	}

	public static void doRequest(String body) {
		try {
			log.info(body);
			URL url = new URL(host);
			HttpsURLConnection http = (HttpsURLConnection) url.openConnection();
			http.setRequestMethod("POST");
			http.setRequestProperty("Content-Encode", "utf-8");
			http.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
			http.setRequestProperty("Content-Length", Integer.toString(body.getBytes("UTF-8").length));
			http.setRequestProperty("Authorization", "Basic " + base64Encode(user + ":" + password));
			http.setUseCaches(false);
			http.setDoOutput(true);
			
			BufferedOutputStream bos = new BufferedOutputStream(http.getOutputStream());
			bos.write(body.getBytes());
			bos.flush();
			bos.close();
			
			InputStream is = http.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            String line;
            StringBuffer response = new StringBuffer(); 
            while((line = rd.readLine()) != null) {
              response.append(line);
              response.append('\r');
            }
            rd.close();
            log.info(response.toString());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("resource")
	public static String base64Encode(String s) {
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		Base64OutputStream out = new Base64OutputStream(bout);
		
		try {
			out.write(s.getBytes());
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return bout.toString();
	}

}
