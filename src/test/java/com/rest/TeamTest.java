package com.rest;

import com.rest.dto.PlayerDto;
import com.rest.dto.TeamDto;
import com.rest.steps.PlayerSteps;
import com.rest.steps.TeamSteps;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TeamTest {

    public PlayerSteps playerSteps = new PlayerSteps();
    public TeamSteps teamSteps = new TeamSteps();

    @Test
    public void getAllTeamTest() {

        TeamDto expectedTeamPlayer = TeamDto.builder()
                .name("Liverpool")
                .players(Arrays.asList(playerSteps.getAll()
                        .extract().body().as(PlayerDto[].class)))
                .build();

        List<TeamDto> actualTeams = Arrays.asList(
                teamSteps
                        .getAll()
                        .extract().body().as(TeamDto[].class)
        );

        Assertions
                .assertThat(actualTeams)
                .as("Team does not exist")
                .contains(expectedTeamPlayer);
    }

    @Test
    public void getTeamByIdTest() {
        TeamDto expectedTeam = TeamDto.builder()
                .name("Liverpool")
                .id(2)
                .build();

        List<TeamDto> actualTeam = Collections.singletonList(
                teamSteps.findById(expectedTeam.getId())
                        .extract().body().as(TeamDto.class)
        );

        Assertions
                .assertThat(actualTeam)
                .as("Team does not exist by id " + expectedTeam.getId())
                .contains(expectedTeam);
    }
}
