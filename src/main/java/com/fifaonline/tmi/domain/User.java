package com.fifaonline.tmi.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String accessId;

    @Column
    private String nickname;

    @Column
    private int level;

    @Builder
    public User(String accessId, String nickname, int level) {
        this.accessId = accessId;
        this.nickname = nickname;
        this.level = level;
    }
}
