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
public class MatchType {

    @Id
    @Column
    private String matchType;

    @Column
    private String desc;

    @Builder
    public MatchType(String matchType, String desc) {
        this.matchType = matchType;
        this.desc = desc;
    }
}