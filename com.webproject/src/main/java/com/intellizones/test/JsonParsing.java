package com.intellizones.test;
import java.io.InputStream;
import java.io.StringReader;
//http://www.htmlescape.net/javaescape_tool.html
//https://adobe.github.io/Spry/samples/data_region/JSONDataSetSample.html
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

import org.json.JSONObject;
import org.json.XML;
import org.xml.sax.InputSource;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JsonParsing {
static JsonParser parser = new JsonParser();

public static void main (String[] str){
	try{
		String strr	=	"{\"id\" : 12345, \"days\" : [ \"Monday\", \"Wednesday\" ], \"person\" : { \"firstName\" : \"David\", \"lastName\" : \"Menoyo\" } }";
		//"{"id" : 12345, "days" : [ "Monday", "Wednesday" ], "person" : { "firstName" : "David", "lastName" : "Menoyo" } }";
		//String st	=	"{\n\t\"id\": \"0001\",\n\t\"type\": \"donut\",\n\t\"name\": \"Cake\",\n\t\"ppu\": 0.55,\n\t\"batters\":\n\t\t{\n\t\t\t\"batter\":\n\t\t\t\t[\n\t\t\t\t\t{ \"id\": \"1001\", \"type\": \"Regular\" },\n\t\t\t\t\t{ \"id\": \"1002\", \"type\": \"Chocolate\" },\n\t\t\t\t\t{ \"id\": \"1003\", \"type\": \"Blueberry\" },\n\t\t\t\t\t{ \"id\": \"1004\", \"type\": \"Devil\'s Food\" }\n\t\t\t\t]\n\t\t},\n\t\"topping\":\n\t\t[\n\t\t\t{ \"id\": \"5001\", \"type\": \"None\" },\n\t\t\t{ \"id\": \"5002\", \"type\": \"Glazed\" },\n\t\t\t{ \"id\": \"5005\", \"type\": \"Sugar\" },\n\t\t\t{ \"id\": \"5007\", \"type\": \"Powdered Sugar\" },\n\t\t\t{ \"id\": \"5006\", \"type\": \"Chocolate with Sprinkles\" },\n\t\t\t{ \"id\": \"5003\", \"type\": \"Chocolate\" },\n\t\t\t{ \"id\": \"5004\", \"type\": \"Maple\" }\n\t\t]\n}";
	
		//String st="{\n\t\"id\": \"0001\",\n\t\"type\": \"donut\",\n\t\"name\": \"Cake\",\n\t\"ppu\": 0.55,\n\t\"batters\":\n\t\t{\n\t\t\t\"batter\":{ \"id\": \"1004\", \"type\": \"Devil\'s Food\" }\n\t\t},\n\t\"topping\":\n\t\t\t{ \"id\": \"5004\", \"type\": \"Maple\" }\n}";
	
		//String st ="{\n\t\"id\": \"0001\",\n\t\"type\": \"donut\",\n\t\"name\": \"Cake\",\n\t\"ppu\": 0.55,\n\t\"batters\":\n\t\t{\n\t\t\t\"batter\":{ \"id\": \"1004\", \"type\": \"Devil\'s Food\" ,\"temp\": {\"myid\":\"rahul\"}}\n\t\t},\n\t\"topping\":\n\t\t\t{ \"id\": \"5004\", \"type\": \"Maple\" }\n}";
	
		String st="{\n\t\"id\": \"0001\",\n\t\"type\": \"donut\",\n\t\"name\": \"Cake\",\n\t\"ppu\": 0.55,\n\t\"batters\":\n\t\t{\n\t\t\t\"batter\":\n\t\t\t\t[\n\t\t\t\t\t{ \"id\": \"1001\", \"type\": \"Regular\" },\n\t\t\t\t\t{ \"id\": \"1002\", \"type\": \"Chocolate\" },\n\t\t\t\t\t{ \"id\": \"1003\", \"type\": \"Blueberry\" },\n\t\t\t\t\t{ \"id\": \"1004\", \"type\": \"Devil\'s Food\" }\n\t\t\t\t]\n\t\t},\n\t\"topping\":\n\t\t[\n\t\t\t{ \"id\": \"5001\", \"type\": \"None\" },\n\t\t\t{ \"id\": \"5002\", \"type\": \"Glazed\" },\n\t\t\t{ \"id\": \"5005\", \"type\": \"Sugar\" },\n\t\t\t{ \"id\": \"5007\", \"type\": \"Powdered Sugar\" },\n\t\t\t{ \"id\": \"5006\", \"type\": \"Chocolate with Sprinkles\" },\n\t\t\t{ \"id\": \"5003\", \"type\": \"Chocolate\" },\n\t\t\t{ \"id\": \"5004\", \"type\": \"Maple\" }\n\t\t]\n}";	
		
		String xml = XML.toString(new JSONObject(st));
		System.out.println(xml);
		
		/***************Convert to XML******************************/
		xml	=	"<rootiot>"+xml+"</rootiot>";
		SAXCreateXPath	xpathCreate	=	new SAXCreateXPath();
		List listTag	=	xpathCreate.parseXML(xml);
		/***************DONE XML******************************/
		//List listTag	=	xpathCreate.getTagList();
		//System.out.println("\n TagList SIze "+listTag.size());
		
		/**Filter List to remove any Node which does not have value. It may have child nodes**/
		DocXPath	docXPath	=	new DocXPath(xml);
		docXPath.initDocumentBuilder();
		
		docXPath.setXPathValue("//rootiot/ppu", "10.01");
		
		docXPath.getXPathValue(xml,listTag);
		/**Get Xpath Key and Value FinalList**/
		HashMap	h	=	(HashMap<String, String> )docXPath.getxPathKeyValue();
		
		
		Set s	=	h.keySet();
		Iterator	iterateFinal	=	s.iterator();
		System.out.println("Final List\n");
		while(iterateFinal.hasNext()){
			String key	=	(String)iterateFinal.next();
			String value=	(String)h.get(key);
			System.out.println(key +":"+value);
		}
		
		
//		ListIterator<String> l	=	listTag.listIterator();
//		while(l.hasNext()){
//			String values	=	l.next();
//			System.out.println(values);
//		}
		docXPath.writeToFile();
		//docXPath.getXPathValue(inputstream,"//rootiot/batters/batter");
		
	//	String jsonString = XML.toJSONObject(xml).toString();
	//	System.out.println("\n\n"+jsonString);
		
	//	HashMap h	=	createHashMapFromJsonString(st);
	//	HashMap returnMap	=new HashMap<String, String>();
	//	returnMap	=	getHashMap(h,returnMap,"");
	//	 Iterator it = h.entrySet().iterator();
	//	    while (it.hasNext()) {
	//	        Map.Entry pair = (Map.Entry)it.next();
	//	        System.out.println(pair.getKey() + " :=: " + pair.getValue()+" :TYPE: "+pair.getClass());
	//	        it.remove(); // avoids a ConcurrentModificationException
	//	    }	
		//System.out.println(s.getClass());
	} catch (Exception e){
		e.printStackTrace();
	}

}
public static Map	all	=	new HashMap();

public static Map getValues(Map<String, Object> map) {

    //List<Object> retVal = new ArrayList<Object>();
    Map retVal	=	new HashMap<String, Object>();
    for (Map.Entry<String, Object> entry : map.entrySet()) {
        Object value = entry.getValue();

        if (value instanceof Map) {
            //retVal.addAll(getValues((HashMap) value));
        	all.putAll(getValues((Map) value));
        	
        } else {
        	retVal.put(entry.getKey(), value);
            //retVal.add(value);
        }
    }

    return retVal;
}

public static HashMap<String, String> getHashMap(HashMap<String, Object> dataMap,HashMap<String, Object> returnMap,String initKey){
	 Iterator it = dataMap.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pair = (Map.Entry)it.next();
	        System.out.println(pair.getKey() + " = " + pair.getValue());
	        String key	=	(String)pair.getKey();
	        Object value	=	pair.getValue();
	        if(value instanceof HashMap){
	        	System.out.println("\n Values : As HashMap");
	        	HashMap	newHashMap	= new HashMap<String, Object>();	
	        	newHashMap.putAll((HashMap)value);
	        	getHashMap(newHashMap, returnMap,key);
	        	//it.remove();
	        } else {
	        	returnMap.put(initKey+"."+key, value.toString());
	        	//it.remove(); 
	        	//HashMap	newHashMap	=	dataMap;
	        	HashMap	newHashMap	= new HashMap<String, Object>();	
	        	newHashMap.putAll((HashMap)dataMap);
	        	
	        	getHashMap(dataMap, returnMap,key);
	        	
	        }
	        //it.remove(); // avoids a ConcurrentModificationException
	    }
	//dataMap
	return null;
}
public static HashMap<String, Object> createHashMapFromJsonString(String json) {
//System.out.println(json);
    JsonObject object = (JsonObject) parser.parse(json);
    Set<Map.Entry<String, JsonElement>> set = object.entrySet();
    Iterator<Map.Entry<String, JsonElement>> iterator = set.iterator();
    HashMap<String, Object> map = new HashMap<String, Object>();

    while (iterator.hasNext()) {

        Map.Entry<String, JsonElement> entry = iterator.next();
        String key = entry.getKey();
        JsonElement value = entry.getValue();

        if (null != value) {
            if (!value.isJsonPrimitive()) {
                if (value.isJsonObject()) {
                	HashMap	newHash	=createHashMapFromJsonString(value.toString());
                    map.put(key, newHash);
                    System.out.println("KEY 4 :"+key+":Value:"+newHash);
                    //System.out.println("KEY 4$ :"+str);
                } else if (value.isJsonArray() && value.toString().contains(":")) {

                    List<HashMap<String, Object>> list = new ArrayList();
                    JsonArray array = value.getAsJsonArray();
                    if (null != array) {
                        for (JsonElement element : array) {
                            list.add(createHashMapFromJsonString(element.toString()));
                            System.out.println("Adding List :"+element.toString());
                        }
                        System.out.println("KEY 1 :"+key);
                        map.put(key, list);
                    }
                } else if (value.isJsonArray() && !value.toString().contains(":")) {
                    map.put(key, value.getAsJsonArray());
                    System.out.println("KEY 2 :"+key);
                }
            } else {
            	
                map.put(key, value.getAsString());
                System.out.println("KEY 3 :"+key+":Value : "+value.getAsString());
            }
        }
    }
    return map;
}


public static HashMap<String, Object> createHashMapFromJsonString2(String json) {
	//System.out.println(json);
	    JsonObject object = (JsonObject) parser.parse(json);
	    Set<Map.Entry<String, JsonElement>> set = object.entrySet();
	    Iterator<Map.Entry<String, JsonElement>> iterator = set.iterator();
	    HashMap<String, Object> map = new HashMap<String, Object>();

	    while (iterator.hasNext()) {

	        Map.Entry<String, JsonElement> entry = iterator.next();
	        String key = entry.getKey();
	        JsonElement value = entry.getValue();

	        if (null != value) {
	            if (!value.isJsonPrimitive()) {
	                if (value.isJsonObject()) {
	                	
	                    map.put(key, createHashMapFromJsonString(value.toString()));
	                    System.out.println("KEY 4 :"+key+":Value:"+value.toString());
	                    //System.out.println("KEY 4$ :"+str);
	                } else if (value.isJsonArray() && value.toString().contains(":")) {

	                    List<HashMap<String, Object>> list = new ArrayList();
	                    JsonArray array = value.getAsJsonArray();
	                    if (null != array) {
	                        for (JsonElement element : array) {
	                            list.add(createHashMapFromJsonString(element.toString()));
	                            System.out.println("Adding List :"+element.toString());
	                        }
	                        System.out.println("KEY 1 :"+key);
	                        map.put(key, list);
	                    }
	                } else if (value.isJsonArray() && !value.toString().contains(":")) {
	                    map.put(key, value.getAsJsonArray());
	                    System.out.println("KEY 2 :"+key);
	                }
	            } else {
	                map.put(key, value.getAsString());
	                System.out.println("KEY 3 :"+key);
	            }
	        }
	    }
	    return map;
	}

}