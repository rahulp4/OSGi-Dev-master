package com.intellizones.gateway.datastoremanager;

import com.intellizones.gateway.dataobjects.ConnectionConfigDTO;
import com.intellizones.gateway.dataobjects.exception.AppException;
import com.intellizones.gateway.datastoremanager.util.ApplicationDataHolder;

public class XMLDataStoreManager implements IDataStoreManager {

	@Override
	public boolean persistDataObject(ConnectionConfigDTO connectionConfigDTO, String primaryKey) throws AppException {
		// TODO Auto-generated method stub
		try{
			ApplicationDataHolder	app =	ApplicationDataHolder.getApplicationDataHolder();
			
//			conf.setTenantID("mytenat");
//			conf.setConnectionId("conid1");
			app.writeJSONToFileSystem(connectionConfigDTO,null,null);
			app.writeDocToFileSystem(connectionConfigDTO,null,connectionConfigDTO.getXmlDocument());
			app.writeMappedSystem(connectionConfigDTO,null,null);
			System.out.println("\n Stored Data Successfullyyyyy");
		} catch (Exception e){
			AppException	app	=	new AppException();
			app.setErrorId("PERSISTEXCEP");
			app.setErrorMessage("Error Saving File");
			throw app;
		}
		return false;
	}

	@Override
	public ConnectionConfigDTO retrieveDataObject(String primaryKey) {
		// TODO Auto-generated method stub
		return null;
	}

}
