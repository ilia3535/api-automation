package com.rest.steps;

import com.rest.controller.PlayerController;
import com.rest.dto.PlayerDto;
import io.restassured.response.ValidatableResponse;

import java.util.Arrays;
import java.util.List;

public class PlayerSteps {

    public PlayerController playerController = new PlayerController();


    public PlayerDto postBody(PlayerDto playerDto) {
        return playerController
                .createPlayer(playerDto)
                .statusCode(201)
                .extract().body().as(PlayerDto.class);
    }

    public <T> T postBody(int statusCode, PlayerDto playerDto, Class<T> clazz) {
        return playerController
                .createPlayer(playerDto)
                .statusCode(statusCode)
                .extract().body().as(clazz);
    }

    public PlayerDto putBody(PlayerDto playerDto) {
        return playerController
                .updatePlayer(playerDto)
                .statusCode(200)
                .extract().body().as(PlayerDto.class);
    }

    public ValidatableResponse putBody(int statusCode, PlayerDto playerDto) {
        return playerController
                .updatePlayer(playerDto)
                .statusCode(statusCode);
    }

    public void deletePlayer(int id) {
        playerController
                .deleteUser(id)
                .statusCode(204);
    }

    public ValidatableResponse deletePlayer(int statusCode, int id) {
        return playerController
                .deleteUser(id)
                .statusCode(statusCode);
    }

    public PlayerDto getPlayerById(int id) {
        return playerController
                .getOne(id)
                .statusCode(200)
                .extract().body().as(PlayerDto.class);
    }

    public ValidatableResponse getPlayerById(int statusCode, int id) {
        return playerController
                .getOne(id)
                .statusCode(statusCode);
    }

    public List<PlayerDto> getAll() {
        return Arrays.asList(playerController
                .getAll()
                .statusCode(200)
                .extract().body().as(PlayerDto[].class));
    }

    public ValidatableResponse getAll(int statusCode) {
        return playerController
                .getAll()
                .statusCode(statusCode);
    }
}
