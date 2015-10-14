package com.intellizones.gateway.datahandler.xml;

import java.io.File;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import com.intellizones.gateway.dataobjects.exception.AppValidationException;

public class SAXCreateXPath extends DefaultHandler {

    // map of all encountered tags and their running count
    private Map<String, Integer> tagCount;
    // keep track of the succession of elements
    private Stack<String> tags;
    
    private List<String> duplicateTageList	=	new ArrayList();
    
    
    public List<String> getDuplicateTageList() {
		return duplicateTageList;
	}
	public void setDuplicateTageList(List<String> duplicateTageList) {
		this.duplicateTageList = duplicateTageList;
	}
	public Stack<String> getTags() {
		return tags;
	}
	public void setTags(Stack<String> tags) {
		this.tags = tags;
	}

	private List<String> tagList	=	new ArrayList<String>();

    public List<String> getTagList() {
		return tagList;
	}
	public void setTagList(List<String> tagList) {
		this.tagList = tagList;
	}

	// set to the tag name of the recently closed tag
    String lastClosedTag;

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
    	
    	//System.out.println("C\n"+ch[0]+":"+start+":"+length);
    }
    /**
     * Construct the XPath expression
     */
    private String getCurrentXPath() {
        String str = "//";
        boolean first = true;
        for (String tag : tags) {
            if (first)
                str = str + tag;
            else
                str = str + "/" + tag;
            //str += "["+tagCount.get(tag)+"]";
            //str += "["+tagCount.get(tag)+"]";
            first = false;
        }
        return str;
    }

    @Override
    public void startDocument() throws SAXException {
        tags = new Stack();
        tagCount = new HashMap<String, Integer>();
        
    }

    @Override
    public void startElement (String namespaceURI, String localName, String qName, Attributes atts)
        throws SAXException
    {
        boolean isRepeatElement = false;

        if (tagCount.get(localName) == null) {
            tagCount.put(localName, 0);
        } else {
            tagCount.put(localName, 1 + tagCount.get(localName));
        }

        if (lastClosedTag != null) {
            // an element was recently closed ...
            if (lastClosedTag.equals(localName)) {
                // ... and it's the same as the current one
                isRepeatElement = true;
            } else {
                // ... but it's different from the current one, so discard it
                tags.pop();
            }
        }

        // if it's not the same element, add the new element and zero count to list
        if (! isRepeatElement) {
            tags.push(localName);
        }

        String currentXPath	=	getCurrentXPath();
        if(tagList.contains(currentXPath)){
        	//System.out.println("\n Duplicate found for "+currentXPath);
        	//throw new AppValidationException("JSON Has Duplicate fields  for "+currentXPath); 
        	duplicateTageList.add(currentXPath);
        } else {
        	//System.out.println("\n Adding :"+currentXPath);
        	//System.out.println("\n Instance Check :"+tagList);
        	
        	tagList.add(currentXPath);
        	setTagList(tagList);
        }
        		
        //System.out.println(getCurrentXPath());
        lastClosedTag = null;
    }

    @Override
    public void endElement (String uri, String localName, String qName) throws SAXException {
        // if two tags are closed in succession (without an intermediate opening tag),
        // then the information about the deeper nested one is discarded
        if (lastClosedTag != null) {
            tags.pop();
        }
        lastClosedTag = localName;
    }

    public static void main (String[] args) throws Exception {
        if (args.length < 1) {
            System.err.println("Usage: SAXCreateXPath <file.xml>");
            System.exit(1);
        }

        // Create a JAXP SAXParserFactory and configure it
        SAXParserFactory spf = SAXParserFactory.newInstance();
        spf.setNamespaceAware(true);
        spf.setValidating(false);

        // Create a JAXP SAXParser
        SAXParser saxParser = spf.newSAXParser();

        // Get the encapsulated SAX XMLReader
        XMLReader xmlReader = saxParser.getXMLReader();

        // Set the ContentHandler of the XMLReader
        xmlReader.setContentHandler(new SAXCreateXPath());

        String filename = args[0];
        String path = new File(filename).getAbsolutePath();
        if (File.separatorChar != '/') {
            path = path.replace(File.separatorChar, '/');
        }
        if (!path.startsWith("/")) {
            path = "/" + path;
        }

        // Tell the XMLReader to parse the XML document
        xmlReader.parse("file:"+path);
        
    }

    public List parseXML(String xmlstring) throws Exception {
//        if (args.length < 1) {
//            System.err.println("Usage: SAXCreateXPath <file.xml>");
//            System.exit(1);
//        }

        // Create a JAXP SAXParserFactory and configure it
        SAXParserFactory spf = SAXParserFactory.newInstance();
        spf.setNamespaceAware(true);
        spf.setValidating(false);

        // Create a JAXP SAXParser
        SAXParser saxParser = spf.newSAXParser();

        // Get the encapsulated SAX XMLReader
        XMLReader xmlReader = saxParser.getXMLReader();

        SAXCreateXPath	xPath	= new SAXCreateXPath();
        
        // Set the ContentHandler of the XMLReader
        xmlReader.setContentHandler(xPath);

        InputSource	inputstream	=	new InputSource(new StringReader(xmlstring));
        
        xmlReader.parse(inputstream);
        List tagList	=	xPath.getTagList();
        //System.out.println("\n\n TAG LIST SIZE IS "+tagList.size());
        return tagList;
//        String filename = args[0];
//        String path = new File(filename).getAbsolutePath();
//        if (File.separatorChar != '/') {
//            path = path.replace(File.separatorChar, '/');
//        }
//        if (!path.startsWith("/")) {
//            path = "/" + path;
//        }
//
//        // Tell the XMLReader to parse the XML document
//        xmlReader.parse("file:"+path);
        
    }

}
