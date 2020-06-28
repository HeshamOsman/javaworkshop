package com.javaworkshop.countryservice.exception;

import org.springframework.http.HttpStatus;

public abstract class ApiServiceException extends RuntimeException{

	private static final long serialVersionUID = 5757097431324762592L;

	protected ApiServiceException(String message) {
        super(message);
    }

    public abstract HttpStatus getErrorHttpStatus();
}
