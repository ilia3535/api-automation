package com.rest.dto;


import lombok.*;

import java.util.List;

@Data
@Builder
@EqualsAndHashCode(exclude = "id")
@NoArgsConstructor
@AllArgsConstructor
public class TeamDto {
    private int captainId;
    private int id;
    private String name;
    private List<PlayerDto> players;
}
