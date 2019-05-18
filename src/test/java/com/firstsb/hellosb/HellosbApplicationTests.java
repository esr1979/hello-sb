package com.firstsb.hellosb;

import com.firstsb.hellosb.persistence.model.Cosa;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;


import javax.print.attribute.standard.Media;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = HellosbApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HellosbApplicationTests {

	private static final Logger log = LoggerFactory.getLogger(HellosbApplicationTests.class);

	private static final String API_ROOT = "http://localhost/api/cosas";

	private Cosa createRandomCosa() {
		final Cosa cosa = new Cosa("","");
		cosa.setNombre(randomAlphabetic(10));
		cosa.setDescripcion(randomAlphabetic(15));
		return cosa;
	}

	private String createBookAsUri(Cosa cosa) {
		Response response = RestAssured.given()
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.body(cosa)
				.post(API_ROOT);
		return API_ROOT + "/" + response.jsonPath().get("id");
	}

	@LocalServerPort
	int port;

	@Before
	public void setUp() {
		RestAssured.port = port;
	}

	@Test
	public void Step1_contextLoads() {

		log.info("CONTEXT TEST LOAD HAS BEEN EXECUTED");

	}

	@Test
	public void Step2_createCosa() {

		log.info("LET'S SEE IF WE CAN CREATE A THING");
		Cosa cosa = new Cosa("", "");
		cosa = createRandomCosa();
		log.info("NOMBRE " + cosa.getNombre());
		log.info("DESCRIPCIÓN " + cosa.getDescripcion());

	}

	@Test
	public void Step3_testEndpoint() {

		log.info("CONNECTING TO ENDPOINT");
		Response response = RestAssured.get("http://localhost");
		Assert.assertEquals(HttpStatus.OK.value(), response.getStatusCode());

	}

	@Test
	public void Step4_createCosaThere(){

		log.info("CREATION OF NEW COSA");
		Cosa cosa = new Cosa("","");
		cosa = createRandomCosa();
		log.info("NOMBRE " + cosa.getNombre());
		log.info("DESCRIPCIÓN " + cosa.getDescripcion());
		log.info("PUTTING THE COSA THERE");
		Response response = RestAssured.given().contentType(MediaType.APPLICATION_JSON_VALUE).body(cosa).post(API_ROOT);
		Assert.assertEquals(HttpStatus.CREATED.value(), response.getStatusCode());

		//This is just to test immediately
		//response = RestAssured.get(API_ROOT);
		//log.info("WE HAVE READ THE FOLLOWING PIECE OF SHIT " + response.getBody().asString());
		//Assert.assertEquals(HttpStatus.OK.value(), response.getStatusCode());


	}

	@Test
	public void Step_5_getAllCosas() {

		Response response = RestAssured.get(API_ROOT);
		log.info("WE HAVE READ THE FOLLOWING PIECE OF SHIT " + response.getBody().asString());
		Assert.assertEquals(HttpStatus.OK.value(), response.getStatusCode());



	}



}
