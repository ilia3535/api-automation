package com.rest.dto;


import lombok.*;

@Data
@Builder
@EqualsAndHashCode(exclude = "id")
@NoArgsConstructor
@AllArgsConstructor
public class PlayerDto {
    private int id;
    private String idParam;
    private String fullName;
    private String position;
    private String teamName;
}
