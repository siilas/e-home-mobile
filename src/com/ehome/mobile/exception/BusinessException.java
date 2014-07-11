package com.ehome.mobile.exception;

public class BusinessException extends RuntimeException {
	
	private static final long serialVersionUID = -8756327925687173270L;

	String message = "";
	
	public BusinessException() {
	}
	
	public BusinessException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}