package com.intellizones.gateway.webproject.util;

import java.io.IOException;
import java.net.URL;

import org.apache.commons.io.IOUtils;
import org.myosgi.DictionaryImpl;
import org.myosgi.DictionaryService;

import com.intellizones.gateway.webproject.Activator;
import com.intellizones.gateway.webproject.httphandler.IHttpHandlers;

public class ApplicationUtil {

		public static synchronized String getTemplate(String elementType){
			elementType = elementType.toLowerCase();
			String template	=	null;

				String snippetLocation = IHttpHandlers.TEMPLATE_LOCATION + elementType + IHttpHandlers.TEMPLATE_EXT;
				URL entry = Activator.getContext().getBundle().getEntry(snippetLocation);
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

		public static void printDebugMessage(String tag,String message){
			System.out.println("\n "+tag+" :: "+message);
		}
}

