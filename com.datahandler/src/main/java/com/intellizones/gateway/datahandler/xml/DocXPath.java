package com.intellizones.gateway.datahandler.xml;

import java.io.File;
import java.io.StringReader;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class DocXPath {

	private InputSource	inputStream	=	null;
	private Map	xPathKeyValue		=	null;
	
	public Map getxPathKeyValue() {
		return xPathKeyValue;
	}
	public void setxPathKeyValue(Map xPathKeyValue) {
		this.xPathKeyValue = xPathKeyValue;
	}
	public DocXPath(String xml){
		inputStream	=	new InputSource(new StringReader(xml));
		xPathKeyValue	=	new HashMap<String, String>();
	}
	
	private DocumentBuilderFactory domFactory =	null; 
	private DocumentBuilder builder =	null;
	private Document doc =	null;
	public Document getDoc() {
		return doc;
	}
	public void setDoc(Document doc) {
		this.doc = doc;
	}

	private XPath xpath =	null;
			
			
	public void initDocumentBuilder(){
		try{
			DocumentBuilderFactory domFactory = DocumentBuilderFactory
		            .newInstance();
		    domFactory.setNamespaceAware(true);
		    builder = domFactory.newDocumentBuilder();
		    
		    doc = builder.parse(inputStream);
		    //Document doc = builder.parse("test.xml");
		    xpath = XPathFactory.newInstance().newXPath();
		} catch (Exception e){
			e.printStackTrace();
		}
		
	}
	
	public void setXPathValue(String xPath,String value){
		try{
			XPathExpression expr = xpath.compile(xPath);
		    //Object result = expr.evaluate(doc, XPathConstants.NODESET);
		    Object result = expr.evaluate(doc, XPathConstants.NODE);
		    //System.out.println(result.getClass());
		    NodeList nodes = (NodeList) result;
		    for (int i = 0; i < nodes.getLength(); i++) {
		    	nodes.item(i).getNodeName();
		    	nodes.item(i).getNextSibling();
		    	//System.out.println("\n");
		    	String nodeValue	=	nodes.item(i).getNodeValue();
		    	nodes.item(i).setNodeValue(value);
		    	
		    }
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void getXPathValue(String xml,List tagList){
		if(tagList!=null){
			ListIterator<String> tagListItearator	=	tagList.listIterator();
			while(tagListItearator.hasNext()){
				String nextTag	=	tagListItearator.next();
				String nodeValue	=	getXPathValue(nextTag);
				if(nodeValue!=null){
					xPathKeyValue.put(nextTag, nodeValue);
				} else {
					//tagList.remove(nextTag);
				}
			}
		}
	}
	
	public String getXPathValue(String xPath){
		try{
	    XPathExpression expr = xpath.compile(xPath);
	    //Object result = expr.evaluate(doc, XPathConstants.NODESET);
	    Object result = expr.evaluate(doc, XPathConstants.NODE);
	    //System.out.println(result.getClass());
	    NodeList nodes = (NodeList) result;
	    for (int i = 0; i < nodes.getLength(); i++) {
	    	
	    	nodes.item(i).getNodeName();
	    	nodes.item(i).getNextSibling();
	    	//System.out.println("\n");
	    	String nodeValue	=	nodes.item(i).getNodeValue();
	    	if(nodeValue==null){
	    		return null;
	    	}else {
	    		return nodeValue;
	    	}
	    	
//	    	System.out.println(nodes.item(i).getNodeValue());
//	    	System.out.println(nodes.item(i).getNodeName());
//	    	System.out.println(nodes.item(i).getNodeType());
//	        System.out.println("NodeItem:"+nodes.item(i));
	    }
		} catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public void writeToFile(){
		try{
			
			String ECLIPSEHOME	=	System.getenv("ECLIPSEHOME");
			System.out.println("\n ECLIPSEHOME : "+ECLIPSEHOME);
			
			String dirLocation	=	"D:/"+File.separator+"data";//ApplicationUtil.PROFILEDIR;//+File.separator+profileId+".xml";
			
			File	profileDir	=	new File(dirLocation);
			boolean isExisting	=	profileDir.isDirectory();
			if(!isExisting){
				profileDir.mkdir();
			}
			
			String fileLoaction	=	dirLocation+File.separator+"my"+".xml";
			
			
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
	
}
