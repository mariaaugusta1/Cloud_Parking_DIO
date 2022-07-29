package com.desafiodio.parking.controller;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ParkingControllerTest {

    @LocalServerPort
    private int randomPort;

    @BeforeEach
    public void setUpTeste(){
        RestAssured.port = randomPort;
    }

//    @Test
//    void whenFindAllthenCheckResult() {
//        RestAssured.given()
//                .when()
//                .get()
//                .then()
//                .statusCode(HttpStatus.OK.value())
//                .body("License[0]", Matchers.equalTo("DMS-1111"));
//    }

    @Test
    void whenCreatethenCkeckIsCreated() {

        var createDto = new ParkingCreateDTO();
        createDto.setColor("Amarelo");
        createDto.setLicense("WRT-5555");
        createDto.setModel("Brasilia");
        createDto.setState("SP");


        RestAssured.given()
                .when()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(createDto)
                .post("/parking")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("color", Matchers.equalTo("Amarelo"))
                .body("license", Matchers.equalTo("WRT-5555"));
    }
}