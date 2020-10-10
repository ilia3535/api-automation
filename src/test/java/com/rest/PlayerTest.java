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

        PlayerDto actual = playerSteps.postBody(toCreatePlayer)
                .extract().body().as(PlayerDto.class);

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
    public void getPlayerByIdTest() {
        PlayerDto expectedPlayer = PlayerDto.builder()
                .id(22)
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


}
