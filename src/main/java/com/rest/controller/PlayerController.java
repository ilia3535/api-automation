package com.rest.controller;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.json.simple.JSONObject;

public class PlayerController {

    private static String POST_CREATE_NEW_PLAYER = "http://localhost:8088/v1/player";

    public ValidatableResponse createPlayer(String fullName, String position, String teamName) {

        JSONObject requestParams = new JSONObject();
        requestParams.put("fullName", fullName);
        requestParams.put("position", position);
        requestParams.put("teamName", teamName);

        return RestAssured
                .given()
                .header("Content-Type", "application/json")
                .body(requestParams.toJSONString())
                .when()
                .post(POST_CREATE_NEW_PLAYER)
                .then();
    }
}
