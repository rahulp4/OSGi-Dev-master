package com.intellizones.gateway.webproject;

import java.io.IOException;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.myosgi.DictionaryImpl;
import org.myosgi.DictionaryService;

import com.intellizones.gateway.webproject.httphandler.IHttpHandlers;
import com.intellizones.gateway.webproject.util.IINtelliZones;

public class HttpRequestHandler extends HttpServlet implements IINtelliZones{
//java -jar target/osgi-for-mere-mortals-launcher-0.0.1-SNAPSHOT.jar
	@Override
	  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    //resp.getWriter().write("Hello World");
		System.out.println("\n YESDDD");
		System.out.println("\n REQUES "+req);
		System.out.println("\n REQUES "+req.getParameter("profileName"));
		String temlate	=	getSnippet("createprofile");
		printDebugMessage("doGet",temlate);
		resp.getWriter().write(temlate);
	  }
	
	@Override
	  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			doGet(req,resp);
		}
	
	public synchronized String getSnippet(String elementType){
		elementType = elementType.toLowerCase();
		String template	=	null;

			String snippetLocation = IHttpHandlers.TEMPLATE_LOCATION + elementType + IHttpHandlers.TEMPLATE_EXT;
			URL entry = Activator.getContext().getBundle().getEntry(snippetLocation);
			printDebugMessage(this.toString(), "URL Entry "+entry);
			
			if(entry!=null) {
				try {
					template = IOUtils.toString(entry.openStream());
				} catch (IOException e) {
					e.printStackTrace();
					//logger.warn("Cannot load snippet for element type '{}'", elementType, e);
				}
			} 
		return template;
	}

	public void printDebugMessage(String tag,String message){
		System.out.println("\n "+tag+" :: "+message);
		DictionaryService d	=	new DictionaryImpl();
	}
}
