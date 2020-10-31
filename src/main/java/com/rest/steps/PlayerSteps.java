package com.rest.steps;

import com.rest.controller.PlayerController;
import com.rest.dto.PlayerDto;
import io.restassured.response.ValidatableResponse;
import lombok.extern.java.Log;

import java.util.Arrays;
import java.util.List;


@Log
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

    public ValidatableResponse putBody(PlayerDto playerDto) {
        return playerController
                .updatePlayer(playerDto)
                .statusCode(202);
    }

    public <T> T putBody(int statusCode, PlayerDto playerDto, Class<T> clazz) {
        return playerController
                .updatePlayer(playerDto)
                .statusCode(statusCode)
                .extract().body().as(clazz);
    }

    public void deletePlayer(int id) {
        playerController
                .deleteUser(id)
                .statusCode(204);
    }

    public <T> T deletePlayer(int statusCode, int id, Class<T> clazz) {
        return playerController
                .deleteUser(id)
                .statusCode(statusCode)
                .extract().body().as(clazz);
    }

    public PlayerDto getPlayerById(int id) {
        return playerController
                .getOne(id)
                .statusCode(200)
                .extract().body().as(PlayerDto.class);
    }

    public <T> T getPlayerById(int statusCode, int id, Class<T> clazz) {
        log.info("Info test");
        log.warning("Warning test!!!");
        return playerController
                .getOne(id)
                .statusCode(statusCode)
                .extract().body().as(clazz);
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
