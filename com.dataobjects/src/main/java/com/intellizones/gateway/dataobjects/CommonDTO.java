package com.intellizones.gateway.dataobjects;

public abstract class CommonDTO implements IDataObjects {

	private String primaryKey	=	null;
	public String getPrimaryKey() {
		return primaryKey;
	}
	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}
	public String getTenantID() {
		return tenantID;
	}
	public void setTenantID(String tenantID) {
		this.tenantID = tenantID;
	}
	public String getTenantName() {
		return tenantName;
	}
	public void setTenantName(String tenantName) {
		this.tenantName = tenantName;
	}
	private String tenantID	=	null;
	private String tenantName	=	null;

}
