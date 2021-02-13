package com.fifaonline.tmi.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserDivisionResponseDto {
    private int matchType;
    private int division;
    private String achievementDate;

    // Date 뒷 부분을 자르기 위해 setter 생성
    public void setAchievementDate(String achievementDate) {
        this.achievementDate = achievementDate;
    }
}
