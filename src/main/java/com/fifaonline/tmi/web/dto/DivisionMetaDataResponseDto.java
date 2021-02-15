package com.fifaonline.tmi.web.dto;

import com.fifaonline.tmi.domain.Division;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DivisionMetaDataResponseDto {
    private String divisionId;
    private String divisionName;

    public Division toEntity() {
        return Division.builder()
                .divisionId(divisionId)
                .divisionName(divisionName)
                .build();
    }
}
