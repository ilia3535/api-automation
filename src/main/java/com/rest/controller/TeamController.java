package com.rest.controller;

import com.rest.dto.TeamDto;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;

import static com.rest.utils.SpecificationFactory.getDefaultRequestSpecication;

public class TeamController {

    private static String GET_ALL_ENDPOINT = "/v1/team";
    private static String POST_CREATE_NEW_TEAM = "/v1/team";
    private static String PUT_UPDATE_NEW_TEAM = "/v1/team/%s/assign/%s";

    public ValidatableResponse getAll() {
        return RestAssured
                .given().spec(getDefaultRequestSpecication())
                .when()
                .get(GET_ALL_ENDPOINT)
                .then()
                .log().all();
    }

    public ValidatableResponse findById(int id) {
        return RestAssured
                .given().spec(getDefaultRequestSpecication())
                .pathParam("id", id)
                .when()
                .get(GET_ALL_ENDPOINT + "/{id}")
                .then()
                .log().all();
    }

    public ValidatableResponse createTeam(TeamDto toCreateTeam) {
        return RestAssured
                .given().spec(getDefaultRequestSpecication())
                .body(toCreateTeam)
                .when()
                .post(POST_CREATE_NEW_TEAM)
                .then()
                .log().all();
    }

    public ValidatableResponse assignPlayer(int teamId, int playerId) {
        return RestAssured
                .given().spec(getDefaultRequestSpecication())
                .when()
                .put(String.format(PUT_UPDATE_NEW_TEAM, teamId, playerId))
                .then()
                .log().all();
    }
}
