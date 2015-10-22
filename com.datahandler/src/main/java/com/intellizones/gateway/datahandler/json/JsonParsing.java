package com.intellizones.gateway.datahandler.json;
//http://www.htmlescape.net/javaescape_tool.html
//https://adobe.github.io/Spry/samples/data_region/JSONDataSetSample.html
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

//import org.apache.commons.lang3.StringEscapeUtils;
import org.json.JSONObject;
import org.json.XML;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.intellizones.gateway.datahandler.xml.DocXPath;
import com.intellizones.gateway.datahandler.xml.SAXCreateXPath;
import com.intellizones.gateway.dataobjects.ConnectionConfigDTO;
import com.intellizones.gateway.dataobjects.exception.AppValidationException;

public class JsonParsing {
static JsonParser parser = new JsonParser();

public SAXCreateXPath	xpathCreate	=	null;

public DocXPath	docXPath	=	null;

public DocXPath getDocXPath() {
	return docXPath;
}

public void setDocXPath(DocXPath docXPath) {
	this.docXPath = docXPath;
}
public String finalXML	=	null;
public String getFinalXML() {
	return finalXML;
}

public void setFinalXML(String finalXML) {
	this.finalXML = finalXML;
}

public JsonParsing(){
	
}

public static void main (String[] str){
	try{
		String strr	=	"{\"id\" : 12345, \"days\" : [ \"Monday\", \"Wednesday\" ], \"person\" : { \"firstName\" : \"David\", \"lastName\" : \"Menoyo\" } }";
		//"{"id" : 12345, "days" : [ "Monday", "Wednesday" ], "person" : { "firstName" : "David", "lastName" : "Menoyo" } }";
		//String st	=	"{\n\t\"id\": \"0001\",\n\t\"type\": \"donut\",\n\t\"name\": \"Cake\",\n\t\"ppu\": 0.55,\n\t\"batters\":\n\t\t{\n\t\t\t\"batter\":\n\t\t\t\t[\n\t\t\t\t\t{ \"id\": \"1001\", \"type\": \"Regular\" },\n\t\t\t\t\t{ \"id\": \"1002\", \"type\": \"Chocolate\" },\n\t\t\t\t\t{ \"id\": \"1003\", \"type\": \"Blueberry\" },\n\t\t\t\t\t{ \"id\": \"1004\", \"type\": \"Devil\'s Food\" }\n\t\t\t\t]\n\t\t},\n\t\"topping\":\n\t\t[\n\t\t\t{ \"id\": \"5001\", \"type\": \"None\" },\n\t\t\t{ \"id\": \"5002\", \"type\": \"Glazed\" },\n\t\t\t{ \"id\": \"5005\", \"type\": \"Sugar\" },\n\t\t\t{ \"id\": \"5007\", \"type\": \"Powdered Sugar\" },\n\t\t\t{ \"id\": \"5006\", \"type\": \"Chocolate with Sprinkles\" },\n\t\t\t{ \"id\": \"5003\", \"type\": \"Chocolate\" },\n\t\t\t{ \"id\": \"5004\", \"type\": \"Maple\" }\n\t\t]\n}";
	
		//String st="{\n\t\"id\": \"0001\",\n\t\"type\": \"donut\",\n\t\"name\": \"Cake\",\n\t\"ppu\": 0.55,\n\t\"batters\":\n\t\t{\n\t\t\t\"batter\":{ \"id\": \"1004\", \"type\": \"Devil\'s Food\" }\n\t\t},\n\t\"topping\":\n\t\t\t{ \"id\": \"5004\", \"type\": \"Maple\" }\n}";
	
		//String st ="{\n\t\"id\": \"0001\",\n\t\"type\": \"donut\",\n\t\"name\": \"Cake\",\n\t\"ppu\": 0.55,\n\t\"batters\":\n\t\t{\n\t\t\t\"batter\":{ \"id\": \"1004\", \"type\": \"Devil\'s Food\" ,\"temp\": {\"myid\":\"rahul\"}}\n\t\t},\n\t\"topping\":\n\t\t\t{ \"id\": \"5004\", \"type\": \"Maple\" }\n}";
	
		String st="{\n\t\"id\": \"0001\",\n\t\"type\": \"donut\",\n\t\"name\": \"Cake\",\n\t\"ppu\": 0.55,\n\t\"batters\":\n\t\t{\n\t\t\t\"batter\":\n\t\t\t\t[\n\t\t\t\t\t{ \"id\": \"1001\", \"type\": \"Regular\" },\n\t\t\t\t\t{ \"id\": \"1002\", \"type\": \"Chocolate\" },\n\t\t\t\t\t{ \"id\": \"1003\", \"type\": \"Blueberry\" },\n\t\t\t\t\t{ \"id\": \"1004\", \"type\": \"Devil\'s Food\" }\n\t\t\t\t]\n\t\t},\n\t\"topping\":\n\t\t[\n\t\t\t{ \"id\": \"5001\", \"type\": \"None\" },\n\t\t\t{ \"id\": \"5002\", \"type\": \"Glazed\" },\n\t\t\t{ \"id\": \"5005\", \"type\": \"Sugar\" },\n\t\t\t{ \"id\": \"5007\", \"type\": \"Powdered Sugar\" },\n\t\t\t{ \"id\": \"5006\", \"type\": \"Chocolate with Sprinkles\" },\n\t\t\t{ \"id\": \"5003\", \"type\": \"Chocolate\" },\n\t\t\t{ \"id\": \"5004\", \"type\": \"Maple\" }\n\t\t]\n}";	
		
		//String st= "\t{\n\t\tcolor: \"red\",\n\t\tvalue: \"#f00\"\n\t},\n\t{\n\t\tcolor: \"green\",\n\t\tvalue: \"#0f0\"\n\t},\n\t{\n\t\tcolor: \"blue\",\n\t\tvalue: \"#00f\"\n\t},\n\t{\n\t\tcolor: \"cyan\",\n\t\tvalue: \"#0ff\"\n\t},\n\t{\n\t\tcolor: \"magenta\",\n\t\tvalue: \"#f0f\"\n\t},\n\t{\n\t\tcolor: \"yellow\",\n\t\tvalue: \"#ff0\"\n\t},\n\t{\n\t\tcolor: \"black\",\n\t\tvalue: \"#000\"\n\t}\n";
		
		JsonParsing j	=new JsonParsing();
		
		ConnectionConfigDTO f	=	new ConnectionConfigDTO();
		f.setJsonString(st);
		String xml = j.convertJSONToXML(f); 
				//XML.toString(new JSONObject(st));
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
//		docXPath.setXPathValue("//rootiot/ppu", "10.01");
		
		docXPath.getXPathValue(xml,listTag);
		/**Get Xpath Key and Value FinalList**/
		HashMap	h	=	(HashMap<String, String> )docXPath.getxPathKeyValue();
		
		
		Set s	=	h.keySet();
		Iterator	iterateFinal	=	s.iterator();
		System.out.println("Final List\n");
		while(iterateFinal.hasNext()){
			String key	=	(String)iterateFinal.next();
			String value=	(String)h.get(key);
			//System.out.println(key +":"+value);
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
	        //System.out.println(pair.getKey() + " = " + pair.getValue());
	        String key	=	(String)pair.getKey();
	        Object value	=	pair.getValue();
	        if(value instanceof HashMap){
	        	//System.out.println("\n Values : As HashMap");
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
                    //System.out.println("KEY 4 :"+key+":Value:"+newHash);
                    //System.out.println("KEY 4$ :"+str);
                } else if (value.isJsonArray() && value.toString().contains(":")) {

                    List<HashMap<String, Object>> list = new ArrayList();
                    JsonArray array = value.getAsJsonArray();
                    if (null != array) {
                        for (JsonElement element : array) {
                            list.add(createHashMapFromJsonString(element.toString()));
                            //System.out.println("Adding List :"+element.toString());
                        }
                        //System.out.println("KEY 1 :"+key);
                        map.put(key, list);
                    }
                } else if (value.isJsonArray() && !value.toString().contains(":")) {
                    map.put(key, value.getAsJsonArray());
                    //System.out.println("KEY 2 :"+key);
                }
            } else {
            	
                map.put(key, value.getAsString());
                //System.out.println("KEY 3 :"+key+":Value : "+value.getAsString());
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
	                            //System.out.println("Adding List :"+element.toString());
	                        }
	                        //System.out.println("KEY 1 :"+key);
	                        map.put(key, list);
	                    }
	                } else if (value.isJsonArray() && !value.toString().contains(":")) {
	                    map.put(key, value.getAsJsonArray());
	                    //System.out.println("KEY 2 :"+key);
	                }
	            } else {
	                map.put(key, value.getAsString());
	                //System.out.println("KEY 3 :"+key);
	            }
	        }
	    }
	    return map;
	}

	public boolean isValidFormat(ConnectionConfigDTO config) {
		// TODO Auto-generated method stub
		try {
			Gson gson = new Gson();
			//System.out.println("\n String to be validated is :\n"+config.getJsonString());
	        gson.fromJson(config.getJsonString(), Object.class);
	        
	        return true;
	      } catch(com.google.gson.JsonSyntaxException ex) { 
	        return false;
	      }
	}
	
	public String convertJSONToXML(ConnectionConfigDTO config)  throws AppValidationException{
		// TODO Auto-generated method stub
		String xml = null;
		try {
			
			
			//System.out.println("\n Coverting to XML :-: "+config.getJsonString());
			
			String re	=	config.getJsonString().replaceAll("\\s+","");
			xml = XML.toString( new JSONObject(re));
			
			System.out.println("XML IS: \n"+xml);
			
			/*
			//System.out.println("Repalce:Escape \n"+XML.escape(re));
			System.out.println("Repalce:XML \n"+XML.toString(new JSONObject(re)));
			
			
			String convert	=	XML.escape(config.getJsonString());
			String s2 = convert;//.replaceAll("\r\n\t", "");
			
			System.out.println("\n Escaped: \n"+s2);
			xml = XML.toString( new JSONObject(config.getJsonString()));
			
			System.out.println("\n Second Escaped: \n"+xml);
			*/
			//JSONObject	s=	new JSONObject(convert);
			
		} catch(com.google.gson.JsonSyntaxException ex) {
			ex.printStackTrace();
			AppValidationException	app	=	new AppValidationException();
			app.setErrorId("JSONCONVERT");
			app.setErrorMessage("Could not convert to String. Invalid JSON");
			throw app;
	    }
		return xml;
	}

	public HashMap<String, String> processXPathAndValues(String xml) throws Exception{
		HashMap	h	=	null;
		try{
			finalXML	=	"<rootiot>"+xml+"</rootiot>";
			xpathCreate	=	new SAXCreateXPath();
			List listTag	=	xpathCreate.parseXML(finalXML);
			
			//check if there was ny duplicates
			if(xpathCreate.getDuplicateTageList()!=null && 
					xpathCreate.getDuplicateTageList().size()>0){
				AppValidationException	app	=	new AppValidationException();
				app.setErrorId("JSONCONVERT");
				app.setErrorMessage("JSON Message as Duplicates for "+xpathCreate.getDuplicateTageList().get(0));
				throw app;
				
			}
			docXPath	=	new DocXPath(finalXML);
			docXPath.initDocumentBuilder();
			//docXPath.setXPathValue("//rootiot/ppu", "10.01");
			
			docXPath.getXPathValue(finalXML,listTag);
			h	=	(HashMap<String, String> )docXPath.getxPathKeyValue();
			Set s	=	h.keySet();
			Iterator	iterateFinal	=	s.iterator();
			System.out.println("Final List\n");
			while(iterateFinal.hasNext()){
				String key	=	(String)iterateFinal.next();
				String value=	(String)h.get(key);
				System.out.println(key +":"+value);
			}
			//docXPath.writeToFile();
			
		} catch (Exception e){
			AppValidationException	app	=	new AppValidationException();
			app.setErrorId("JSONCONVERT");
			app.setErrorMessage("Could not convert to String. Invalid JSON");
			throw app;
		}
		
		return h;
	}
}