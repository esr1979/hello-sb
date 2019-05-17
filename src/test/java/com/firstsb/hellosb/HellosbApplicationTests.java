package com.firstsb.hellosb;

import com.firstsb.hellosb.persistence.model.Cosa;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;

import io.restassured.specification.RequestSpecification;
import javafx.application.Application;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = HellosbApplication.class)
public class HellosbApplicationTests {

	private static final Logger log = LoggerFactory.getLogger(HellosbApplicationTests.class);

	private Cosa createRandomCosa() {
		final Cosa cosa = new Cosa("","");
		cosa.setNombre(randomAlphabetic(10));
		cosa.setDescripcion(randomAlphabetic(15));
		return cosa;
	}

	@LocalServerPort
	int port;

	@Before
	public void setUp() {
		RestAssured.port = port;
	}

	@Test
	@Order(1)
	public void contextLoads() {

		log.info("CONTEXT TEST LOAD HAS BEEN EXECUTED");

	}

	@Test
	@Order(2)
	public void createCosa() {

		log.info("LET'S SEE IF WE CAN CREATE A THING");
		Cosa cosa = new Cosa("", "");
		cosa = createRandomCosa();
		log.info("NOMBRE " + cosa.getNombre());
		log.info("DESCRIPCIÓN " + cosa.getDescripcion());

	}

	@Test
	@Order(3)
	public void testEndpoint() {

		log.info("CONNECTING TO ENDPOINT");
		Response response = RestAssured.get("http://localhost");
		Assert.assertEquals(HttpStatus.OK.value(), response.getStatusCode());

	}

}
