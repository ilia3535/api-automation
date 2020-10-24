package com.rest.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"timestamp", "extraInfo"})
public class ErrorMessageDto {
    private Long timestamp;
    private String message;
    private String extraInfo;
}
