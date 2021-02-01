package com.fifaonline.tmi.web;

import com.fifaonline.tmi.service.UserService;
import com.fifaonline.tmi.web.dto.UserApiResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserApiController {
    private final UserService userService;

    @GetMapping("/api/v1/user/{nickname}")
    public Long requestUserInfo(@PathVariable String nickname) {
        UserApiResponseDto userApiResponseDto = userService.requestUserInfo(nickname);
        return userService.save(userApiResponseDto);
    }
}