package com.fifaonline.tmi.web;

import com.fifaonline.tmi.service.UserService;
import com.fifaonline.tmi.web.dto.UserApiResponseDto;
import com.fifaonline.tmi.web.dto.UserInfoResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserApiController {
    private final UserService userService;

    @GetMapping("/api/v1/user/{nickname}")
    public String requestUserInfo(@PathVariable String nickname) {
        UserApiResponseDto userApiResponseDto = userService.requestUserInfo(nickname);
        return userService.save(userApiResponseDto);
    }

    @GetMapping("/api/v1/user/division/{nickname}")
    public String requestUserAccessID(@PathVariable String nickname) {
        UserInfoResponseDto userInfoResponseDto = userService.findById(nickname);
        return userInfoResponseDto.getAccessId();
    }
}