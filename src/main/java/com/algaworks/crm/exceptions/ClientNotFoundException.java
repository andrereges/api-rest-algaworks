package com.algaworks.crm.exceptions;

public class ClientNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public ClientNotFoundException(String message) {
        super(message);
    }	
}
