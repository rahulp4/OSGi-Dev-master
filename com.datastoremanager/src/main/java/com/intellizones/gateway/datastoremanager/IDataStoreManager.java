package com.intellizones.gateway.datastoremanager;

import java.util.HashMap;

import com.intellizones.gateway.dataobjects.ConnectionConfigDTO;

public interface IDataStoreManager {

	public boolean persistDataObject(ConnectionConfigDTO connectionConfigDTO,String primaryKey);
	
	public ConnectionConfigDTO retrieveDataObject(String primaryKey);
	
}
