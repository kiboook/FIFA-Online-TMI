package com.fifaonline.tmi.api;

import com.fifaonline.tmi.config.ApiKey;
import com.fifaonline.tmi.domain.*;
import com.fifaonline.tmi.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.inject.Inject;
import java.text.NumberFormat;

@RequiredArgsConstructor
@Service
public class UserApiClient {
    private final MatchTypeRepository matchTypeRepository;
    private final DivisionRepository divisionRepository;
    private final RestTemplate restTemplate;

    @Inject
    private ApiKey apiKey;

    public UserApiResponseDto requestUserInfo(String nickname) {
        final String UserInfoUrl = "https://api.nexon.co.kr/fifaonline4/v1.0/users?nickname={nickname}";
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", apiKey.getKey());
        final HttpEntity<String> entity = new HttpEntity<>(httpHeaders);

        return restTemplate.exchange(UserInfoUrl, HttpMethod.GET, entity, UserApiResponseDto.class, nickname).getBody();
    }

    public MatchTypeMetaDataResponseDto[] requestMatchTypeMetaData() {
        MatchTypeMetaDataResponseDto[] matchTypeDtoArray = null;

        try {
            matchTypeDtoArray =
                    restTemplate.getForEntity("https://static.api.nexon.co.kr/fifaonline4/latest/matchtype.json",
                            MatchTypeMetaDataResponseDto[].class).getBody();
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        return matchTypeDtoArray;
    }

    public DivisionMetaDataResponseDto[] requestDivisionMetaData() {
        DivisionMetaDataResponseDto[] divisionResponseDtoArray = null;

        try {
            divisionResponseDtoArray =
                    restTemplate.getForEntity("https://static.api.nexon.co.kr/fifaonline4/latest/division.json",
                            DivisionMetaDataResponseDto[].class).getBody();
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        return divisionResponseDtoArray;
    }

    public PlayerMetaDataResponseDto[] requestPlayerMetaData() {
        PlayerMetaDataResponseDto[] playerMetaDataResponseDtoArray = null;

        try {
            playerMetaDataResponseDtoArray =
                    restTemplate.getForEntity("https://static.api.nexon.co.kr/fifaonline4/latest/spid.json",
                            PlayerMetaDataResponseDto[].class).getBody();
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        return playerMetaDataResponseDtoArray;
    }

    public UserDivisionResponseDto[] requestUserDivision(String accessId) {
        UserDivisionResponseDto[] userDivisionResponseDtoArray = null;
        final String UserDivisionUrl = "https://api.nexon.co.kr/fifaonline4/v1.0/users/{accessid}/maxdivision";
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", apiKey.getKey());
        final HttpEntity<String> entity = new HttpEntity<>(httpHeaders);


        try {
            userDivisionResponseDtoArray =
                    restTemplate.exchange(UserDivisionUrl, HttpMethod.GET, entity,
                            UserDivisionResponseDto[].class, accessId).getBody();
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        assert userDivisionResponseDtoArray != null;
        for (UserDivisionResponseDto val : userDivisionResponseDtoArray) {
            val.setMatchType(requestUserDivisionMatchType(val.getMatchType()));
            val.setDivision(requestUserDivisionType(val.getDivision()));
            val.setAchievementDate(val.getAchievementDate().split("T")[0]);
        }
        return userDivisionResponseDtoArray;
    }

    public String requestUserDivisionMatchType(String id) {
        MatchType entity = matchTypeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("매치 타입 에러!"));

        return entity.getDesc();
    }

    public String requestUserDivisionType(String id) {
        Division entity = divisionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("등급 매치 에러!"));

        return entity.getDivisionName();
    }

    public BuyRecordResponseDto[] requestBuyRecord(String accessId) {
        BuyRecordResponseDto[] buyRecordResponseDtoArray = null;

        final String UserBuyRecordUrl =
                "https://api.nexon.co.kr/fifaonline4/v1.0/users/{accessid}/markets?tradetype=buy&offset=0&limit=7";
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", apiKey.getKey());
        final HttpEntity<String> entity = new HttpEntity<>(httpHeaders);

        try {
            buyRecordResponseDtoArray =
                    restTemplate.exchange(UserBuyRecordUrl, HttpMethod.GET, entity,
                            BuyRecordResponseDto[].class, accessId).getBody();
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        assert buyRecordResponseDtoArray != null;
        for (BuyRecordResponseDto val : buyRecordResponseDtoArray) {
            String valSpid = val.getSpid();
            String imgUrl = null;
            val.setTradeDate(val.getTradeDate().split("T")[0]);
            val.setValue(NumberFormat.getInstance().format(Long.valueOf(val.getValue())));
            try {
                restTemplate.getForEntity(
                        "https://fo4.dn.nexoncdn.co.kr/live/externalAssets/common/playersAction/p{spid}.png",
                        String.class, valSpid);
                imgUrl = "https://fo4.dn.nexoncdn.co.kr/live/externalAssets/common/playersAction/p" + valSpid + ".png";
                val.setSpid(imgUrl);
            } catch (Exception e1) {
                try {
                    restTemplate.getForEntity(
                            "https://fo4.dn.nexoncdn.co.kr/live/externalAssets/common/players/p{spid}.png",
                            String.class, valSpid);
                    imgUrl = "https://fo4.dn.nexoncdn.co.kr/live/externalAssets/common/players/p" + valSpid + ".png";
                    val.setSpid(imgUrl);
                } catch (Exception e2) {
                    val.setSpid("https://user-images.githubusercontent.com/54533309/108505829-58605c80-72fb-11eb-963d-eaf02f050f34.png");
                }
            }
        }
        return buyRecordResponseDtoArray;
    }

    public SellRecordResponseDto[] requestSellRecord(String accessId) {
        SellRecordResponseDto[] sellRecordResponseDtoArray = null;

        final String UserSellRecordUrl =
                "https://api.nexon.co.kr/fifaonline4/v1.0/users/{accessid}/markets?tradetype=sell&offset=0&limit=7";
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", apiKey.getKey());
        final HttpEntity<String> entity = new HttpEntity<>(httpHeaders);

        try {
            sellRecordResponseDtoArray =
                    restTemplate.exchange(UserSellRecordUrl, HttpMethod.GET, entity,
                            SellRecordResponseDto[].class, accessId).getBody();
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        assert sellRecordResponseDtoArray != null;
        for (SellRecordResponseDto val : sellRecordResponseDtoArray) {
            String valSpid = val.getSpid();
            String imgUrl = null;
            val.setTradeDate(val.getTradeDate().split("T")[0]);
            val.setValue(NumberFormat.getInstance().format(Long.valueOf(val.getValue())));
            try {
                restTemplate.getForEntity(
                        "https://fo4.dn.nexoncdn.co.kr/live/externalAssets/common/playersAction/p{spid}.png",
                        String.class, valSpid);
                imgUrl = "https://fo4.dn.nexoncdn.co.kr/live/externalAssets/common/playersAction/p" + valSpid + ".png";
                val.setSpid(imgUrl);
            } catch (Exception e1) {
                try {
                    restTemplate.getForEntity(
                            "https://fo4.dn.nexoncdn.co.kr/live/externalAssets/common/players/p{spid}.png",
                            String.class, valSpid);
                    imgUrl = "https://fo4.dn.nexoncdn.co.kr/live/externalAssets/common/players/p" + valSpid + ".png";
                    val.setSpid(imgUrl);
                } catch (Exception e2) {
                    val.setSpid("https://user-images.githubusercontent.com/54533309/108505829-58605c80-72fb-11eb-963d-eaf02f050f34.png");
                }
            }
        }
        return sellRecordResponseDtoArray;
    }
}