package ua.com.ucslombard.sms.content;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartDocument;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;


public class Body {
	
	private static String source = "ucs-lombard";
	private static String start = dateTransform(new Date());
		
	private static String getMsg(Map<String, Object> map) {
		String result = "";
		
		int days = getPrimitiveInt(map.get("days"));
		
		switch (days) {
		case 3:
			result = "Через 3 дня истекает срок действия Вашего договора. Тел. ";
			break;
		case 0:
			result = "Сегодня истекает срок действия Вашего договора. Тел. ";
			break;
		case -7:
			result = "Через 3 дня изделия по Вашему договору буду безвозвратно отправлены в Госсокровищницу. Тел. ";
			break;
		default:
			result = "";
			break;
		}
		
		result += map.get("phoneNumber").toString();
		return  result;
	}
	
	private static int getPrimitiveInt(Object o) {
		int result = 0;
		try {
			result = ((Double)o).intValue();
		} catch (ClassCastException e) {
			try {
			result = ((Integer)o).intValue();
			} catch (ClassCastException e2) {
				try {
					result = ((Float)o).intValue();
					} catch (ClassCastException e3) {}
			}
		}
		return result;
	}
	
	public static String build(List<Map<String, Object>> data) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
		XMLEventWriter eventWriter;
		
		try {
			eventWriter = outputFactory.createXMLEventWriter(baos);
			XMLEventFactory eventFactory = XMLEventFactory.newInstance();
			XMLEvent endOfLine = eventFactory.createDTD("\n");
			StartDocument openTag = eventFactory.createStartDocument();
			eventWriter.add(openTag);
			StartElement configStartElement = eventFactory.createStartElement("",
			        "", "message");
			eventWriter.add(configStartElement);
			eventWriter.add(endOfLine);
			
			Map<String, String> args = new HashMap<String, String>();
			args.put("id", "individual");
			args.put("source", source);
			args.put("start", start);
			args.put("uniq_key", getUniqKey().toString());
			createNode(eventWriter, "service", "", args);
			if (!data.isEmpty()) {
				for (Map<String, Object> map:data) {		
					createNode(eventWriter, "to", map.get("telephone").toString());				
					args.clear();
					args.put("content-type", "text/plain");
					args.put("encoding", "plain");
					createNode(eventWriter, "body", getMsg(map), args);
				}
			}
			
		    eventWriter.add(eventFactory.createEndElement("", "", "message"));
		    eventWriter.add(endOfLine);
		    eventWriter.add(eventFactory.createEndDocument());
		    eventWriter.close();
		} catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return baos.toString();
	}
	
	private static void createNode(XMLEventWriter eventWriter, String name,
		      String value, Map<String,String> args) throws XMLStreamException {
		XMLEventFactory eventFactory = XMLEventFactory.newInstance();
		XMLEvent end = eventFactory.createDTD("\n");
		XMLEvent tab = eventFactory.createDTD("\t");
		
		StartElement sElement = eventFactory.createStartElement("", "", name);
		eventWriter.add(tab);		    
		eventWriter.add(sElement);
		
		for (Entry<String, String> entry:args.entrySet()) {
			eventWriter.add(eventFactory.createAttribute(entry.getKey(), entry.getValue()));
		}
				
		Characters characters = eventFactory.createCharacters(value);
		eventWriter.add(characters);
		
		EndElement eElement = eventFactory.createEndElement("", "", name);
		eventWriter.add(eElement);
		eventWriter.add(end);
	}
	
	private static void createNode(XMLEventWriter eventWriter, String name,
		      String value) throws XMLStreamException {
		XMLEventFactory eventFactory = XMLEventFactory.newInstance();
		XMLEvent end = eventFactory.createDTD("\n");
		XMLEvent tab = eventFactory.createDTD("\t");
		
		StartElement sElement = eventFactory.createStartElement("", "", name);
		eventWriter.add(tab);		    
		eventWriter.add(sElement);
		
		Characters characters = eventFactory.createCharacters(value);
		eventWriter.add(characters);
		
		EndElement eElement = eventFactory.createEndElement("", "", name);
		eventWriter.add(eElement);
		eventWriter.add(end);
	}
	
	private static String dateTransform(Date date) {
        String result = null;
        
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MINUTE, 5);
        date.setTime(c.getTimeInMillis());
        
        if (date != null) {
            result = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.US).format(date);

        }
        
        return result; 
	}
	
	private static Integer getUniqKey() {
        Integer uniqKey;
        
        do {
            uniqKey = new Random().nextInt();
        } while (uniqKey < 0);
        
        return uniqKey;
	}
	
	public static List<Map<String, Object>> addAdditionalData(List<Map<String, Object>> list) {
		Map<String, Object> map = new HashMap<String, Object>();
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
		return list;
	}

}
