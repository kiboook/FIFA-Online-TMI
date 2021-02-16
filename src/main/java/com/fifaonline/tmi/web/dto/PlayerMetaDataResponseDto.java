package com.fifaonline.tmi.web.dto;

import com.fifaonline.tmi.domain.Player;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PlayerMetaDataResponseDto {
    private String id;
    private String name;

    public Player toEntity() {
        return Player.builder()
                .id(id)
                .name(name)
                .build();
    }
}
