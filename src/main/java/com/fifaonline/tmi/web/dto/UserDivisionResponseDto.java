package com.fifaonline.tmi.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDivisionResponseDto {
    private String matchType;
    private int division;
    private String achievementDate;
}
