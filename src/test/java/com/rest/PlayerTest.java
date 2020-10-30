package com.rest;

import com.rest.controller.PlayerController;
import com.rest.dto.ErrorMessageDto;
import com.rest.dto.PlayerDto;
import com.rest.steps.PlayerSteps;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
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

        ErrorMessageDto error = playerSteps.postBody(400, toCreatePlayer, ErrorMessageDto.class);

        Assertions
                .assertThat(error.getMessage())
                .isEqualTo("Player should not have id field");
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

        ErrorMessageDto error = playerSteps.getPlayerById(404, expectedId.getId(), ErrorMessageDto.class);

        Assertions
                .assertThat(error.getMessage())
                .isEqualTo(String.format("Player with id: [%s] not found", expectedId.getId()));
    }

    @Test
    public void updateUserTest() {

        PlayerDto toUpdatePlayer = PlayerDto.builder()
                .id(9)
                .fullName("Sturridge")
                .position("RF")
                .teamName("Arsenal")
                .build();

        playerSteps.putBody(toUpdatePlayer);

        PlayerDto actual = playerSteps.getPlayerById(toUpdatePlayer.getId());

        Assertions.assertThat(actual)
                .isEqualTo(toUpdatePlayer);
    }

    @Test
    public void updateUserWithWrongIdTest() {

        PlayerDto toUpdatePlayer = PlayerDto.builder()
                .id(222)
                .fullName("Sturridge")
                .position("RF")
                .teamName("Liverpool")
                .build();

        ErrorMessageDto error = playerSteps.putBody(404, toUpdatePlayer, ErrorMessageDto.class);

        Assertions
                .assertThat(error.getMessage())
                .isEqualTo("Player with id: " + toUpdatePlayer.getId() + " not found");
    }

    @Test
    public void deletePlayerTest() {
        List<PlayerDto> current = Arrays.asList(playerController.getAll().extract().body().as(PlayerDto[].class));
        if (current.isEmpty()) {
            throw new RuntimeException("No available players");
        }
        int randomIndex = new Random().nextInt(current.size());
        int randomId = current.get(randomIndex).getId();

        playerSteps.deletePlayer(randomId);
        ErrorMessageDto error = playerSteps.getPlayerById(404, randomId, ErrorMessageDto.class);

        Assertions
                .assertThat(error.getMessage())
                .isEqualTo(String.format("Player with id: [%s] not found", randomId));
    }

    @Test
    public void deletePlayerWithWrongIdTest() {

        PlayerDto idOfNotExistingPlayer = PlayerDto.builder()
                .id(333)
                .build();

        ErrorMessageDto error = playerSteps.deletePlayer(404, idOfNotExistingPlayer.getId(), ErrorMessageDto.class);

        Assertions
                .assertThat(error.getMessage())
                .isEqualTo(String.format("Player with id: [%s] not found", idOfNotExistingPlayer.getId()));
    }


}
