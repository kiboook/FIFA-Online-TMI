package com.fifaonline.tmi.web.dto;

import com.fifaonline.tmi.domain.User;
import lombok.Getter;

@Getter
public class UserInfoResponseDto {
    private String accessId;
    private String nickname;
    private int level;

    public UserInfoResponseDto(User entity) {
        this.accessId = entity.getAccessId();
        this.nickname = entity.getNickname();
        this.level = entity.getLevel();
    }
}