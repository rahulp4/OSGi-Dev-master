package com.intellizones.gateway.webproject.httphandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.intellizones.gateway.dataobjects.IDataObjects;
import com.intellizones.gateway.webproject.exception.AppValidationException;
import com.intellizones.gateway.webproject.util.ApplicationUtil;

public abstract class AbstractHttpRequestHandler implements IHttpHandlers {

	private String PAGE_ERROR	=	"template_error";
	private String mainPageTemplate	=	null;
	
	private String hiddenFieldTemplate	=	null;
	
	private String embeddedchildTemplate	=	null;

	private String action	=	null;
	
	private String nextPage	=	null;
	
	public String getNextPage() {
		return nextPage;
	}

	public void setNextPage(String nextPage) {
		this.nextPage = nextPage;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getAppMode() {
		return appMode;
	}

	public void setAppMode(String appMode) {
		this.appMode = appMode;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	private String appMode	=	null;
	private String mode		=	null;
	
	public String getEmbeddedchildTemplate() {
		return embeddedchildTemplate;
	}

	public void setEmbeddedchildTemplate(String embeddedchildTemplate) {
		this.embeddedchildTemplate = embeddedchildTemplate;
	}

	public String getHiddenFieldTemplate() {
		return hiddenFieldTemplate;
	}

	public void setHiddenFieldTemplate(String hiddenFieldTemplate) {
		this.hiddenFieldTemplate = hiddenFieldTemplate;
	}

	public String getMainPageTemplate() {
		return mainPageTemplate;
	}

	public void setMainPageTemplate(String mainPageTemplate) {
		this.mainPageTemplate = mainPageTemplate;
	}

	@Override
	public void handleCommonRequest(HttpServletRequest req, HttpServletResponse resp,IHttpHandlers handler,String actionId) throws Exception {
		// TODO Auto-generated method stub
		String mainPage	=	ApplicationUtil.getTemplate(IHttpHandlers.PAGE_MAINPAGE);
		//ApplicationUtil.printDebugMessage(this.toString(),mainPage);
		setMainPageTemplate(mainPage);

		String hiddenField	=	ApplicationUtil.getTemplate(IHttpHandlers.PAGE_HIDDEN_FIELD);
		//ApplicationUtil.printDebugMessage(this.toString(),mainPage);
		setHiddenFieldTemplate(hiddenField);
//		System.out.println("\n commonRequest -----");
//		System.out.println(mainPage);
//		System.out.println("\n commonRequest -----End");
	}
	
	@Override
	public void handleRequest(HttpServletRequest req,HttpServletResponse resp,IHttpHandlers handler,String actionId) throws Exception {
		try{
		if(actionId==null || actionId.trim().equals("")){
			handler.handlePageRenderRequest(req, resp,handler,actionId);
		} else if(actionId.contains(IHttpHandlers.RENDER)){
			//ApplicationUtil.printDebugMessage(this.toString(),"handleRequest for Render : "+actionId);
			handler.modifyMainPageTemplate(req, resp, handler, actionId);
			handler.modifyHiddenFieldPageTemplate(req, resp, handler, actionId);
			handler.modifyEmbeddedChildPageTemplate(req, resp, handler, actionId);
			handler.handlePageRenderRequest(req, resp,handler,actionId);
			handler.createFinalPageTemplate(req, resp, handler, actionId);
			handler.writeHttpResponse(req, resp, null, actionId);
		} else if(actionId.contains(IHttpHandlers.SUBMIT)){
			doCommonValidtions(req, resp,handler,actionId);
			IDataObjects dataObject	=	handler.validateRequest(req, resp, handler, actionId);
			handler.handlePageSubmitRequest(req, resp,handler,actionId,dataObject);
		} 
		} catch (AppValidationException e){
			throw e;
		
		} catch (Exception e){
			e.printStackTrace();
			throw e;
		}
		
	}
	
	@Override
	public void writeHttpResponse(HttpServletRequest	req,HttpServletResponse resp,String responseString,String actionId) throws Exception {
		resp.setContentType("text/html; charset=UTF-8");
        resp.getWriter().write(mainPageTemplate);		
	}
	
	@Override
	public void createFinalPageTemplate(HttpServletRequest req, HttpServletResponse resp, IHttpHandlers handler,
			String actionId) throws Exception {
		// TODO Auto-generated method stub
		hiddenFieldTemplate	=	StringUtils.replace(hiddenFieldTemplate,"%action%",getAction());
		hiddenFieldTemplate	=	StringUtils.replace(hiddenFieldTemplate,"%appmode%",getAppMode());
		hiddenFieldTemplate	=	StringUtils.replace(hiddenFieldTemplate,"%mode%",getMode());
		hiddenFieldTemplate	=	StringUtils.replace(hiddenFieldTemplate,"%nextpage%",getNextPage());
		mainPageTemplate	=	StringUtils.replace(mainPageTemplate, "%embedchild%", embeddedchildTemplate);
		mainPageTemplate	=	StringUtils.replace(mainPageTemplate, "%hiddenfields%", hiddenFieldTemplate);
	}
	
	
	@Override
	public void handleError(HttpServletRequest	req,HttpServletResponse resp,IHttpHandlers handler,String actionId, Exception ex) throws Exception {
		String mainPage	=	ApplicationUtil.getTemplate(IHttpHandlers.PAGE_MAINPAGE);
		setMainPageTemplate(mainPage);

		String hiddenField	=	ApplicationUtil.getTemplate(IHttpHandlers.PAGE_HIDDEN_FIELD);
		setHiddenFieldTemplate(hiddenField);
		embeddedchildTemplate	=	ApplicationUtil.getTemplate(PAGE_ERROR);
		embeddedchildTemplate	=	StringUtils.replace(embeddedchildTemplate, "%errormessage%", ex.getMessage());		
	}

	public void doCommonValidtions(HttpServletRequest	req,HttpServletResponse resp,IHttpHandlers handler,String actionId) throws Exception {
		
	}
}
