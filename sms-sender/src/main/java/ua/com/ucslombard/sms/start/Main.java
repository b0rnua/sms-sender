package ua.com.ucslombard.sms.start;

import ua.com.ucslombard.sms.content.Body;
import ua.com.ucslombard.sms.db.Request;
import ua.com.ucslombard.sms.db.Util;
import ua.com.ucslombard.sms.http.Conversation;

public class Main {

	public static void main(String[] args) {

		Conversation.setHost("https://api.life.com.ua/ip2sms/");
		Conversation.setUser("");
		Conversation.setPassword("");
		String body = Body.build(Body.addAdditionalData(Util.
				convertResultSet2List(Request.get())));
		Conversation.doRequest(body);				
	}

}
