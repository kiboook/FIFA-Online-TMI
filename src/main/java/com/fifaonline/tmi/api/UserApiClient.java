package com.fifaonline.tmi.api;

import com.fifaonline.tmi.config.ApiKey;
import com.fifaonline.tmi.web.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Service
public class UserApiClient {

    private final RestTemplate restTemplate;

    private final String API_KEY = ApiKey.API_KEY;

    private final String UserInfoUrl = "https://api.nexon.co.kr/fifaonline4/v1.0/users?nickname={nickname}";

    public UserResponseDto requestUserInfo(String nickname) {
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", API_KEY);

        final HttpEntity<String> entity = new HttpEntity<>(httpHeaders);

        return restTemplate.exchange(UserInfoUrl, HttpMethod.GET, entity, UserResponseDto.class, nickname).getBody();
    }
}