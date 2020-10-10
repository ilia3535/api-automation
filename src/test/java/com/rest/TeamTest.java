package com.rest;

import com.rest.dto.PlayerDto;
import com.rest.dto.TeamDto;
import com.rest.steps.PlayerSteps;
import com.rest.steps.TeamSteps;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

public class TeamTest {

    public PlayerSteps playerSteps = new PlayerSteps();
    public TeamSteps teamSteps = new TeamSteps();

    @Test
    public void getAllTeamTest() {
        List<PlayerDto> expectedPlayerForLiverpool = playerSteps.getAll()
                .stream()
                .filter(p -> "Liverpool".equalsIgnoreCase(p.getTeamName()))
                .collect(Collectors.toList());

        TeamDto expectedTeam = TeamDto.builder()
                .id(2)
                .captainId(0)
                .name("Liverpool")
                .players(expectedPlayerForLiverpool)
                .build();

        List<TeamDto> actualTeams =
                teamSteps
                        .getAll();

        Assertions
                .assertThat(actualTeams)
                .as("Team does not exist")
                .contains(expectedTeam);
    }

    @Test
    public void getTeamByIdTest() {
        TeamDto expectedTeam = TeamDto.builder()
                .name("Liverpool")
                .id(2)
                .captainId(0)
                .build();

        TeamDto actualTeam = teamSteps.findById(expectedTeam.getId());

        Assertions
                .assertThat(actualTeam)
                .as("Team does not exist by id " + expectedTeam.getId())
                .isEqualToComparingOnlyGivenFields(expectedTeam, "name", "captainId");
    }
}
