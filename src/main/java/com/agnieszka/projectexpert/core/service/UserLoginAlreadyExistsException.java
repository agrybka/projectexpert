package com.agnieszka.projectexpert.core.service;

public class UserLoginAlreadyExistsException extends ServiceException{

	
	private static final long serialVersionUID = 1L;

	public UserLoginAlreadyExistsException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserLoginAlreadyExistsException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public UserLoginAlreadyExistsException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public UserLoginAlreadyExistsException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
	

}
