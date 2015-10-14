package com.intellizones.gateway.webproject.exception;

public class AppException extends Exception{

	private String errorId				=	null;
	private String errorMessage			=	null;
	public String getErrorId() {
		return errorId;
	}

	public void setErrorId(String errorId) {
		this.errorId = errorId;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorPageTemplate() {
		return errorPageTemplate;
	}

	public void setErrorPageTemplate(String errorPageTemplate) {
		this.errorPageTemplate = errorPageTemplate;
	}

	private String errorPageTemplate	=	null;
	
	public AppException(String message){
		errorMessage	=	message;
	}

	public AppException(){
		
	}

}
