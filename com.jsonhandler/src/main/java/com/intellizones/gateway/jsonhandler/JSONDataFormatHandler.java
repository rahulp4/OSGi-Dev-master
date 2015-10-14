package com.intellizones.gateway.jsonhandler;

import com.google.gson.Gson;
import com.intellizones.gateway.dataobjects.ConnectionConfigDTO;

public class JSONDataFormatHandler implements IDataFormatHandler {

	  private static final Gson gson = new Gson();

	@Override
	public boolean isValidFormat(ConnectionConfigDTO config) {
		// TODO Auto-generated method stub
		try {
			System.out.println("\n String to be validated is :\n"+config.getJsonString());
	          gson.fromJson(config.getJsonString(), Object.class);
	          return true;
	      } catch(com.google.gson.JsonSyntaxException ex) { 
	          return false;
	      }
	}

	public boolean isValidFormat(String config) {
		// TODO Auto-generated method stub
		try {
	          gson.fromJson(config, Object.class);
	          return true;
	      } catch(com.google.gson.JsonSyntaxException ex) {
	    	  ex.printStackTrace();
	          return false;
	      }
	}

	public static void main(String st[]){
		JSONDataFormatHandler jsonHandler	=	new JSONDataFormatHandler();
		String s	=	"{\"id\" : 12345, \"days\" : [ \"Monday\", \"Wednesday\" ], \"person\" : { \"firstName\" : \"David\", \"lastName\" : \"Menoyo\" } }";
		String str	=	"{\n\t\"id\": \"0001\",\n\t\"type\": \"donut\",\n\t\"name\": \"Cake\",\n\t\"ppu\": 0.55,\n\t\"batters\":\n\t\t{\n\t\t\t\"batter\":\n\t\t\t\t[\n\t\t\t\t\t{ \"id\": \"1001\", \"type\": \"Regular\" },\n\t\t\t\t\t{ \"id\": \"1002\", \"type\": \"Chocolate\" },\n\t\t\t\t\t{ \"id\": \"1003\", \"type\": \"Blueberry\" },\n\t\t\t\t\t{ \"id\": \"1004\", \"type\": \"Devil\'s Food\" }\n\t\t\t\t]\n\t\t},\n\t\"topping\":\n\t\t[\n\t\t\t{ \"id\": \"5001\", \"type\": \"None\" },\n\t\t\t{ \"id\": \"5002\", \"type\": \"Glazed\" },\n\t\t\t{ \"id\": \"5005\", \"type\": \"Sugar\" },\n\t\t\t{ \"id\": \"5007\", \"type\": \"Powdered Sugar\" },\n\t\t\t{ \"id\": \"5006\", \"type\": \"Chocolate with Sprinkles\" },\n\t\t\t{ \"id\": \"5003\", \"type\": \"Chocolate\" },\n\t\t\t{ \"id\": \"5004\", \"type\": \"Maple\" }\n\t\t]\n}";		
		jsonHandler.isValidFormat(str);
	}
	
}
