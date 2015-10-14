package com.intellizones.gateway.webproject;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.intellizones.gateway.dataobjects.ConnectionConfigDTO;
import com.intellizones.gateway.dataobjects.TestDTO;
import com.intellizones.gateway.datastoremanager.IDataStoreManager;
import com.intellizones.gateway.datastoremanager.XMLDataStoreManager;
import com.intellizones.gateway.jsonhandler.IDataFormatHandler;
import com.intellizones.gateway.jsonhandler.JSONDataFormatHandler;
import com.intellizones.gateway.webproject.httphandler.IHttpHandlers;
import com.intellizones.gateway.webproject.util.ApplicationFactory;
import com.intellizones.gateway.webproject.util.ApplicationUtil;
import com.intellizones.gateway.webproject.util.IINtelliZones;

public class IntellizonesServlet  extends HttpServlet implements IINtelliZones{

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		IHttpHandlers	handler	=	null;
		String action	=	null;
		try{
			TestDTO	test	=	new TestDTO();
			IDataStoreManager xml	=	new XMLDataStoreManager();
			IDataFormatHandler dataFormat	=	new JSONDataFormatHandler();
			//dataFormat.isValidFormat(new ConnectionConfigDTO());
			System.out.println("\n YES THIS IS INSTANTIATED THREEEEEEEE");
			action	=	req.getParameter(IHttpHandlers.HIDDEN_ACTION);
			String nextAction	=	req.getParameter(IHttpHandlers.HIDDEN_NEXTPAGE);				
			if(action==null){
				//Its a login request
				action	=	IHttpHandlers.PAGE_LOGIN_RENDER;
				
			}
			ApplicationUtil.printDebugMessage(this.getClass().getCanonicalName(), "Action is "+action);
			handler	=	ApplicationFactory.getInstance().getHandlerClass(action);
			handler.handleCommonRequest(req, resp,handler,action);
			handler.handleRequest(req, resp, handler, action);
			
			//If the action was for Submit, then after submit request is processed
			//we have to call for rendering next page.
			if(action!=null && action.contains(IHttpHandlers.SUBMIT)){
				handler	=	ApplicationFactory.getInstance().getHandlerClass(nextAction);				
				handler.handleCommonRequest(req, resp,handler,nextAction);
				handler.handleRequest(req,resp,handler,nextAction);				
			}
		} catch(Exception e){
			e.printStackTrace();
			try{
				handler.handleError(req, resp, handler, action, e);
				handler.createFinalPageTemplate(req, resp, handler, action);
				handler.writeHttpResponse(req, resp, null, action);
			} catch (Exception e1){
				
			}
		} catch (Throwable t){
			t.printStackTrace();
		}
	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try{
		
			doGet(req, resp);

		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	protected void handleRequest(HttpServletRequest req, HttpServletResponse resp,String actionId) throws ServletException, IOException {
		String action	=	req.getParameter(IHttpHandlers.HIDDEN_ACTION);
		if(action==null){
			handleInitialPage(req, resp, actionId);
		} else {
			String firstName	=	req.getParameter("firstName");			
			System.out.println("FIRST NAME IS "+firstName);
			System.out.println("REQUES IS"+req);			
			StringBuffer sb = new StringBuffer();
			HttpRequestHandler	handler	=	new HttpRequestHandler();
			String temlate	=	handler.getSnippet(IHttpHandlers.PAGE_MAINPAGE);
			System.out.println("Reponse : "+temlate);
			resp.setContentType("text/html; charset=UTF-8");
			resp.getWriter().write(temlate);		 
		}
	}
	protected void handleInitialPage(HttpServletRequest req, HttpServletResponse resp,String actionId) throws ServletException, IOException {
//		PersonDTO	personDTO	=	new PersonDTO();
//		personDTO.setFirtName("Rahul");
//		personDTO.setLastName("Poddar");
//		
//		String json = new Gson().toJson(personDTO);
//        resp.setContentType("application/json");
//        resp.getWriter().write(json);
        
	}
}
