package com.intellizones.gateway.datahandler.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;

public class ApplicationDataHolder {

	private static ApplicationDataHolder	applicationDataHolder	=	null;

	private String USERCONFIGFILENAME	=	"user.config";
	private String 	GATEWAYHOME	=	"GATEWAYHOME";
	
	private HashMap<String, String> configData	=	null;
	
	public String getData(String key) throws Exception {
		if(key==null){
			throw new Exception("Key is null");
		}
		return configData.get(key);
	}
	
	public void addData(String key,String value) throws Exception {
		if(key==null || value == null){
			throw new Exception("Key is null");
		}
		configData.put(key,value);		
	}
	
	public static ApplicationDataHolder getApplicationDataHolder() throws Exception {
			if(applicationDataHolder==null){
				applicationDataHolder	=	new ApplicationDataHolder();
				
				applicationDataHolder.initializeApp();
			}
		return applicationDataHolder;
	}

	
	private void initializeApp() throws Exception{
		configData	=	new HashMap<String, String>();
		String dirLocation	=	System.getProperty(GATEWAYHOME)+File.separator+USERCONFIGFILENAME;		
		System.out.println("\n dirLocation : "+dirLocation);
		
		File file = new File(dirLocation);
		try{
			String lineString	=	null;
			BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
			while ((lineString =bufferedReader.readLine()) != null) {
				String array[]	=	lineString.split("=");
				configData.put(array[0].trim(), array[1].trim());
				
			}			
			

			
		} catch (Exception e){
			throw new Exception("File now found at "+dirLocation);
		}
	}

	public static void main(String[] a){
		try{
			ApplicationDataHolder	app =	ApplicationDataHolder.getApplicationDataHolder();
		} catch(Exception e){
			e.printStackTrace();
		}
	}
}
