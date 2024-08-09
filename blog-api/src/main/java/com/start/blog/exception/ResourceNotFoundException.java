package com.start.blog.exception;

public class ResourceNotFoundException extends RuntimeException{
	String resourceNAme;
	String fieldNAme;
	long fieldValue;
	public ResourceNotFoundException(String resourceNAme, String fieldNAme, long fieldValue) {
		super(String.format("%s is not found with %s : %s",resourceNAme, fieldNAme, fieldValue));
		this.resourceNAme = resourceNAme;
		this.fieldNAme = fieldNAme;
		this.fieldValue = fieldValue;
	}
	

}
