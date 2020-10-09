package com.rest.steps;

import com.rest.controller.TeamController;
import io.restassured.response.ValidatableResponse;

public class TeamSteps {

    public TeamController teamController = new TeamController();

    public ValidatableResponse getAll() {
        return teamController
                .getAll()
                .statusCode(200);
    }

    public ValidatableResponse getAll(int statusCode) {
        return teamController
                .getAll()
                .statusCode(statusCode);
    }

    public ValidatableResponse findById(int id) {
        return teamController
                .findById(id)
                .statusCode(200);
    }

    public ValidatableResponse findById(int statusCode, int id) {
        return teamController
                .findById(id)
                .statusCode(statusCode);
    }
}
