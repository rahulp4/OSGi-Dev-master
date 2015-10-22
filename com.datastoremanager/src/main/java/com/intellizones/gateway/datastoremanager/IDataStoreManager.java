package com.intellizones.gateway.datastoremanager;

import java.util.HashMap;

import com.intellizones.gateway.dataobjects.ConnectionConfigDTO;
import com.intellizones.gateway.dataobjects.exception.AppException;

public interface IDataStoreManager {

	public boolean persistDataObject(ConnectionConfigDTO connectionConfigDTO,String primaryKey) throws AppException;
	
	public ConnectionConfigDTO retrieveDataObject(String primaryKey);
	
}
