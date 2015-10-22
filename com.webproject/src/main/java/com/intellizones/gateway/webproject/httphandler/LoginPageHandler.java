package com.intellizones.gateway.webproject.httphandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.intellizones.gateway.dataobjects.IDataObjects;
import com.intellizones.gateway.datastoremanager.util.AppProperty;
import com.intellizones.gateway.webproject.exception.AppException;
import com.intellizones.gateway.webproject.util.ApplicationSessionManager;
import com.intellizones.gateway.webproject.util.ApplicationUtil;

public class LoginPageHandler extends AbstractHttpRequestHandler {

	private String	CURRENT_PAGE_TEMPLATE	=	"template_login"; 

	
	@Override
	public void handlePageRenderRequest(HttpServletRequest req, HttpServletResponse resp, IHttpHandlers handler,
			String actionId) throws Exception {
		// TODO Auto-generated method stub		
		ApplicationUtil.printDebugMessage(this.getClass().getName(), "In RenderRequest for LocalConfig");
		setAction(IHttpHandlers.PAGE_LOGIN_SUBMIT);
		setAppMode("");
		setMode("");
		setNextPage(IHttpHandlers.PAGE_REMOTE_RENDER);
			
	}

	@Override
	public void handlePageSubmitRequest(HttpServletRequest req, HttpServletResponse resp, IHttpHandlers handler,
			String actionId,IDataObjects dataObject) throws Exception {
		// TODO Auto-generated method stub
		String userName		=	req.getParameter("username");
		String password		=	req.getParameter("password");
		String configUser	=	AppProperty.getProperty("user");
		String configPwd	=	AppProperty.getProperty("password");
		ApplicationUtil.printDebugMessage(this.getClass().getCanonicalName(), "Pwd Config    : "+configPwd);

		System.out.println("\n configPwd "+configPwd);
		if(userName!=null && userName.equals(configUser) ){
			if(password!=null && password.equals(configPwd) ){
				
			} else {
				throw new AppException("User and Password are not valid");
			}
		} else {
			throw new AppException("User and Password are not valid");
		}
		
		ApplicationSessionManager.createNewSession(req, resp);
	}

	@Override
	public void modifyMainPageTemplate(HttpServletRequest req, HttpServletResponse resp, IHttpHandlers handler,
			String actionId) throws Exception {
		// TODO Auto-generated method stub
		String hiddenFiled	=	ApplicationUtil.getTemplate(PAGE_HIDDEN_FIELD);
		setHiddenFieldTemplate(hiddenFiled);		
		
	}

	@Override
	public void modifyEmbeddedChildPageTemplate(HttpServletRequest req, HttpServletResponse resp, IHttpHandlers handler,
			String actionId) throws Exception {
		// TODO Auto-generated method stub
		String embeddedChild	=	ApplicationUtil.getTemplate(CURRENT_PAGE_TEMPLATE);		
		setEmbeddedchildTemplate(embeddedChild);
		
	}

	@Override
	public void modifyHiddenFieldPageTemplate(HttpServletRequest req, HttpServletResponse resp, IHttpHandlers handler,
			String actionId) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void intiHandler(HttpServletRequest req, HttpServletResponse resp, IHttpHandlers handler, String actionId)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IDataObjects validateRequest(HttpServletRequest req, HttpServletResponse resp, IHttpHandlers handler,
			String actionId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


}
