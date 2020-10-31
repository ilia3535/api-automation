package com.rest;

import com.rest.controller.TeamController;
import com.rest.dto.PlayerDto;
import com.rest.dto.TeamDto;
import com.rest.steps.PlayerSteps;
import com.rest.steps.TeamSteps;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TeamTest {

    public PlayerSteps playerSteps = new PlayerSteps();
    public TeamSteps teamSteps = new TeamSteps();
    public TeamController teamController = new TeamController();

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

        List<TeamDto> actualTeams = teamSteps.getAll();

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

    @Test
    public void createNewTeamTest() {

        List<TeamDto> current = Arrays.asList(teamController.getAll().extract().body().as(TeamDto[].class));
        List<TeamDto> sorted = current.stream().sorted(new Comparator<TeamDto>() {
            public int compare(TeamDto o1, TeamDto o2) {
                return o1.getId().compareTo(o2.getId());
            }
        }).collect(Collectors.toList());

        int expectedId = sorted.get(sorted.size() - 1).getId() + 1;

        List<PlayerDto> players = new ArrayList<>();

        TeamDto toCreateTeam = TeamDto.builder()
                .id(expectedId)
                .name("Chelsea")
                .players(players)
                .captainId(2)
                .build();

        TeamDto actual = teamSteps.postBody(toCreateTeam);

        Assertions.assertThat(actual)
                .isEqualTo(toCreateTeam);
        Assertions.assertThat(actual.getId())
                .as("Wrong id")
                .isEqualTo(expectedId)
                .isNotZero()
                .isNotNegative()
                .isNotNull();
    }

    @Test
    public void assignPlayerToTheTeam() {

        int liverpoolTeamId = 2;

        PlayerDto expectedPlayer = PlayerDto.builder()
                .id(2)
                .fullName("Héctor Bellerín")
                .teamName("Liverpool")
                .position("DF")
                .build();


        teamSteps.putBody(liverpoolTeamId, expectedPlayer.getId());

        TeamDto actualTeam = teamSteps.findById(liverpoolTeamId);

        PlayerDto actualPlayer = playerSteps.getPlayerById(expectedPlayer.getId());

        Assertions
                .assertThat(actualPlayer)
                .as("Player hasn't found")
                .isEqualTo(expectedPlayer);
        Assertions
                .assertThat(actualTeam.getPlayers())
                .contains(expectedPlayer);
    }
}
