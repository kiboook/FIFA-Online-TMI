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
public class Division {
    @Id
    @Column
    private String divisionId;

    @Column
    private String divisionName;

    @Builder
    public Division(String divisionId, String divisionName) {
        this.divisionId = divisionId;
        this.divisionName = divisionName;
    }
}
