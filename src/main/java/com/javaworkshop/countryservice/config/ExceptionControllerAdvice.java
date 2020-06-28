package com.javaworkshop.countryservice.config;

import org.hibernate.exception.JDBCConnectionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.javaworkshop.countryservice.constants.ErrorMessages;
import com.javaworkshop.countryservice.exception.ApiServiceException;


@ControllerAdvice
public class ExceptionControllerAdvice extends ResponseEntityExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionControllerAdvice.class);

	@ExceptionHandler(ApiServiceException.class)
	public final ResponseEntity<String> handleExceptionDefinedInternally(Exception ex,WebRequest request) {
			LOGGER.error("Handling Exception: ", ex);
			return handleApiServiceExceptionAsResponse((ApiServiceException) ex);
		
	}
	
	@ExceptionHandler(JDBCConnectionException.class)
    public ResponseEntity<Object> dbError(Exception ex,
            WebRequest request){
        return new ResponseEntity<Object>(ErrorMessages.INTERNAL_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
    }

	private ResponseEntity<String> handleApiServiceExceptionAsResponse(ApiServiceException ex) {
		return new ResponseEntity<String>(ex.getMessage(), ex.getErrorHttpStatus());
	}

}