package com.marketplace.facilit.exceptions;

public class EmptyFieldException extends Exception{
	
	public EmptyFieldException(String fieldName) {
		super(fieldName+" is empty.");
	}
}
