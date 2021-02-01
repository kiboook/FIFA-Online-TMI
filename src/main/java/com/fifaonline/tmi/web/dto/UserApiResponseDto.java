package com.fifaonline.tmi.web.dto;

import com.fifaonline.tmi.domain.User;
import lombok.Getter;

@Getter
public class UserApiResponseDto {
    private String accessId;
    private String nickname;
    private int level;

    public User toEntity() {
        return User.builder()
                .accessId(accessId)
                .nickname(nickname)
                .level(level).build();
    }
}