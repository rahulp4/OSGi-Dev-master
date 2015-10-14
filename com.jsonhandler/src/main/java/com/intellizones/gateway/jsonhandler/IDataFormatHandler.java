package com.intellizones.gateway.jsonhandler;

import com.intellizones.gateway.dataobjects.ConnectionConfigDTO;

public interface IDataFormatHandler {

	public boolean isValidFormat(ConnectionConfigDTO jsonString);
	
}
