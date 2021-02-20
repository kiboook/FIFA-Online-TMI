package com.fifaonline.tmi.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SellRecordResponseDto {
    private String tradeDate;
    private String saleSn;
    private String spid;
    private String grade;
    private String value;
}
