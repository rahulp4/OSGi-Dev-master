package com.intellizones.gateway.webproject;

import javax.servlet.ServletException;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.http.HttpService;
import org.osgi.service.http.NamespaceException;
import org.osgi.util.tracker.ServiceTracker;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

import com.intellizones.gateway.webproject.util.ApplicationDataHolder;

public class Activator implements BundleActivator,ServiceTrackerCustomizer {
	static private BundleContext context;
	
	private ServiceTracker httpTracker;

	public void start(BundleContext context) throws Exception {
		this.context = context;
		httpTracker = new ServiceTracker(context, HttpService.class.getName(), this);
		httpTracker.open();
	}
	
	/*
	  public void start(BundleContext context) throws Exception {
		  this.context	=	context;
	    httpTracker = new ServiceTracker(context, HttpService.class.getName(), null) {
	      public void removedService(ServiceReference reference, Object service) {
	        // HTTP service is no longer available, unregister our servlet...
	        try {
	           ((HttpService) service).unregister("/hello");
	        } catch (IllegalArgumentException exception) {
	           // Ignore; servlet registration probably failed earlier on...
	        }
	      }

	      public Object addingService(ServiceReference reference) {
	        // HTTP service is available, register our servlet...
	        HttpService httpService = (HttpService) this.context.getService(reference);
	        try {
	          httpService.registerServlet("/hello", new HttpRequestHandler(), null, null);
//7030953242
	          //httpService.registerResources("/hello/index","web/index.html",null);
	          httpService.registerResources("/resources", "web", null);
	          //httpService.registerResources("/hello/web/extcss.css","web/extcss.css",null);
//              httpService.registerResources("/goodbye.html","/goodbye.html",null);
//              httpService.registerServlet("/hi", new HelloWorldServlet(), null, null);
//              Servlet adaptedJspServlet = new JspServlet(bc.getBundle(), "/");
//              httpService.registerServlet("/*.jsp", adaptedJspServlet, null, null);	          
	        } catch (Exception exception) {
	          exception.printStackTrace();
	        }
	        return httpService;
	      }
	    };
	    // start tracking all HTTP services...
	    httpTracker.open();
	  }
*/
	
	
	  public void stop(BundleContext context) throws Exception {
	    // stop tracking all HTTP services...
	    httpTracker.close();
	  }
	  
		static public BundleContext getContext() {
			return context;
		}

		@Override
		public Object addingService(ServiceReference reference) {
			// TODO Auto-generated method stub
		HttpService service = (HttpService) context.getService(reference);
      	try {
      		if (service != null) {
				//service.registerServlet("/hello", new HttpRequestHandler(), null, null);
				service.registerServlet("/init", new IntellizonesServlet(), null, null);
				service.registerResources("/files", "/web", null);
				ApplicationDataHolder.getApplicationDataHolder();
			}
		} catch (NamespaceException e) {
			e.printStackTrace();
		} catch (ServletException e){
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}
      	return service;			
		}

		@Override
		public void modifiedService(ServiceReference reference, Object service) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void removedService(ServiceReference reference, Object objService) {
			// TODO Auto-generated method stub
			HttpService service = (HttpService) context.getService(reference);
       		if (service != null) {
			service.unregister("/files");
		}			
		}
	  
}
