package com.rest.steps;

import com.rest.controller.PlayerController;
import io.restassured.response.ValidatableResponse;

public class PlayerSteps {

    public PlayerController playerController = new PlayerController();

    public ValidatableResponse postBody(String fullName, String position, String teamName) {
        return playerController
                .createPlayer(fullName, position, teamName)
                .statusCode(201);
    }

    public ValidatableResponse postBody(int statusCode, String fullName, String position, String teamName) {
        return playerController
                .createPlayer(fullName, position, teamName)
                .statusCode(statusCode);
    }
}
