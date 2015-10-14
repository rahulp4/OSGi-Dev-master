package com.intellizones.gateway.webproject.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ApplicationSessionManager {

	public static String REMOTECONFIGCONN	=	"REMOTECONFIGCONN";
	public static HttpSession createNewSession(HttpServletRequest	req,HttpServletResponse resp){
		HttpSession session	=	req.getSession(false);
		if(session!=null){
			session.invalidate();
		}
		session	=	req.getSession(true);
		if(session.isNew()){
			System.out.println("\n New Session Created");
		}
		
		return session;
	}
	
	public static HttpSession getSession(HttpServletRequest req) throws Exception {
		HttpSession session	=	req.getSession(false);
		if(session.isNew()){
			throw new Exception("Invalid Session Object");
		}
		return session;
	}
	
	public static void putInSession(HttpServletRequest req,String key, Object value) throws Exception{
		HttpSession session	=	req.getSession(false);
		session.setAttribute(key, value);
	}

	public static Object getFromSession(HttpServletRequest req,String key) throws Exception{
		HttpSession session	=	req.getSession(false);
		return session.getAttribute(key);
	}
	
	public static void removeFromSession(HttpServletRequest req,String key) throws Exception{
		HttpSession session	=	req.getSession(false);
		if(session!=null){
		if(session.isNew()){
			throw new Exception("Invalid Session Object");
		}
		
		session.removeAttribute(REMOTECONFIGCONN);
		}
	}
}