package com.intellizones.gateway.webproject.httphandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.intellizones.gateway.dataobjects.IDataObjects;
import com.intellizones.gateway.webproject.HttpRequestHandler;

public class IndexPageHandler extends AbstractHttpRequestHandler {

	@Override
	public void handlePageRenderRequest(HttpServletRequest req, HttpServletResponse resp, IHttpHandlers handler,
			String actionId) throws Exception {
		// TODO Auto-generated method stub
		
		String firstName	=	req.getParameter("firstName");
		
		System.out.println("FIRST NAME IS "+firstName);
		System.out.println("REQUES IS"+req);
		
		StringBuffer sb = new StringBuffer();
		HttpRequestHandler	basicHandler	=	new HttpRequestHandler();
		String temlate	=	basicHandler.getSnippet(IHttpHandlers.PAGE_MAINPAGE);
		System.out.println("Reponse : "+temlate);
		resp.setContentType("text/html; charset=UTF-8");
		resp.getWriter().write(temlate);
		
	}

	@Override
	public void handlePageSubmitRequest(HttpServletRequest req, HttpServletResponse resp, IHttpHandlers handler,
			String actionId,IDataObjects dataObject) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modifyMainPageTemplate(HttpServletRequest req, HttpServletResponse resp, IHttpHandlers handler,
			String actionId) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modifyEmbeddedChildPageTemplate(HttpServletRequest req, HttpServletResponse resp, IHttpHandlers handler,
			String actionId) throws Exception {
		// TODO Auto-generated method stub
		
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
