package com.fifaonline.tmi.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity
public class Player {
    @Id
    @Column
    private String id;

    @Column
    private String name;

    @Builder
    public Player(String id, String name) {
        this.id = id;
        this.name = name;
    }
}