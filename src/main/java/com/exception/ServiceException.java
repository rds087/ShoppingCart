package com.exception;

public class ServiceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;

	public ServiceException(String message) {
		super();
		this.message = message;
	}

	public ServiceException() {
		super();
	}

	public ServiceException(Exception exception) {
		super();
		this.message = exception.getMessage();
	}

	@Override
	public String toString() {
		return "ServiceException [message=" + message + "]";
	}

}
