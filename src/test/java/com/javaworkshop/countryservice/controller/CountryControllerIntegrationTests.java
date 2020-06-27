package com.javaworkshop.countryservice.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static io.restassured.RestAssured.get;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import com.javaworkshop.countryservice.constants.ErrorMessages;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CountryControllerIntegrationTests {
	
	@LocalServerPort
	private int port;
	
	@Test
	public void testGetCountryInfoWithValidCode() {
		get("http://localhost:"+port+"/USA")
				.then()
				.assertThat()
				.statusCode(HttpStatus.OK.value());
	}
	
	@Test
	public void testGetCountryInfoWithInvalidCode() {

		String body = get("http://localhost:"+port+"/KKKK")
				.then()
				.assertThat()
				.statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.extract().asString();
		assertTrue(ErrorMessages.INVALID_COUNTRY_CODE.equals(body));

	}
}
