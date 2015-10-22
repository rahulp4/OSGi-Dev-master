package com.intellizones.gateway.dataobjects;

import java.util.HashMap;

public class RemoteConnectionData extends CommonDTO {
	
	private String jsonData	=	null;
	public String getJsonData() {
		return jsonData;
	}
	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}
	public String getConnectionTppe() {
		return connectionTppe;
	}
	public void setConnectionTppe(String connectionTppe) {
		this.connectionTppe = connectionTppe;
	}
	public String getHttpURL() {
		return httpURL;
	}
	public void setHttpURL(String httpURL) {
		this.httpURL = httpURL;
	}
	public String getHttpSecurityKey() {
		return httpSecurityKey;
	}
	public void setHttpSecurityKey(String httpSecurityKey) {
		this.httpSecurityKey = httpSecurityKey;
	}
	public String getHttpSecurityValue() {
		return httpSecurityValue;
	}
	public void setHttpSecurityValue(String httpSecurityValue) {
		this.httpSecurityValue = httpSecurityValue;
	}
	public HashMap<String, String> getHttpKeyValueFields() {
		return httpKeyValueFields;
	}
	public void setHttpKeyValueFields(HashMap<String, String> httpKeyValueFields) {
		this.httpKeyValueFields = httpKeyValueFields;
	}
	public String getMqttBrokerURL() {
		return mqttBrokerURL;
	}
	public void setMqttBrokerURL(String mqttBrokerURL) {
		this.mqttBrokerURL = mqttBrokerURL;
	}
	public String getMqttBrokerPwd() {
		return mqttBrokerPwd;
	}
	public void setMqttBrokerPwd(String mqttBrokerPwd) {
		this.mqttBrokerPwd = mqttBrokerPwd;
	}
	public String getMqttBrokerTopic() {
		return mqttBrokerTopic;
	}
	public void setMqttBrokerTopic(String mqttBrokerTopic) {
		this.mqttBrokerTopic = mqttBrokerTopic;
	}
	public String getMqttBrokerQoS() {
		return mqttBrokerQoS;
	}
	public void setMqttBrokerQoS(String mqttBrokerQoS) {
		this.mqttBrokerQoS = mqttBrokerQoS;
	}
	public String getMqttBrokerPayload() {
		return mqttBrokerPayload;
	}
	public void setMqttBrokerPayload(String mqttBrokerPayload) {
		this.mqttBrokerPayload = mqttBrokerPayload;
	}
	private String connectionTppe	=	null;
	private String httpURL	=	null;
	private String httpSecurityKey	=	null;
	private String httpSecurityValue	=	null;
	private HashMap<String, String> httpKeyValueFields	=	null;
	
	private String mqttBrokerURL	=	null;
	private String mqttBrokerPwd	=	null;
	private String mqttBrokerTopic	=	null;
	private String mqttBrokerQoS	=	null;
	private String mqttBrokerPayload	=	null;
	
	
}
