package com.intellizones.gateway.datastoremanager.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.intellizones.gateway.dataobjects.ConnectionConfigDTO;
import com.intellizones.gateway.dataobjects.exception.AppException;

public class ApplicationDataHolder {

	private static ApplicationDataHolder	applicationDataHolder	=	null;

	private String USERCONFIGFILENAME	=	"user.config";
	private String 	GATEWAYHOME	=	"GATEWAYHOME";
	private String 	APPDATABASEFOLDER="dbfoldername";
	private String 	JSONFILEFOLDERNAMES="json";
	private String 	XMLFILEFOLDERNAMES	=	"xml";
	private String  MAPPINGFOLENAME	= "mappingfilename";	

	
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
		dirLocation	=	"D:\\Home_Auto\\raspberry\\Latest\\Apache-Felix\\apache-ibm\\OSGi-Dev-master\\appconfig\\user.config";
		File file = new File(dirLocation);
		try{
			String lineString	=	null;
			BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
			while ((lineString =bufferedReader.readLine()) != null) {
				String array[]	=	lineString.split("=");
				configData.put(array[0].trim(), array[1].trim());
				
			}			
			

			
		} catch (Exception e){
			e.printStackTrace();
			throw new Exception("File not found at "+dirLocation);
		}
	}

	public void writeJSONToFileSystem(ConnectionConfigDTO configDataDTO,String applicationHome,String jsonString) throws AppException {
		jsonString	=	"{\"id\" : 12345, \"days\" : [ \"Monday\", \"Wednesday\" ], \"person\" : { \"firstName\" : \"David\", \"lastName\" : \"Menoyo\" } }";
		
		applicationHome	=	"D:\\Home_Auto\\raspberry\\Latest\\Apache-Felix\\apache-ibm\\OSGi-Dev-master\\appconfig";
		String fileLocation	=	null;
		String tenentId	=	configDataDTO.getTenantID();
		String dirLocation	=	null;
		try{
		//APPDATABASEFOLDER
		dirLocation	=	applicationHome	+File.separator+getData(APPDATABASEFOLDER)+File.separator+tenentId;
		System.out.println(dirLocation);
		if(checkAppDataFolder(dirLocation,configDataDTO.getTenantID())){
			fileLocation	=	dirLocation+File.separator+configDataDTO.getConnectionId()+".json";
			File file	=	new File(fileLocation);
		
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(jsonString);
			bw.close();
			
		} else {
			//Exception
			throw new IOException();
		}
		} catch(IOException e){
			e.printStackTrace();
			throw new AppException("Could Not Store Data at Location "+fileLocation);
		} catch (Exception e){
			e.printStackTrace();
			throw new AppException("Could Not Store Data at Location "+fileLocation);
			
		}
		
	}
	
	private boolean checkAppDataFolder(String dirLocation,String tenentId){
		//String dirLocation	=	applicationHome	+File.separator+APPDATABASEFOLDER+File.separator+tenentId;
		
		try{
			File	profileDir	=	new File(dirLocation);
			boolean isExisting	=	profileDir.isDirectory();
			if(!isExisting){
				profileDir.mkdir();
			}
			return true;
		} catch (Exception e){
			e.printStackTrace();
			return false;
		}
		
	}
	public void writeDocToFileSystem(ConnectionConfigDTO configDataDTO,String applicationHome,Document doc) throws AppException {
		applicationHome	=	"D:\\Home_Auto\\raspberry\\Latest\\Apache-Felix\\apache-ibm\\OSGi-Dev-master\\appconfig";
		String fileLocation	=	null;
		String tenentId	=	configDataDTO.getTenantID();
		String dirLocation	=	null;
		try{
		//APPDATABASEFOLDER
		dirLocation	=	applicationHome	+File.separator+getData(APPDATABASEFOLDER)+File.separator+tenentId;
		System.out.println(dirLocation);
		if(checkAppDataFolder(dirLocation,configDataDTO.getTenantID())){
			fileLocation	=	dirLocation+File.separator+configDataDTO.getConnectionId()+".xml";
			File file	=	new File(fileLocation);
			
			File	profileDir	=	new File(dirLocation);
			boolean isExisting	=	profileDir.isDirectory();
			if(!isExisting){
				profileDir.mkdir();
			}
			
			String fileLoaction	=	dirLocation+File.separator+configDataDTO.getConnectionId()+".xml";
			
			
			//File	profileDir	=	
			System.out.println("\n ProfilePath : "+fileLoaction);
		
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			//StreamResult result = new StreamResult(new File("D:\\file.xml"));
			StreamResult result = new StreamResult(new File(fileLoaction));
			// Output to console for testing
			// StreamResult result = new StreamResult(System.out);
	 
			transformer.transform(source, result);
			System.out.println("File saved!");
			
		}
		} catch (IOException e){
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}
	
	}
	public static void main(String[] a){
		try{
			ApplicationDataHolder	app =	ApplicationDataHolder.getApplicationDataHolder();
			ConnectionConfigDTO conf	=	new ConnectionConfigDTO();
			conf.setTenantID("mytenat");
			conf.setConnectionId("conid1");
			app.writeJSONToFileSystem(conf,null,null);
			
			
			DocumentBuilderFactory docFactory = null;
			DocumentBuilder docBuilder = null;
			Document doc = null;
			String profileId	=	null;
			
				try{
					docFactory = DocumentBuilderFactory.newInstance();
					docBuilder = docFactory.newDocumentBuilder();
					doc = docBuilder.newDocument();
					Element rootElement = doc.createElement("primaryKey");
					doc.appendChild(rootElement);

				} catch (Exception e){
					throw e;
				}
				
			
			
				app.writeDocToFileSystem(conf, null, doc);
			
		} catch(Exception e){
			e.printStackTrace();
		}
	}
}
