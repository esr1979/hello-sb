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

import java.util.List;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;


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

	private String createCosaAsUri(Cosa cosa) {
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
	public void Step01_contextLoads() {

		log.info("CONTEXT TEST LOAD HAS BEEN EXECUTED");

	}

	@Test
	public void Step02_createCosa() {

		log.info("LET'S SEE IF WE CAN CREATE A THING");
		Cosa cosa = new Cosa("", "");
		cosa = createRandomCosa();
		log.info("NOMBRE " + cosa.getNombre());
		log.info("DESCRIPCIÓN " + cosa.getDescripcion());

	}

	@Test
	public void Step03_testEndpoint() {

		log.info("CONNECTING TO ENDPOINT");
		Response response = RestAssured.get("http://localhost");
		Assert.assertEquals(HttpStatus.OK.value(), response.getStatusCode());

	}

	@Test
	public void Step04_createCosasThere(){

		log.info("CREATION OF NEW COSA");
		Cosa cosa = new Cosa("","");
		cosa = createRandomCosa();
		log.info("NAME --> " + cosa.getNombre());
		log.info("DESCRIPTION --> " + cosa.getDescripcion());
		log.info("PUTTING THE COSA THERE 1");
		Response response = RestAssured.given()
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.body(cosa)
				.post(API_ROOT);
		Assert.assertEquals(HttpStatus.CREATED.value(), response.getStatusCode());

		cosa = createRandomCosa();
		log.info("NAME --> " + cosa.getNombre());
		log.info("DESCRIPTION --> " + cosa.getDescripcion());
		log.info("PUTTING THE COSA THERE 2");
		response = RestAssured.given()
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.body(cosa)
				.post(API_ROOT);
		Assert.assertEquals(HttpStatus.CREATED.value(), response.getStatusCode());

		cosa = createRandomCosa();
		log.info(" --> " + cosa.getNombre());
		log.info("DESCRIPTION --> " + cosa.getDescripcion());
		log.info("PUTTING THE COSA THERE 3");
		response = RestAssured.given()
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.body(cosa)
				.post(API_ROOT);
		Assert.assertEquals(HttpStatus.CREATED.value(), response.getStatusCode());

		//This is just to test immediately
		//response = RestAssured.get(API_ROOT);
		//log.info("WE HAVE READ THE FOLLOWING PIECE OF SHIT " + response.getBody().asString());
		//Assert.assertEquals(HttpStatus.OK.value(), response.getStatusCode());


	}

	@Test
	public void Step_05_getAllCosas() {

		Response response = RestAssured.get(API_ROOT);
		String location = API_ROOT + "/" + response.jsonPath().get("id");
		log.info("WE HAVE READ THE FOLLOWING PIECE OF SHIT --> " + response.getBody().asString());
		log.info("THE COSAS WE HAVE READ ARE LOCATED IN --> " + location);
		Assert.assertEquals(HttpStatus.OK.value(), response.getStatusCode());

	}

	@Test
	public void Step_06_createCosaThereAsUri() {

		Cosa cosa = createRandomCosa();
		String location = createCosaAsUri(cosa);
		Response response = RestAssured.get(location);

		log.info("THE COSA WE HAVE CREATED IS LOCATED IN --> " + location);
		log.info("THE COSA NAME IS --> " + response.jsonPath().get("nombre"));
		log.info("WHOLE BODY --> " + response.getBody().asString());

		Assert.assertEquals(HttpStatus.OK.value(), response.getStatusCode());
		Assert.assertEquals(cosa.getNombre(), response.jsonPath().get("nombre"));

	}

	@Test
	public void Step_07_getCosaByName() {

		Cosa cosa = createRandomCosa();
		createCosaAsUri(cosa);

		log.info("NAME --> " + cosa.getNombre());
		log.info("DESCRIPTION --> " + cosa.getDescripcion());
		log.info("COSA CREATED");

		Response response = RestAssured.get(
				API_ROOT + "/nombre/" + cosa.getNombre());

		Cosa cosarecuperada[] = response.getBody().as(Cosa[].class);

		log.info("WE HAVE RECOVERED THE FOLLOWING ...");
		log.info("WHOLE BODY --> " + response.getBody().asString());

		log.info(Long.toString(cosarecuperada[0].getId()));
		log.info(cosarecuperada[0].getNombre());
		log.info(cosarecuperada[0].getDescripcion());

		Assert.assertEquals(HttpStatus.OK.value(), response.getStatusCode());
		Assert.assertTrue(response.as(List.class)
				.size() > 0);
	}

	@Test
	public void Step_08_getNotExistentCosa(){

		String cosaToSearch = API_ROOT + "/" + randomNumeric(4);
		Response response = RestAssured.get(cosaToSearch);
		log.info("LOOKING FOR THIS COSA --> " + cosaToSearch);
		Assert.assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCode());

	}

	@Test
	public void Step_09_createInvalidCosa() {

		Cosa cosa = createRandomCosa();
		cosa.setDescripcion(null);
		Response response = RestAssured.given()
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.body(cosa)
				.post(API_ROOT);
		log.info("WHOLE BODY --> " + response.getBody().asString());
		Assert.assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCode());


	}

	@Test
	public void Step_10_createUpdateCosa() {
		Cosa cosa = createRandomCosa();
		String location = createCosaAsUri(cosa);
		log.info("LOCATION IS -->" + location);
		cosa.setId(Long.parseLong(location.split("api/cosas/")[1]));
		cosa.setDescripcion("Es una cosa de goma");
		cosa.setNombre("Goma");

		Response response = RestAssured.given()
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.body(cosa)
				.put(location);

		Assert.assertEquals(HttpStatus.OK.value(), response.getStatusCode());

		response = RestAssured.get(location);

		log.info("THE ACTUALIZED THING IS --> " + response.getBody().asString());

		Assert.assertEquals(HttpStatus.OK.value(), response.getStatusCode());
		Assert.assertEquals("Es una cosa de goma", response.jsonPath().get("descripcion"));
		Assert.assertEquals("Goma", response.jsonPath().get("nombre"));

	}

	@Test
	public void Step_11_createDeleteCosa() {
		Cosa cosa = createRandomCosa();
		String location = createCosaAsUri(cosa);

		Response response = RestAssured.get(API_ROOT);
		String locationBefore = API_ROOT + "/" + response.jsonPath().get("id");
		log.info("WE HAVE READ THE FOLLOWING PIECE OF SHIT --> " + response.getBody().asString());
		log.info("THE COSAS WE HAVE READ ARE LOCATED IN --> " + locationBefore);
		Assert.assertEquals(HttpStatus.OK.value(), response.getStatusCode());

		response = RestAssured.delete(location);

		Assert.assertEquals(HttpStatus.OK.value(), response.getStatusCode());

		response = RestAssured.get(location);

		log.info("WE HAVE CREATED AND DELETED --> " + location);

		Assert.assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCode());

		response = RestAssured.get(API_ROOT);
		String locationAfter = API_ROOT + "/" + response.jsonPath().get("id");
		log.info("WE HAVE READ THE FOLLOWING PIECE OF SHIT --> " + response.getBody().asString());
		log.info("THE COSAS WE HAVE READ ARE LOCATED IN --> " + locationAfter);
		Assert.assertEquals(HttpStatus.OK.value(), response.getStatusCode());

	}
	
	

}
