package com.example.demo.exeption;

public class EmployeeNotFoundException extends RuntimeException{
	
	private String message;

	public EmployeeNotFoundException(String message) {
		super();
		this.message = message;
	}
	
}
