package com.fifaonline.tmi.web;

import com.fifaonline.tmi.service.UserService;
import com.fifaonline.tmi.web.dto.UserDivisionResponseDto;
import com.fifaonline.tmi.web.dto.UserInfoResponseDto;
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
        userService.requestMatchTypeMetaData();
        userService.requestDivisionMetaData();
        return "index";
    }

    @GetMapping("/user/info/{nickname}")
    public String userInfo(@PathVariable String nickname, Model model) {
        UserInfoResponseDto userResponseDto = userService.userInfoFindById(nickname);
        model.addAttribute("user", userResponseDto);
        return "user-info";
    }

    @GetMapping("/user/division/{nickname}")
    public String userDivision(@PathVariable String nickname, Model model) {
        UserDivisionResponseDto[] userDivisionResponseArray = userService.requestUserDivision(nickname);
        model.addAttribute("division", userDivisionResponseArray);
        return "user-division";
    }
}