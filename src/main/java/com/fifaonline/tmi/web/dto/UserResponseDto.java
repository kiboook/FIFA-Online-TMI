package com.fifaonline.tmi.web.dto;

import com.fifaonline.tmi.domain.User;
import lombok.Getter;

@Getter
public class UserResponseDto {
    private String accessId;
    private String nickname;
    private int level;

    public UserResponseDto(User entity) {
        this.accessId = entity.getAccessId();
        this.nickname = entity.getNickname();
        this.level = entity.getLevel();
    }
}