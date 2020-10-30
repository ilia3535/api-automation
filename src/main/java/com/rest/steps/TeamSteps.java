package com.rest.steps;

import com.rest.controller.TeamController;
import com.rest.dto.TeamDto;
import io.restassured.response.ValidatableResponse;

import java.util.Arrays;
import java.util.List;

public class TeamSteps {

    public TeamController teamController = new TeamController();

    public List<TeamDto> getAll() {
        return Arrays.asList(teamController
                .getAll()
                .statusCode(200)
                .extract().body().as(TeamDto[].class));
    }

    public ValidatableResponse getAll(int statusCode) {
        return teamController
                .getAll()
                .statusCode(statusCode);
    }

    public TeamDto findById(int id) {
        return teamController
                .findById(id)
                .statusCode(200)
                .extract().body().as(TeamDto.class);
    }

    public ValidatableResponse findById(int statusCode, int id) {
        return teamController
                .findById(id)
                .statusCode(statusCode);
    }

    public TeamDto postBody(TeamDto teamDto) {
        return teamController
                .createTeam(teamDto)
                .statusCode(201)
                .extract().body().as(TeamDto.class);
    }

    public ValidatableResponse postBody(int statusCode, TeamDto teamDto) {
        return teamController
                .createTeam(teamDto)
                .statusCode(statusCode);
    }

    public TeamDto putBody(TeamDto teamDto) {
        return teamController
                .updateTeam(teamDto)
                .statusCode(202)
                .extract().body().as(TeamDto.class);
    }
}
