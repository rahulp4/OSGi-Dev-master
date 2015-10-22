package com.intellizones.gateway.datastoremanager.util;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

import com.intellizones.gateway.dataobjects.exception.AppException;

public class AppProperty {
	
	String result = "";
	private static InputStream inputStream;
	private static Properties prop = new Properties();

	private static AppProperty	appProperty	=	null;
	private static Object lock	=	null;
	private static String propFileName = "user.config";
	private static String propAppConfDir = "conf";
//	String appDB = "db";
	
	private AppProperty(){
	
	}
	
	public static AppProperty getAppProperty() throws Exception {
		synchronized (lock) {
			if(appProperty==null){
				appProperty	=	new AppProperty();
				appProperty.initProp();
			} else {
				
			}
		}
		return appProperty;
	}
	
	public static void initProp() throws Exception {
		FileInputStream fis = null;
		try {
			String workingDir = System.getProperty("user.dir");
			String propsFile	=	workingDir+File.separator+propAppConfDir+File.separator+propFileName;
			fis = new FileInputStream(propsFile);
		    prop.load(fis);    
 		} catch (Exception e) {
			e.printStackTrace();
			AppException	appex	=	new AppException();
			appex.setErrorId("PROPERTYLOAD");
			appex.setErrorMessage("Error In Loading Property");
			throw appex;
		} finally {
		      fis.close();
		}
	}

	public static String getProperty(String key) throws Exception {
		if(appProperty==null){
			initProp();
		}
		
		String value	=	(String)prop.get(key);
		if(value==null){
			AppException	appex	=	new AppException();
			appex.setErrorId("PROPERTYLOAD");
			appex.setErrorMessage("Error In Loading Property");
			throw appex;
		}
		
		return value;
	}
}
