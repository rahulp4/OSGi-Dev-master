package com.intellizones.gateway.datastoremanager;

import com.intellizones.gateway.dataobjects.ConnectionConfigDTO;

public class XMLDataStoreManager implements IDataStoreManager {

	@Override
	public boolean persistDataObject(ConnectionConfigDTO connectionConfigDTO, String primaryKey) {
		// TODO Auto-generated method stub
		System.out.println("\n Stored Data Successfully");
		return false;
	}

	@Override
	public ConnectionConfigDTO retrieveDataObject(String primaryKey) {
		// TODO Auto-generated method stub
		return null;
	}

}
