package com.rest.controller;

import com.rest.dto.PlayerDto;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;

import static com.rest.utils.SpecificationFactory.getDefaultRequestSpecication;

public class PlayerController {

    private static String POST_CREATE_NEW_PLAYER = "/v1/player";
    private static String PUT_UPDATE_PLAYER = "/v1/player";
    private static String GET_ALL_ENDPOINT = "/v1/player";
    private static String DELETE_PLAYER = "/v1/player";

    public ValidatableResponse createPlayer(PlayerDto toCreatePlayer) {

        return RestAssured
                .given().spec(getDefaultRequestSpecication())
                .body(toCreatePlayer)
                .when()
                .post(POST_CREATE_NEW_PLAYER)
                .then()
                .log().all();
    }

    public ValidatableResponse updatePlayer(PlayerDto toUpdatePlayer, int id) {
        return RestAssured
                .given().spec(getDefaultRequestSpecication())
                .pathParam("id", id)
                .body(toUpdatePlayer)
                .when()
                .put(PUT_UPDATE_PLAYER + "/{id}")
                .then()
                .log().all();
    }

    public ValidatableResponse deleteUser(int id) {
        return RestAssured
                .given().spec(getDefaultRequestSpecication())
                .pathParam("id", id)
                .when()
                .delete(DELETE_PLAYER + "/{id}")
                .then()
                .log().all();
    }


    public ValidatableResponse getOne(int id) {
        return RestAssured
                .given().spec(getDefaultRequestSpecication())
                .pathParam("id", id)
                .when()
                .get(GET_ALL_ENDPOINT + "/{id}")
                .then()
                .log().all();
    }

    public ValidatableResponse getAll() {
        return RestAssured
                .given().spec(getDefaultRequestSpecication())
                .when()
                .get(GET_ALL_ENDPOINT)
                .then()
                .log().all();
    }


}
