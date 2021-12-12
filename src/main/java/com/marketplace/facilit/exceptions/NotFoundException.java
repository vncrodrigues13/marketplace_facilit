package com.marketplace.facilit.exceptions;

public abstract class NotFoundException extends Exception{
	
	public NotFoundException(String element) {
		
		super(element + "Not Found Exception");
		super.fillInStackTrace();
	}
}
