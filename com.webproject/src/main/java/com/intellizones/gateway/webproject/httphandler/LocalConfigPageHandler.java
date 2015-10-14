package com.intellizones.gateway.webproject.httphandler;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.intellizones.gateway.datahandler.json.JsonParsing;
import com.intellizones.gateway.dataobjects.ConnectionConfigDTO;
import com.intellizones.gateway.dataobjects.IDataObjects;
import com.intellizones.gateway.dataobjects.exception.AppValidationException;
import com.intellizones.gateway.datastoremanager.IDataStoreManager;
import com.intellizones.gateway.datastoremanager.XMLDataStoreManager;
import com.intellizones.gateway.jsonhandler.JSONDataFormatHandler;
import com.intellizones.gateway.webproject.exception.AppException;
import com.intellizones.gateway.webproject.util.ApplicationSessionManager;
import com.intellizones.gateway.webproject.util.ApplicationUtil;

public class LocalConfigPageHandler extends AbstractHttpRequestHandler {

	private String CURRENT_PAGE_TEMPLATE	=	"template_localconfig";	
	private String TEMPLATE_FIELDDATATYPES	=	"template_fielddatatypes";
	
	@Override
	public void handlePageRenderRequest(HttpServletRequest req, HttpServletResponse resp, IHttpHandlers handler,
			String actionId) throws Exception {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
//		String embeddedChild	=	ApplicationUtil.getTemplate(CURRENT_PAGE_TEMPLATE);
//		String mainPageTemplate	=	getMainPageTemplate();
//		
//		mainPageTemplate	=	StringUtils.replace(mainPageTemplate, "%embedchild%", embeddedChild);
		
		ApplicationUtil.printDebugMessage(this.getClass().getName(), "In RenderRequest for LocalConfig");
		setAction(IHttpHandlers.PAGE_LOCAL_SUBMIT);
		setAppMode("");
		setMode("");
		setNextPage(IHttpHandlers.PAGE_LOCAL_RENDER);

		populateRemoteFieldDataList(req,resp);
		//mainPageTemplate.replaceAll("%embedchild%", embeddedChild);
//		ApplicationUtil.printDebugMessage(this.toString(), mainPageTemplate);
//		ApplicationUtil.printDebugMessage(this.toString(), embeddedChild);
//		
//		writeHttpResponse(req, resp, mainPageTemplate, actionId);
		
		
	}

	@Override
	public void handlePageSubmitRequest(HttpServletRequest req, HttpServletResponse resp, IHttpHandlers handler,
			String actionId,IDataObjects dataObject) throws Exception {
		// TODO Auto-generated method stub
		
		ConnectionConfigDTO connectionConfigDTO	=	(ConnectionConfigDTO)ApplicationSessionManager.getFromSession(req, ApplicationSessionManager.REMOTECONFIGCONN);
		String jsonString	=	connectionConfigDTO.getJsonString();
		ApplicationUtil.printDebugMessage(this.getClass().getSimpleName(), "JSON : "+jsonString);
		ApplicationSessionManager.putInSession(req, ApplicationSessionManager.REMOTECONFIGCONN, connectionConfigDTO);
		//IDataStoreManager
		IDataStoreManager	xmlDataStore	=	new XMLDataStoreManager();
		xmlDataStore.persistDataObject(connectionConfigDTO, connectionConfigDTO.getPrimaryKey());
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
//		String hiddenFiled	=	ApplicationUtil.getTemplate(PAGE_HIDDEN_FIELD);
//		setHiddenFieldTemplate(hiddenFiled);
		
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
		
		ConnectionConfigDTO connectionConfigDTO	=	(ConnectionConfigDTO)ApplicationSessionManager.getFromSession(req, ApplicationSessionManager.REMOTECONFIGCONN);
		Map<String, String[]> map = req.getParameterMap();
		
		String[] fieldNames = null;
		String[] dataTypes = null;
		
		//ApplicationUtil.printDebugMessage(this.getClass().getCanonicalName(),"Subitted");
		for (Entry<String, String[]> entry : map.entrySet()) {
		    String name = entry.getKey();
		    String [] values	=	entry.getValue();
		    //localFieldNames
		    
		    if(name.equals("locDeviceName")){
		    	connectionConfigDTO.setLocDeviceName(req.getParameter("locDeviceName"));
		    } else if(name.equals("locDeviceId")){
		    	connectionConfigDTO.setLocDeviceId(req.getParameter("locDeviceId"));
		    } else if(name.equals("locConnType")){
		    	connectionConfigDTO.setLocConnType(req.getParameter("locConnType"));
		    } else if(name.equals("locPortName")){
		    	connectionConfigDTO.setLocPortName(req.getParameter("locPortName"));
		    } else if(name.equals("locDataSize")){
		    	connectionConfigDTO.setLocDataSize(req.getParameter("locDataSize"));
		    	ApplicationUtil.printDebugMessage(this.getClass().getSimpleName(), req.getParameter("locDataSize"));
		    } 
		    
		    if(name.equals("localFieldNames")){
		    	fieldNames	=	values;
		    } else if(name.equals("remoteFieldNames")){
		    	dataTypes	=	values;
		    }
		    
		    ApplicationUtil.printDebugMessage(this.getClass().getCanonicalName(),name + ": " + Arrays.toString(values));
		}
		
		setFieldValues(connectionConfigDTO,fieldNames,dataTypes);
		connectionConfigDTO.setPrimaryKey(connectionConfigDTO.getLocDeviceId());
		
		return connectionConfigDTO;
		
	}

	private void populateRemoteFieldDataList(HttpServletRequest req,HttpServletResponse resp) throws Exception {
		String remoteConnDataTypeOption	=	ApplicationUtil.getTemplate(TEMPLATE_FIELDDATATYPES);
		StringBuffer	remoteConnDataTypeSB	=	new StringBuffer();
		try{
		ConnectionConfigDTO connConfigDTO	=	(ConnectionConfigDTO)ApplicationSessionManager.getFromSession(req, ApplicationSessionManager.REMOTECONFIGCONN);
		HashMap	remoteFieldMap	=	connConfigDTO.getFieldIDAndTypeMap();
		Set s	=	remoteFieldMap.keySet();
		if(s!=null){
			Iterator	iterateRemoteData	=	s.iterator();
			while(iterateRemoteData.hasNext()){
				String value	=	(String)iterateRemoteData.next();
				//for (DataTypes r : DataTypes.values()) {
				 String tempValue	=	null;
				 tempValue	=	StringUtils.replace(remoteConnDataTypeOption, IHttpHandlers.DELIMITER_DATATYPENAME, value);
				 tempValue	=	StringUtils.replace(tempValue, IHttpHandlers.DELIMITER_DATATYPEID, value);			    		 
			     System.out.println(tempValue);
			     remoteConnDataTypeSB.append(tempValue);
			 }
	
			 ApplicationUtil.printDebugMessage(this.getClass().getSimpleName()," OPTIONS : "+remoteConnDataTypeSB.toString());
			 String embeddedChild	=	getEmbeddedchildTemplate();
			 embeddedChild	=	StringUtils.replace(embeddedChild, IHttpHandlers.DELIMITER_REMOTEFIELDDATALIST, remoteConnDataTypeSB.toString());
			 setEmbeddedchildTemplate(embeddedChild);;
		} else {
			throw new Exception();
		}
		} catch (Exception e){
			e.printStackTrace();
			throw new AppException("Invlid Session. No Remote Data Found. Please login and start");
		}
	}

	private void setFieldValues(ConnectionConfigDTO connectionConfigDTO,String[] fieldNames,String[] dataTypes){
		if(fieldNames!=null && fieldNames.length>0 && dataTypes!=null && dataTypes.length>0){
			for(int index=0;index<fieldNames.length;index++){
				ApplicationUtil.printDebugMessage(this.getClass().getSimpleName(), fieldNames[index]+": "+dataTypes[index]);
				connectionConfigDTO.addLocAndRemoteFieldMap(fieldNames[index], dataTypes[index]);
			}
			
		}
	}

}
