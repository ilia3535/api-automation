package com.rest;

import com.rest.dto.PlayerDto;
import com.rest.steps.PlayerSteps;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.containsString;

public class PlayerTest {

    public PlayerSteps playerSteps = new PlayerSteps();

    int id;

    @Test
    public void createNewUserTest() {
        PlayerDto playerData = PlayerDto.builder()
                .fullName("Roberto Firmino")
                .position("CFD")
                .teamName("Liverpool")
                .build();

        playerSteps.postBody(playerData.getFullName(), playerData.getPosition(), playerData.getTeamName())
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
                .id(22)
                .build();

        List<PlayerDto> actualPlayer = Collections.singletonList(
                playerSteps
                        .getPlayerById(playerId.getId())
                        .extract().body().as(PlayerDto.class)
        );

        Assertions
                .assertThat(actualPlayer)
                .as("Players not match on id's")
                .contains(expectedPlayer);
    }


}
