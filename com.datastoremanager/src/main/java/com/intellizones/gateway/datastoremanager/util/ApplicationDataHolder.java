package com.intellizones.gateway.datastoremanager.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.intellizones.gateway.dataobjects.ConnectionConfigDTO;
import com.intellizones.gateway.dataobjects.exception.AppException;

public class ApplicationDataHolder {

	private static ApplicationDataHolder	applicationDataHolder	=	null;

	private String 	APPDATABASEFOLDER="db";
	
	public static ApplicationDataHolder getApplicationDataHolder() throws Exception {
		if(applicationDataHolder==null){
			applicationDataHolder	=	new ApplicationDataHolder();
		}
		return applicationDataHolder;
	}

	
	private void initializeApp() throws Exception{
	
	}

	public void writeJSONToFileSystem(ConnectionConfigDTO configDataDTO,String applicationHome,String jsonString) throws AppException {
		jsonString	=	configDataDTO.getJsonString();//"{\"id\" : 12345, \"days\" : [ \"Monday\", \"Wednesday\" ], \"person\" : { \"firstName\" : \"David\", \"lastName\" : \"Menoyo\" } }";
		
		String fileLocation	=	null;
		String workingDir = System.getProperty("user.dir");
		applicationHome	=	workingDir+File.separator+APPDATABASEFOLDER;
		String tenentId	=	configDataDTO.getTenantID();
		String dirLocation	=	null;
		try{
			dirLocation	=	applicationHome	+File.separator+tenentId;
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
		//applicationHome	=	"D:\\Home_Auto\\raspberry\\Latest\\Apache-Felix\\apache-ibm\\OSGi-Dev-master\\appconfig";
		String workingDir = System.getProperty("user.dir");
		applicationHome	=	workingDir+File.separator+APPDATABASEFOLDER;
				//+File.separator+propFileName;		
		String fileLocation	=	null;
		String tenentId	=	configDataDTO.getTenantID();
		String dirLocation	=	null;

		
		try{
		//APPDATABASEFOLDER
		dirLocation	=	applicationHome	+File.separator+tenentId;
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
			
			
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			//StreamResult result = new StreamResult(new File("D:\\file.xml"));
			StreamResult result = new StreamResult(new File(fileLoaction));
			// Output to console for testing
			// StreamResult result = new StreamResult(System.out);
	 
			transformer.transform(source, result);
			System.out.println("File saved!");
			
			String user	=	AppProperty.getProperty("user");
			System.out.println("\n User is "+user);
			
		}
		} catch (IOException e){
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}
	
	}
	
	
	public void writeMappedSystem(ConnectionConfigDTO configDataDTO,String applicationHome,String jsonString) throws AppException {
		
		String fileLocation	=	null;
		String workingDir = System.getProperty("user.dir");
		applicationHome	=	workingDir+File.separator+APPDATABASEFOLDER;
		String tenentId	=	configDataDTO.getTenantID();
		String dirLocation	=	null;
		FileWriter fstream = null;
		BufferedWriter out = null;
		
		try{
			dirLocation	=	applicationHome	+File.separator+tenentId;
			System.out.println(dirLocation);
				fileLocation	=	dirLocation+File.separator+configDataDTO.getConnectionId()+".map";
				File file	=	new File(fileLocation);
				if (!file.exists()) {
					file.createNewFile();
				}
				fstream = new FileWriter(file.getAbsoluteFile());
				out = new BufferedWriter(fstream);
			    int count = 0;

			    // create your iterator for your map
			    Iterator<Entry<String, String>> it = configDataDTO.getLocAndRemoteFieldMap().entrySet().iterator();

			    while (it.hasNext()) {

			        Map.Entry<String, String> pairs = it.next();
			        System.out.println("Value is " + pairs.getValue());
			        out.write(pairs.getKey()+"="+pairs.getValue() + "\n");
			    }
			    
		} catch(IOException e){
			e.printStackTrace();
			throw new AppException("Could Not Store Data at Location "+fileLocation);
		} catch (Exception e){
			e.printStackTrace();
			throw new AppException("Could Not Store Data at Location "+fileLocation);
		} finally{
			try{
			if(out!=null){
				out.close();
			}
			if(fstream!=null){
				fstream.close();
			}
			} catch (Exception e){
				
			}
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
