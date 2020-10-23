package com.rest;

import com.rest.controller.PlayerController;
import com.rest.dto.PlayerDto;
import com.rest.steps.PlayerSteps;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PlayerTest {

    public PlayerSteps playerSteps = new PlayerSteps();
    public PlayerController playerController = new PlayerController();

    @Test
    public void getAllPlayers() {

        PlayerDto expectedPlayer = PlayerDto.builder()
                .id(12)
                .fullName("Joe Gomez")
                .position("DF")
                .teamName("Liverpool")
                .build();

        List<PlayerDto> actualPlayers = playerSteps.getAll();

        Assertions.assertThat(actualPlayers)
                .as("Player is not found in the list")
                .contains(expectedPlayer);
    }

    @Test
    public void createNewUserTest() {
        List<PlayerDto> current = Arrays.asList(playerController.getAll().extract().body().as(PlayerDto[].class));
        List<PlayerDto> sorted = current.stream().sorted(new Comparator<PlayerDto>() {
            public int compare(PlayerDto o1, PlayerDto o2) {
                return o1.getId().compareTo(o2.getId());
            }
        }).collect(Collectors.toList());

        int expectedId = sorted.get(sorted.size() - 1).getId() + 1;

        PlayerDto toCreatePlayer = PlayerDto.builder()
                .fullName("Roberto Firmino")
                .position("CFD")
                .teamName("Liverpool")
                .build();

        PlayerDto actual = playerSteps.postBody(toCreatePlayer);

        Assertions.assertThat(actual)
                .isEqualTo(toCreatePlayer);
        Assertions.assertThat(actual.getId())
                .as("Wrong id")
                .isEqualTo(expectedId)
                .isNotZero()
                .isNotNegative()
                .isNotNull();
    }

    @Test
    public void createNewUserWithIdNegativeTest() {

        PlayerDto toCreatePlayer = PlayerDto.builder()
                .id(40)
                .fullName("Test Player")
                .position("FF")
                .teamName("Lasc")
                .build();

        playerSteps.postBody(400, toCreatePlayer);
    }

    @Test
    public void getPlayerByIdTest() {
        PlayerDto expectedPlayer = PlayerDto.builder()
                .id(27)
                .fullName("Roberto Firmino")
                .position("CFD")
                .teamName("Liverpool")
                .build();


        PlayerDto actualPlayer = playerSteps
                .getPlayerById(expectedPlayer.getId());

        Assertions
                .assertThat(actualPlayer)
                .as("Players not match on id's")
                .isEqualTo(expectedPlayer);
    }

    @Test
    public void getPlayerByNotExistingIdTest() {
        PlayerDto expectedId = PlayerDto.builder()
                .id(200)
                .build();

        playerSteps.getPlayerById(404, expectedId.getId());
    }

    @Test
    public void updateUserTest() {

        PlayerDto toUpdatePlayer = PlayerDto.builder()
                .fullName("Sturridge")
                .position("RF")
                .teamName("Trabzonspor")
                .build();

        PlayerDto actual = playerSteps.putBody(toUpdatePlayer, 9);

        Assertions.assertThat(actual)
                .isEqualTo(toUpdatePlayer);
    }

    @Test
    public void updateUserWithWrongIdTest() {

        PlayerDto toUpdatePlayer = PlayerDto.builder()
                .fullName("Sturridge")
                .position("RF")
                .teamName("Trabzonspor")
                .build();

        playerSteps.putBody(404, toUpdatePlayer, 222);

    }

    @Test
    public void deletePlayerTest() {
        playerSteps.deletePlayer(8);
        playerSteps.getPlayerById(404, 8);
    }

    @Test
    public void deletePlayerWithWrongIdTest() {
        playerSteps.deletePlayer(404, 333);
    }


}
