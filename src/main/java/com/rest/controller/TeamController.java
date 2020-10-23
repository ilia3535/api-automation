package com.rest.controller;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;

public class TeamController {

    private static String GET_ALL_ENDPOINT = "http://localhost:8088/v1/team";

    public ValidatableResponse getAll() {
        return RestAssured
                .given()
                .when()
                .get(GET_ALL_ENDPOINT)
                .then()
                .log().all();
    }

    public ValidatableResponse findById(int id) {
        return RestAssured
                .given()
                .pathParam("id", id)
                .when()
                .get(GET_ALL_ENDPOINT + "/{id}")
                .then()
                .log().all();
    }
}
