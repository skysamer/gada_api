package com.smartmobility.gada_api.global.controller;

import com.smartmobility.gada_api.config.JwtTokenProvider;
import com.smartmobility.gada_api.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HomeController {
    private final JwtTokenProvider jwtTokenProvider;
    @GetMapping("/")
    public String home(){
        return "hello gada 0.1.11";
    }

//    @GetMapping("/test")
//    public String test(@AuthenticationPrincipal Member member){
//        System.out.println("member = " + member);
//        return "test";
//    }
}