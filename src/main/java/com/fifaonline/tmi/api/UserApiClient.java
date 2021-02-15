package com.fifaonline.tmi.api;

import com.fifaonline.tmi.config.ApiKey;
import com.fifaonline.tmi.domain.Division;
import com.fifaonline.tmi.domain.MatchType;
import com.fifaonline.tmi.domain.MatchTypeRepository;
import com.fifaonline.tmi.web.dto.DivisionResponseDto;
import com.fifaonline.tmi.web.dto.MatchTypeResponseDto;
import com.fifaonline.tmi.web.dto.UserApiResponseDto;
import com.fifaonline.tmi.web.dto.UserDivisionResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.inject.Inject;

@RequiredArgsConstructor
@Service
public class UserApiClient {
    private final MatchTypeRepository matchTypeRepository;
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

    public MatchTypeResponseDto[] requestMatchTypeMetaData() {
        MatchTypeResponseDto[] matchTypeDtoArray = null;

        try {
            matchTypeDtoArray =
                    restTemplate.getForEntity("https://static.api.nexon.co.kr/fifaonline4/latest/matchtype.json",
                            MatchTypeResponseDto[].class).getBody();
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        return matchTypeDtoArray;
    }

    public DivisionResponseDto[] requestDivisionMetaData() {
        DivisionResponseDto[] divisionResponseDtoArray = null;

        try {
            divisionResponseDtoArray =
                    restTemplate.getForEntity("https://static.api.nexon.co.kr/fifaonline4/latest/division.json",
                            DivisionResponseDto[].class).getBody();
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        return divisionResponseDtoArray;
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
            val.setAchievementDate(val.getAchievementDate().split("T")[0]);
        }
        return userDivisionResponseDtoArray;
    }

    public String requestUserDivisionMatchType(String id) {
        MatchType entity = matchTypeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("매치 타입 에러!"));

        return entity.getDesc();
    }
}