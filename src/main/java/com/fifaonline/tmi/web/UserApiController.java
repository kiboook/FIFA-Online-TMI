package com.fifaonline.tmi.web;

import com.fifaonline.tmi.config.ApiKey;
import com.fifaonline.tmi.service.UserService;
import com.fifaonline.tmi.web.dto.UserApiResponseDto;
import com.fifaonline.tmi.web.dto.UserInfoResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

@RequiredArgsConstructor
@RestController
public class UserApiController {
    @Inject
    private ApiKey apiKey;
    private final UserService userService;

    @GetMapping("/api/v1/user/{nickname}")
    public String requestUserInfo(@PathVariable String nickname) {
        UserApiResponseDto userApiResponseDto = userService.requestUserInfo(nickname);
        return userService.userInfoSave(userApiResponseDto);
    }

    @GetMapping("/api/v1/user/accessId/{nickname}")
    public String requestUserAccessID(@PathVariable String nickname) {
        UserInfoResponseDto userInfoResponseDto = userService.findById(nickname);
        return userInfoResponseDto.getAccessId();
    }

    @GetMapping("/api/v1/key")
    public String requestApiKey() {
        return apiKey.getKey();
    }
}