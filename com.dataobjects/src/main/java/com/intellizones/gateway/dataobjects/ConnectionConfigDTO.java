package com.intellizones.gateway.dataobjects;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.w3c.dom.Document;

public class ConnectionConfigDTO extends CommonDTO {

	public ConnectionConfigDTO(){
		fieldIDAndTypeMap	=	new HashMap<String, String>();
		locAndRemoteFieldMap	=	new HashMap<String, String>();
	}
	
	
	
	public HashMap<String, String> getRemoteDataFieldsKeyMap() {
		return remoteDataFieldsKeyMap;
	}
	public void setRemoteDataFieldsKeyMap(HashMap<String, String> remoteDataFieldsKeyMap) {
		this.remoteDataFieldsKeyMap = remoteDataFieldsKeyMap;
	}
	public String getXmlString() {
		return xmlString;
	}
	public void setXmlString(String xmlString) {
		this.xmlString = xmlString;
	}
	public String getJsonString() {
		return jsonString;
	}
	public void setJsonString(String jsonString) {
		this.jsonString = jsonString;
	}
	public void setFieldIDAndTypeMap(HashMap<String, String> fieldIDAndTypeMap) {
		this.fieldIDAndTypeMap = fieldIDAndTypeMap;
	}
	public String getConnectionId() {
		return connectionId;
	}
	public void setConnectionId(String connectionId) {
		this.connectionId = connectionId;
	}
	public String getConnectionName() {
		return connectionName;
	}
	public void setConnectionName(String connectionName) {
		this.connectionName = connectionName;
	}
	public String getRemoteConnType() {
		return remoteConnType;
	}
	public void setRemoteConnType(String remoteConnType) {
		this.remoteConnType = remoteConnType;
	}
	public String getRemoteRESTConnURL() {
		return remoteRESTConnURL;
	}
	public void setRestRemoteConnURL(String remoteRESTConnURL) {
		this.remoteRESTConnURL = remoteRESTConnURL;
	}
	public String getSecurityKey() {
		return securityKey;
	}
	public void setSecurityKey(String securityKey) {
		this.securityKey = securityKey;
	}
	public HashMap<String, String> getFieldIDAndTypeMap() {
		return fieldIDAndTypeMap;
	}
	
	
	public void addFieldAndDataType(String key,String value){
		this.fieldIDAndTypeMap.put(key, value);
	}
	
	public String getDataType(String fieldName){
		return this.fieldIDAndTypeMap.get(fieldName);
	}

	
	
	public String getLocPortName() {
		return locPortName;
	}
	public void setLocPortName(String locPortName) {
		this.locPortName = locPortName;
	}
	public void setRemoteRESTConnURL(String remoteRESTConnURL) {
		this.remoteRESTConnURL = remoteRESTConnURL;
	}
	public String getLocDeviceName() {
		return locDeviceName;
	}
	public void setLocDeviceName(String locDeviceName) {
		this.locDeviceName = locDeviceName;
	}
	public String getLocDeviceId() {
		return locDeviceId;
	}
	public void setLocDeviceId(String locDeviceId) {
		this.locDeviceId = locDeviceId;
	}
	public String getLocConnType() {
		return locConnType;
	}
	public void setLocConnType(String locConnType) {
		this.locConnType = locConnType;
	}
	public String getLocDataSize() {
		return locDataSize;
	}
	public void setLocDataSize(String locDataSize) {
		this.locDataSize = locDataSize;
	}
	public HashMap<String, String> getLocAndRemoteFieldMap() {
		return locAndRemoteFieldMap;
	}
	public void setLocAndRemoteFieldMap(HashMap<String, String> locAndRemoteFieldMap) {
		this.locAndRemoteFieldMap = locAndRemoteFieldMap;
	}

	
	public void addLocAndRemoteFieldMap(String key,String value){
		this.locAndRemoteFieldMap.put(key, value);
	}
	
	public String getLocAndRemoteFieldMap(String fieldName){
		return this.locAndRemoteFieldMap.get(fieldName);
	}
	

	public String getLocalFieldIndexFrom() {
		return localFieldIndexFrom;
	}
	public void setLocalFieldIndexFrom(String localFieldIndexFrom) {
		this.localFieldIndexFrom = localFieldIndexFrom;
	}
	public String getLocalFieldIndexTo() {
		return localFieldIndexTo;
	}
	public void setLocalFieldIndexTo(String localFieldIndexTo) {
		this.localFieldIndexTo = localFieldIndexTo;
	}



	public String getLocalSensorDataFormat() {
		return localSensorDataFormat;
	}
	public void setLocalSensorDataFormat(String localSensorDataFormat) {
		this.localSensorDataFormat = localSensorDataFormat;
	}
	
	private  String connectionId	=	null;
	private  String jsonString		=	null;
	private  String xmlString		=	null;
	private HashMap<String, String> remoteDataFieldsKeyMap	=	null;
	private  String connectionName						=	null;
	private  String remoteConnType						=	null;
	private  String remoteRESTConnURL					=	null;
	private  String securityKey							=	null;
	private HashMap<String, String>	fieldIDAndTypeMap	=	null;
	private  String locDeviceName						=	null;
	private  String locDeviceId						=	null;
	private  String locConnType						=	null;
	private  String locPortName						=	null;
	private  String locDataSize						=	null;
	private HashMap<String, String>	locAndRemoteFieldMap	=	null;
	private String localFieldIndexFrom	=	null;
	private String localFieldIndexTo		=	null;
	private String localSensorDataFormat	=	null;
	private Document xmlDocument	=	null;
	
	
	public Document getXmlDocument() {
		return xmlDocument;
	}



	public void setXmlDocument(Document xmlDocument) {
		this.xmlDocument = xmlDocument;
	}



	public String toString() {
		String toString	=	"<connectionId>"+connectionId+"</connectionId>"+
		"<jsonString>"+jsonString+"</jsonString>"+
		"<xmlString>"+xmlString+"</xmlString>"+
		"<connectionName>"+connectionName+"</connectionName>"+
		"<remoteConnType>"+remoteConnType+"</remoteConnType>"+
		"<remoteRESTConnURL>"+remoteRESTConnURL+"</remoteRESTConnURL>"+
		"<securityKey>"+securityKey+"</securityKey>"+		
		"<locDeviceName>"+locDeviceName+"</locDeviceName>"+		
		"<locDeviceId>"+locDeviceId+"</locDeviceId>"+		
		"<locConnType>"+locConnType+"</locConnType>"+		
		"<locPortName>"+locPortName+"</locPortName>"+		
		"<locDataSize>"+locDataSize+"</locDataSize>"+		
		"<localFieldIndexFrom>"+localFieldIndexFrom+"</localFieldIndexFrom>"+		
		"<localFieldIndexTo>"+localFieldIndexTo+"</localFieldIndexTo>"+		
		"<localSensorDataFormat>"+localSensorDataFormat+"</localSensorDataFormat>";
		
		if(locAndRemoteFieldMap!=null ){
			Set keySet	=	locAndRemoteFieldMap.keySet();
			Iterator<String> it	= keySet.iterator();
			while(it.hasNext()){
				String key	=	it.next();
				String	value	=	locAndRemoteFieldMap.get(key);
				toString=toString+"<"+key+">"+value+"</"+key+">";
			}
			
			
		}
		
		if(fieldIDAndTypeMap!=null ){
			Set keySet	=	fieldIDAndTypeMap.keySet();
			Iterator<String> it	= keySet.iterator();
			while(it.hasNext()){
				String key	=	it.next();
				String	value	=	fieldIDAndTypeMap.get(key);
				toString=toString+"<"+key+">"+value+"</"+key+">";
			}
			
		}
		
		return toString;
	}
}
