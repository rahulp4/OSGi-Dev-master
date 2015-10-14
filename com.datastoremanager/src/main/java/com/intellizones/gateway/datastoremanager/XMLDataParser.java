package com.intellizones.gateway.datastoremanager;

import java.io.File;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

//import com.intellizones.gateway.webproject.util.ApplicationUtil;

public class XMLDataParser {

	DocumentBuilderFactory docFactory = null;
	DocumentBuilder docBuilder = null;
	Document doc = null;
	String profileId	=	null;
	
	public void initDocument() throws Exception{
		try{
			docFactory = DocumentBuilderFactory.newInstance();
			docBuilder = docFactory.newDocumentBuilder();
			doc = docBuilder.newDocument();
		} catch (Exception e){
			throw e;
		}
	}

	public void initEditDocument() throws Exception{
		try{
			docFactory = DocumentBuilderFactory.newInstance();
			docBuilder = docFactory.newDocumentBuilder();
			
		} catch (Exception e){
			throw e;
		}
	}
	
	/*
	public void createDocument(String profileId,String profileName){
		try {
			 
			this.profileId	=	profileId;
			// root elements
			
			Element rootElement = doc.createElement("primaryKey");
			doc.appendChild(rootElement);
			
			//rootElement.setTextContent(profileId);
			
			// set attribute to staff element
			Attr attrProfileId = doc.createAttribute("primaryKey");
			attrProfileId.setValue(profileId);
			rootElement.setAttributeNode(attrProfileId);

			// set attribute to staff element
			Element rootElementProfileName = doc.createElement("profileName");
			rootElement.appendChild(rootElementProfileName);
			rootElementProfileName.setTextContent(profileName);
			
			// write the content into xml file
	 
		  } catch (Exception tfe) {
			tfe.printStackTrace();
		  }		
	}
	
	public boolean addChileNode(String strParentNode,String chileNode,String chiledNodeValue){
		NodeList	nodeList	=	doc.getElementsByTagName(strParentNode);
		
		ApplicationUtil.printDebugMessage(this.toString(), "addChileNode : chileNode       : "+chileNode);
		ApplicationUtil.printDebugMessage(this.toString(), "addChileNode : chiledNodeValue : "+chiledNodeValue);
		boolean isExistingNode	=	false;
		HashMap<String, String> profileDataMap	=	null;
		if(nodeList!=null && nodeList.getLength()>0){
			//First check if child node is existing.
			
			Element test1 = doc.createElement(chileNode);
			Node	parentNode	=	nodeList.item(0);
			Element	parentNodeElement	=	(Element)parentNode;
			
			NodeList childElementNodeList	=	parentNodeElement.getElementsByTagName(chileNode);
			if(childElementNodeList!=null && childElementNodeList.getLength()>0){
				ApplicationUtil.printDebugMessage(this.toString(), "Chile is existing");
				Element childElementNode	=	(Element)childElementNodeList.item(0);
				childElementNode.getParentNode().removeChild(childElementNode);
				isExistingNode	=	true;
			}
			
			//isChildNodeExisting(parentNode.getN,chileNode);
			
			parentNodeElement.appendChild(test1);
			test1.setTextContent(chiledNodeValue);
		} else {
			//NOT IN NODE . ADD THIS NODE
			Element rootElement	=	doc.getDocumentElement();
			Element newParentNode = doc.createElement(strParentNode);
			rootElement.appendChild(newParentNode);
			// set attribute to staff element
			Attr attrswitchoff = doc.createAttribute("id");
			attrswitchoff.setValue("1");
			newParentNode.setAttributeNode(attrswitchoff);
			Element childNode = doc.createElement(chileNode);
			newParentNode.appendChild(childNode);
			childNode.setTextContent(chiledNodeValue);
			//
		}
		ApplicationUtil.printDebugMessage(this.toString(),"Current XMLDocument Is : "+profileDataMap);
		//ItemDataHolder.getItemDataHolder().setProfileDataMap(profileDataMap);
		return true;
		
	}
	
	private boolean isChildNodeExisting(NodeList nodeList,String chileNodeName){
		if(nodeList!=null){
			for(int nodeIndex	=	0	;nodeIndex<nodeList.getLength();nodeIndex++){
				Node childNode	=	nodeList.item(nodeIndex);
				
				ApplicationUtil.printDebugMessage(this.toString(), "Checking Existing Node for "+"chileNodeName with" + childNode.getNodeName());
				if(childNode.getNodeName().equals(chileNodeName)){
					return true;
				}
			}
		}
		
		return false;
	}
	public static File[] getProfileList(){
		
		//String dirLocation	=	System.getenv("ECLIPSEHOME")+File.separator+"data";//ApplicationUtil.PROFILEDIR;//+File.separator+profileId+".xml";
		String dirLocation	=	"D:\\"+File.separator+"data";//ApplicationUtil.PROFILEDIR;//+File.separator+profileId+"
		
		File folder = new File(dirLocation);
		File[] listOfFiles = folder.listFiles();

	    for (int i = 0; i < listOfFiles.length; i++) {
	      if (listOfFiles[i].isFile()) {
	        System.out.println("File " + listOfFiles[i].getName());
	        
	      } else if (listOfFiles[i].isDirectory()) {
	        System.out.println("Directory " + listOfFiles[i].getName());
	      }
	    }
	    
	    return listOfFiles;
	}
	
	
	public void writeToFile(){
		try{
			
			String ECLIPSEHOME	=	System.getenv("ECLIPSEHOME");
			System.out.println("\n ECLIPSEHOME : "+ECLIPSEHOME);
			
			String dirLocation	=	ECLIPSEHOME+File.separator+"data";//ApplicationUtil.PROFILEDIR;//+File.separator+profileId+".xml";
			
			File	profileDir	=	new File(dirLocation);
			boolean isExisting	=	profileDir.isDirectory();
			if(!isExisting){
				profileDir.mkdir();
			}
			
			String fileLoaction	=	dirLocation+File.separator+profileId+".xml";
			
			
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
		} catch (TransformerException e){
			e.printStackTrace();
		}
	
	}

	public void updateToFile(){
		try{
			
			String ECLIPSEHOME	=	System.getenv("ECLIPSEHOME");
			System.out.println("\n ECLIPSEHOME : "+ECLIPSEHOME);
			
			String dirLocation	=	ECLIPSEHOME+File.separator+"data";//ApplicationUtil.PROFILEDIR;//+File.separator+profileId+".xml";
			String fileLoaction	=	dirLocation+File.separator+profileId+".xml";
			
			File profileFile	=	new File(fileLoaction);
			profileFile.delete();
			
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
 
			System.out.println("File Update!");
		} catch (TransformerException e){
			e.printStackTrace();
		}
	
	}

	public void updateDocumentObject(String nodeId,String nodeBinding,String nodeState, String nodeType,String item){

//		AdminEvent-0 : >[mosquitto:/raspberry:command:ON:OL1N0L2N1S1100000000
//		AdminEvent-1 : >[mosquitto:/raspberry:command:OFF:OL1N0L2N1S1000000000
//		AdminEvent Command:  : ON
		ApplicationUtil.printDebugMessage(this.toString(),"Submitted Node State "+nodeState);
		String bindingState	=	getBindingConfig(nodeBinding,nodeState);
		//Node02 (Type=SwitchItem, State=OFF)
		String itemDetails	=	item.toString();
		String itemType	=	itemDetails.substring(itemDetails.indexOf('=')+1, itemDetails.indexOf(','));
		ApplicationUtil.printDebugMessage(this.toString(), "Type is : "+itemType);
		ApplicationUtil.printDebugMessage(this.toString(), "Binding State : "+bindingState);
		
		if(bindingState!=null){
			addChileNode(itemType, nodeId,bindingState);
			//writeToFile();
		}
		
		
	}
	
	private String getBindingConfig(String nodeBinding,String state){
		String[] configurationStrings = nodeBinding.split("],");
		int tokenLength	=	configurationStrings.length;
		for(int tokenCount=0;tokenCount<tokenLength;tokenCount++){
			if(configurationStrings[tokenCount].contains(state)){
				return configurationStrings[tokenCount];
			} 
		}
		return null;
	}
	

//	  public void readAndUpdateProfileDataIntoMemory(HttpServletRequest req,String fileName) {
//		  HashMap<String, String> profileDataMap	=	null;
//		  profileId	=	fileName;
//		  
//		  boolean isSuccess	=	false;
//		  try {
//	    	
//			String fileLocation	=	System.getenv("ECLIPSEHOME")+File.separator+ApplicationUtil.PROFILEDIR+File.separator+fileName+".xml";
//			
//			File fXmlFile = new File(fileLocation);
//			
//			doc = docBuilder.parse(fXmlFile);
//	 
//			doc.getDocumentElement().normalize();		 
//			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
//			NodeList nList = doc.getElementsByTagName("profileId");
//			for (int temp = 0; temp < nList.getLength(); temp++) {
//				Node nNode = nList.item(temp);
//				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
//					Element eElement = (Element) nNode;
//					System.out.println("Profile id : " + eElement.getAttribute("profileId"));
//					NodeList	profileNodeChild	=	eElement.getChildNodes();
//					for(int nodeIndex=0;nodeIndex<profileNodeChild.getLength();nodeIndex++){
//						Node profileIdChildNode	=	profileNodeChild.item(nodeIndex);
//						if (profileIdChildNode.getNodeType() == Node.ELEMENT_NODE) {
//							Element profileIdChildElement = (Element) profileIdChildNode;
//							profileDataMap	=	processProfileDataMap(profileIdChildElement);
//							ItemDataHolder.getItemDataHolder().setProfileDataMap(profileDataMap);
//							isSuccess	=	true;
//							System.out.println("\nCurrent Profile Element :" + profileIdChildElement.getNodeName());
//							//This will have all child of profileId such as ProfileName, SwitchOn,SwitchOff,SwitchItem etc.
//							
//						}
//					}
//				}
//			}
//	    } catch (Exception e) {
//		e.printStackTrace();
//	    }
//		if(isSuccess){
//			//ApplicationUtil.cleanHttpSessionForNewProfile(req);
//		}
//	  }
//	 
	  private static HashMap<String, String> processProfileDataMap(Element rootElement){
		  	HashMap	profileDataMap	=	new HashMap<String, String>();
			NodeList	profileNodeChild	=	rootElement.getChildNodes();
			String nodeTypeName	=	rootElement.getNodeName();
			
			for(int nodeIndex=0;nodeIndex<profileNodeChild.getLength();nodeIndex++){
				Node profileIdChildNode	=	profileNodeChild.item(nodeIndex);
				
				profileDataMap.put(profileIdChildNode.getNodeName(), profileIdChildNode.getTextContent()+"~"+nodeTypeName);
				
				ApplicationUtil.printDebugMessage("XMLDocumentDomImpl", " Node Name "+profileIdChildNode.getNodeName());
				ApplicationUtil.printDebugMessage("XMLDocumentDomImpl", " Node Value"+profileIdChildNode.getTextContent()+"~"+nodeTypeName);
			}		  
		  
		  return profileDataMap;
	  }
	  */
		public static void main(String str[]){
			try{
				/*
				File fileDir	=	new File("D:\\rahul1");
				
				boolean is	=	fileDir.isDirectory();
				if(!is){
					fileDir.mkdir();
				}
				System.out.println("\n Output : "+is);
				*/
				
				
				XMLDataParser x	=	new XMLDataParser();
//				x.initEditDocument();
//				x.readAndUpdateProfileDataIntoMemory(null,"eve");
				x.initDocument();
				//x.createDocument("morning","morning");
				//x.addChileNode("SwitchTTT","Node01"+"~"+">[mosquitto:/raspberry:command:ON:OL1N0L2N1S1100000000");
				//x.addChileNode("primaryKey","deviceLoc","Pune");
				System.out.println("\n Output : "+x.toString());
				//x.writeToFile();
			} catch (Exception e){
				e.printStackTrace();
			}
		}
	

}
