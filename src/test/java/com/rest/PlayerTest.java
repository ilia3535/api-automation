package com.rest;

import com.rest.dto.PlayerDto;
import com.rest.steps.PlayerSteps;
import org.testng.annotations.Test;

public class PlayerTest {

    public PlayerSteps playerSteps = new PlayerSteps();

    @Test
    public void createNewUserTest() {
        PlayerDto playerName = PlayerDto.builder()
                .fullName("Roberto Firmino")
                .position("CFD")
                .teamName("Liverpool")
                .build();

        playerSteps.postBody(playerName.getFullName(), playerName.getPosition(), playerName.getTeamName());

    }


}
