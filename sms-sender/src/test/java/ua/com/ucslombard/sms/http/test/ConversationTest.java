package ua.com.ucslombard.sms.http.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import ua.com.ucslombard.sms.content.Body;
import ua.com.ucslombard.sms.http.Conversation;

public class ConversationTest {
	
	@Test
	public void base64EncodeTest() {
		String expect = "SmF2YVRpcHMubmV0";
		String result = Conversation.base64Encode("JavaTips.net");
		assertEquals(expect, result);
	}
	
	@Test
	public void doRequestTest() {
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("telephone", "0509970208");
		map.put("days", 3);
		map.put("phoneNumber", "0506963358");
		list.add(map);
		map = new HashMap<String, Object>();
		map.put("telephone", "0509970208");
		map.put("days", -7);
		map.put("phoneNumber", "0506963358");
		list.add(map);
		map = new HashMap<String, Object>();
		map.put("telephone", "0509970208");
		map.put("days", 0);
		map.put("phoneNumber", "0506963358");
		list.add(map);
		
		Conversation.doRequest(Body.build(list));
	}

}
