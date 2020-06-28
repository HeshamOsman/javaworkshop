package com.javaworkshop.countryservice.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hibernate.exception.JDBCConnectionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.javaworkshop.countryservice.config.ExceptionControllerAdvice;
import com.javaworkshop.countryservice.constants.ErrorMessages;
import com.javaworkshop.countryservice.exception.ResourceNotFoundException;
import com.javaworkshop.countryservice.service.CountryService;

@ExtendWith(MockitoExtension.class)
public class CountryControllerUnitTest {
	
	private MockMvc mockMvc;
	
	@InjectMocks
	private  CountryController countryController;

	@Mock
	private CountryService countryService;

    @BeforeEach
    public  void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(countryController)
                .setControllerAdvice(new ExceptionControllerAdvice())
                .build();
    }

    @Test
    public void testGetCountryInfoWhenDatabaseIsDown() throws Exception {

    	when(countryService.findCountryByCode(any(String.class))).thenThrow(JDBCConnectionException.class);

    	MvcResult result = mockMvc.perform(get("/USA"))
                .andExpect(status().isInternalServerError())
                .andReturn();
    	
    	assertTrue(ErrorMessages.INTERNAL_ERROR.equals(result.getResponse().getContentAsString()));
    }
    
    @Test
    public void testGetCountryInfoWithInvalidCode() throws Exception{
         
        when(countryService.findCountryByCode(any(String.class))).thenThrow(new ResourceNotFoundException());
         
        MvcResult result = mockMvc.perform(get("/KKKK"))
                .andExpect(status().isInternalServerError())
                .andReturn();
    	assertTrue(ErrorMessages.INVALID_COUNTRY_CODE.equals(result.getResponse().getContentAsString()));
    }

}
