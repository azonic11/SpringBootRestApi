package com.crud.models.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class CustomerNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 545008959446428794L;
		
	private String id;
	
	public CustomerNotFoundException(String id) {
	super(String.format(" not found : '%s'",id));
	this.id=id;
	}
	
	public String getId() {
	return this.id;
	}
	
}