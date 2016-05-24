package com.agnieszka.projectexpert.core.service;
/**
 * Wyjatek bazowy dla bledow ktore wystepuje w warstwie serwisow (biznesowej)
 * @author Aga
 *
 */
public class ServiceException extends Exception{

	private static final long serialVersionUID = 1L;

	public ServiceException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public ServiceException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public ServiceException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	
}
