package com.rest;

import com.rest.dto.PlayerDto;
import com.rest.steps.PlayerSteps;
import io.restassured.response.ValidatableResponse;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.containsString;

public class PlayerTest {

    public PlayerSteps playerSteps = new PlayerSteps();

    @Test
    public void createNewUserTest() {
        PlayerDto playerData = PlayerDto.builder()
                .fullName("Roberto Firmino")
                .position("CFD")
                .teamName("Liverpool")
                .build();

        ValidatableResponse response = playerSteps.postBody(playerData.getFullName(), playerData.getPosition(), playerData.getTeamName())
                .body("fullName", containsString(playerData.getFullName()))
                .body("position", containsString(playerData.getPosition()))
                .body("teamName", containsString(playerData.getTeamName()));
    }

    @Test
    public void getPlayerByIdTest() {
        PlayerDto expectedPlayer = PlayerDto.builder()
                .fullName("Roberto Firmino")
                .position("CFD")
                .teamName("Liverpool")
                .build();


        PlayerDto playerId = PlayerDto.builder()
                .id(23)
                .build();

        List<PlayerDto> actualPlayer = Arrays.asList(
                playerSteps
                        .getPlayerById(playerId.getId())
                        .extract().body().as(PlayerDto[].class)
        );

        Assertions
                .assertThat(actualPlayer)
                .as("Players not match on id's")
                .contains(expectedPlayer);
    }


}
