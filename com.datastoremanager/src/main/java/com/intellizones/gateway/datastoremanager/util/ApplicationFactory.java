package com.intellizones.gateway.datastoremanager.util;


public class ApplicationFactory {

	private static ApplicationFactory instance	=	null;
	
	public static ApplicationFactory getInstance(){
		if(instance==null){
			instance	=	new ApplicationFactory();
		} else {
			
		}
		return instance;
	}
	
}
