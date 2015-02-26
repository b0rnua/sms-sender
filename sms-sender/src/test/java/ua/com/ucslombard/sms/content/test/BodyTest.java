package ua.com.ucslombard.sms.content.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import ua.com.ucslombard.sms.content.Body;

public class BodyTest {
	
//	@Test
//	public void getUniqKeyTest() {
//		System.out.println(Body.getUniqKey());		
//	}
	
//	@Test
//	public void dateTransformTest() {
//		String expect = "Wed, 25 Feb 2015 13:05:00 +0200";
//		Date date = null;
//		try {
//			date = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse("25.02.2015 13:00:00");
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		String result = null;
//		result = Body.dateTransform(date);
//		assertEquals(expect, result);
//	}
	
	@Test
	public void buildTest() {
		
		String expect = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
				+ "<message>\n"
				+ "\t<service id=\"individual\" "
				+ "source=\"ucs-lombard\">"
				+ "</service>\n"
				+ "\t<to>0509970208</to>\n"
				+ "\t<body content-type=\"text/plain\" encoding=\"plain\">Через 3 дня истекает срок действия Вашего договора. Тел. 0506963358</body>\n"
				+ "</message>\n";
		String result = null;
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("telephone", "0509970208");
		map.put("days", 3);
		map.put("phoneNumber", "0506963358");
		list.add(map);
		map = new HashMap<String, Object>();
		map.put("telephone", "0509970200");
		map.put("days", -7);
		map.put("phoneNumber", "0506963358");
		list.add(map);
		map = new HashMap<String, Object>();
		map.put("telephone", "0509970208");
		map.put("days", 0);
		map.put("phoneNumber", "0506963355");
		list.add(map);
		
		result = Body.build(list);

		assertEquals(expect, result);
	}
	
	@Test
	public void addAdditionalDataTest() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		map = new HashMap<String, Object>();
		map = new HashMap<String, Object>();
		map.put("telephone", "0509970208");
		map.put("days", 0);
		map.put("phoneNumber", "0509970208");
		list.add(map);
		map = new HashMap<String, Object>();
		map.put("telephone", "0503649864");
		map.put("days", -7);
		map.put("phoneNumber", "0509970208");
		list.add(map);
		map = new HashMap<String, Object>();
		map.put("telephone", "0503649864");
		map.put("days", 0);
		map.put("phoneNumber", "0509970208");
		list.add(map);
		map = new HashMap<String, Object>();
		map.put("telephone", "0503649864");
		map.put("days", 3);
		map.put("phoneNumber", "0509970208");
		list.add(map);
		map = new HashMap<String, Object>();
		map.put("telephone", "0509970208");
		map.put("days", 0);
		map.put("phoneNumber", "0509970208");
		list.add(map);
		
		List<Map<String, Object>> list2 = new ArrayList<Map<String, Object>>();
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2 = new HashMap<String, Object>();
		map2.put("telephone", "0509970208");
		map2.put("days", 0);
		map2.put("phoneNumber", "0509970208");
		list2.add(map2);
		
		list2 = Body.addAdditionalData(list2);
		
		assertEquals(list, list2);
		
	}
	
//	@Test
//	public void getPrimitiveIntTest() {
//		int expect[] = {0,1,-2,-3};
//		Object source[] = {"ss", new Integer(1), new Double(-2), new Float(-3)};
//		
//		for (int i=0; i<expect.length; i++) {
//			assertEquals(expect[i], Body.getPrimitiveInt(source[i]));
//		}
//	}

}
