package com.fifaonline.tmi.web;

import com.fifaonline.tmi.service.UserService;
import com.fifaonline.tmi.web.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {
    private final UserService userService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/user/info/{nickname}")
    public String userInfo(@PathVariable String nickname, Model model) {
        UserResponseDto userResponseDto = userService.findById(nickname);
        model.addAttribute("user", userResponseDto);
        return "user-info";
    }

    @GetMapping("/user/division/{nickname}")
    public String userDivision(@PathVariable String nickname) {
        // nickname 가지고 DB 에서 유저 조회 한 뒤 accessId 통해 역대 최고 등급 API 찌르기
        return "user-division";
    }
}