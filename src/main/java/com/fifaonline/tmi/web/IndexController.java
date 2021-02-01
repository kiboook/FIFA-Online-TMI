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

    @GetMapping("/user/info/{id}")
    public String userInfo(@PathVariable Long id, Model model) {
        UserResponseDto userResponseDto = userService.findById(id);
        model.addAttribute("user", userResponseDto);
        return "user-info";
    }
}
