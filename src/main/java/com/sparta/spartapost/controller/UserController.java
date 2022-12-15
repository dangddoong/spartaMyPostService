package com.sparta.spartapost.controller;

import com.sparta.spartapost.dto.LoginRequestDto;
import com.sparta.spartapost.dto.SignupRequestDto;
import com.sparta.spartapost.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

// @RestController는 @Controller에 @ResponseBody가 추가된 것. 주용도는 Json형태로 객체 데이터를 반환하는 것.
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;


    @GetMapping("/signup")
    public ModelAndView signupPage() {
        return new ModelAndView("signup");
    }

    @GetMapping("/login")
    public ModelAndView loginPage() {
        return new ModelAndView("login");
    }

    @PostMapping("/signup")
    public String signup(@RequestBody SignupRequestDto signupRequestDto) {
        userService.signup(signupRequestDto);
        return "redirect:/api/user/login";
    }

    @PostMapping("/login")
    public String login(LoginRequestDto loginRequestDto) {
        userService.login(loginRequestDto);
        return "redirect:/api/shop";
    }

}
