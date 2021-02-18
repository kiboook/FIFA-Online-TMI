package com.fifaonline.tmi.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class buyRecordResponseDto {
    private String tradeDate;
    private String saleSn;
    private String spid;
    private String grade;
    private String value;
}
