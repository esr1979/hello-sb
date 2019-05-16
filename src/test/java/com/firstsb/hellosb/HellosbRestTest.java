package com.firstsb.hellosb;

import com.firstsb.hellosb.persistence.model.Cosa;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;


public class HellosbRestTest {

    private static final Logger log = LoggerFactory.getLogger(HellosbRestTest.class);

    private static final String API_ROOT
            = "http://localhost:8081";

    private Cosa createRandomCosa() {
        final Cosa cosa = new Cosa("","");
        cosa.setNombre(randomAlphabetic(10));
        cosa.setDescripcion(randomAlphabetic(15));
        return cosa;
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
