package com.javaworkshop.countryservice.exception;

import org.springframework.http.HttpStatus;

import com.javaworkshop.countryservice.constants.ErrorMessages;

public class ResourceNotFoundException extends ApiServiceException {

	private static final long serialVersionUID = -5706973495569643080L;
	
	public ResourceNotFoundException() {
		super(ErrorMessages.INVALID_COUNTRY_CODE);
	}

	@Override
	public HttpStatus getErrorHttpStatus() {
		return HttpStatus.INTERNAL_SERVER_ERROR;
	}

}
