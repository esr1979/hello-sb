package com.firstsb.hellosb;

import com.firstsb.hellosb.persistence.model.Cosa;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;


@RunWith(SpringRunner.class)
@SpringBootTest
public class HellosbApplicationTests {

	private static final Logger log = LoggerFactory.getLogger(HellosbApplicationTests.class);

	private static final String API_ROOT
			= "http://localhost:8081";


	private Cosa createRandomCosa() {
		final Cosa cosa = new Cosa("","");
		cosa.setNombre(randomAlphabetic(10));
		cosa.setDescripcion(randomAlphabetic(15));
		return cosa;
	}

	private String createCosaAsUri(Cosa cosa) {
		final Response response = RestAssured.given()
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.body(cosa)
				.post(API_ROOT);
		return API_ROOT + "/" + response.jsonPath()
				.get("id");
	}
	
	
	@Test
	public void contextLoads() {

		log.info("CONTEXT TEST LOAD HAS BEEN EXECUTED");

	}

	@Test
	public void createCosa() {

		log.info("LET'S SEE IF WE CAN CREATE A THING");
		Cosa cosa = new Cosa("", "");
		cosa = createRandomCosa();
		log.info("NOMBRE " + cosa.getNombre());
		log.info("DESCRIPCIÓN " + cosa.getDescripcion());

	}

	@Test
	public void testEndpoint() {
		Response response = RestAssured.get(API_ROOT);

	}

}
