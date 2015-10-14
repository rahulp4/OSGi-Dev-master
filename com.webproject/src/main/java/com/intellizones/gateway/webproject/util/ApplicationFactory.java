package com.intellizones.gateway.webproject.util;

import com.intellizones.gateway.webproject.httphandler.IHttpHandlers;
import com.intellizones.gateway.webproject.httphandler.IndexPageHandler;
import com.intellizones.gateway.webproject.httphandler.LocalConfigPageHandler;
import com.intellizones.gateway.webproject.httphandler.LoginPageHandler;
import com.intellizones.gateway.webproject.httphandler.RemoteConfigPageHandler;

public class ApplicationFactory {

	private static ApplicationFactory instance	=	null;
	
	public static ApplicationFactory getInstance(){
		if(instance==null){
			instance	=	new ApplicationFactory();
		} else {
			
		}
		
		return instance;
	}
	
	public IHttpHandlers getHandlerClass(String handlerName) throws Exception {
		IHttpHandlers newInstacnce	=	null;
		
		if(handlerName.equals(IHttpHandlers.PAGE_LOGIN_RENDER )|| handlerName.equals(IHttpHandlers.PAGE_LOGIN_SUBMIT)){
			//newInstacnce	=	new IndexPageHandler();
			newInstacnce	=	new LoginPageHandler();
		} else if(handlerName.equals(IHttpHandlers.PAGE_INDEX_RENDER )|| handlerName.equals(IHttpHandlers.PAGE_INDEX_SUBMIT)){
			newInstacnce	=	new IndexPageHandler();
		} else if(handlerName.equals(IHttpHandlers.PAGE_REMOTE_RENDER )|| handlerName.equals(IHttpHandlers.PAGE_REMOTE_SUBMIT)){
			newInstacnce	=	new RemoteConfigPageHandler();
		} else if(handlerName.equals(IHttpHandlers.PAGE_LOCAL_RENDER )|| handlerName.equals(IHttpHandlers.PAGE_LOCAL_SUBMIT)){
			newInstacnce	=	new LocalConfigPageHandler();
		}
		return newInstacnce;
	}
}
