package com.javaworkshop.countryservice.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;
import org.hibernate.exception.JDBCConnectionException;

import com.javaworkshop.countryservice.constants.ErrorMessages;
import com.javaworkshop.countryservice.exception.ResourceNotFoundException;
import com.javaworkshop.countryservice.service.CountryService;

@ExtendWith(MockitoExtension.class)
public class CountryControllerUnitTest {
	
	   @InjectMocks
	   CountryController countryController;
	     
	    @Mock
	    CountryService countryService;
	     
	    @Test
	    public void testGetCountryInfoWhenDatabaseIsDown(){
	        MockHttpServletRequest request = new MockHttpServletRequest();
	        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
	         
	        when(countryService.findCountryByCode(any(String.class))).thenThrow(JDBCConnectionException.class);
	         
	        ResponseEntity<?> responseEntity = countryController.getCountryInfoByCode("123");
	         
	        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
	        assertThat((String)responseEntity.getBody()).isEqualTo(ErrorMessages.INTERNAL_ERROR);
	    }
	    
	    @Test
	    public void testGetCountryInfoWithInvalidCode(){
	        MockHttpServletRequest request = new MockHttpServletRequest();
	        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
	         
	        when(countryService.findCountryByCode(any(String.class))).thenThrow(ResourceNotFoundException.class);
	         
	        ResponseEntity<?> responseEntity = countryController.getCountryInfoByCode("123");
	         
	        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
	        assertThat((String)responseEntity.getBody()).isEqualTo(ErrorMessages.INVALID_COUNTRY_CODE);
	    }

}
