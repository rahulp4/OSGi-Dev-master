package com.intellizones.gateway.webproject.httphandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.intellizones.gateway.dataobjects.IDataObjects;

public interface IHttpHandlers {

	public void intiHandler(HttpServletRequest	req,HttpServletResponse resp,IHttpHandlers handler,String actionId) throws Exception ;
	
	public void handleCommonRequest(HttpServletRequest	req,HttpServletResponse resp,IHttpHandlers handler,String actionId) throws Exception ;
	
	public void handleRequest(HttpServletRequest	req,HttpServletResponse resp,IHttpHandlers handler,String actionId) throws Exception ;
	
	public void handlePageRenderRequest(HttpServletRequest	req,HttpServletResponse resp,IHttpHandlers handler,String actionId) throws Exception ;
	
	public void handlePageSubmitRequest(HttpServletRequest	req,HttpServletResponse resp,IHttpHandlers handler,String actionId,IDataObjects dataObject) throws Exception ;
	
	public void modifyMainPageTemplate(HttpServletRequest	req,HttpServletResponse resp,IHttpHandlers handler,String actionId) throws Exception ;
	
	public void modifyEmbeddedChildPageTemplate(HttpServletRequest	req,HttpServletResponse resp,IHttpHandlers handler,String actionId) throws Exception;

	public void modifyHiddenFieldPageTemplate(HttpServletRequest	req,HttpServletResponse resp,IHttpHandlers handler,String actionId) throws Exception ;
	
	public void createFinalPageTemplate(HttpServletRequest	req,HttpServletResponse resp,IHttpHandlers handler,String actionId) throws Exception ;
	
	public void writeHttpResponse(HttpServletRequest	req,HttpServletResponse resp,String responseString,String actionId) throws Exception ;	

	public void handleError(HttpServletRequest	req,HttpServletResponse resp,IHttpHandlers handler,String actionId, Exception ex) throws Exception ;
	
	public IDataObjects validateRequest(HttpServletRequest	req,HttpServletResponse resp,IHttpHandlers handler,String actionId) throws Exception;
	

	
	public static String PAGE_INDEX_RENDER	=	"";
	public static String PAGE_INDEX_SUBMIT	=	"indexsubmit";
	public static String PAGE_REMOTE_RENDER	=	"remoteconfig_render";
	public static String PAGE_REMOTE_SUBMIT	=	"remoteconfig_submit";
	public static String PAGE_LOCAL_RENDER	=	"localconfig_render";
	public static String PAGE_LOCAL_SUBMIT	=	"localconfig_submit";
	public static String PAGE_HIDDEN_FIELD	=	"template_hidden_field";
	public static String PAGE_LOGIN_SUBMIT	=	"login_submit";
	public static String PAGE_LOGIN_RENDER	=	"login_render";
		
	public static final String	TEMPLATE_LOCATION	=	"web/page/";
	public static final String	TEMPLATE_EXT	=	".html";
	public static final String	HIDDEN_ACTION	=	"action";
	public static final String	HIDDEN_APPMODE	=	"appmode";
	public static final String	HIDDEN_MODE	=	"mode";
	public static final String	HIDDEN_NEXTPAGE	=	"nextpage";
	
	public static final String	PAGE_MAINPAGE	=	"mainpage";

	public static String SUBMIT	=	"submit";
	public static String RENDER	=	"render";
	
	//This is for mainpage.html
	public static String DELIMITER_EMBEDCHILD	=	"%embedchild%";
	public static String DELIMITER_HIDDENFIELD_PAGE	=	"%hiddenfields%";
	public static String DELIMITER_HIDDENFIELD_ACTION	=	"%action%";
	public static String DELIMITER_HIDDENFIELD_APPMODE	=	"%appmode%";
	public static String DELIMITER_HIDDENFIELD_MODE	=	"%mode%";
	public static String DELIMITER_HIDDENFIELD_NEXTPAGE	=	"%nextpage%";
	
	public static String DELIMITER_FIELDDATATYPESLIST	=	"%fielddatatypeslist%";
	public static String DELIMITER_DATATYPENAME				=	"%datatypenames%";
	public static String DELIMITER_DATATYPEID				=	"%datatypeid%";
	public static String DELIMITER_REMOTECONNDATATYPES				=	"%remoteconndatatypes%";
	
	public static String DELIMITER_REMOTEFIELDDATALIST	=	"%remotefielddatalist%";
	
}
