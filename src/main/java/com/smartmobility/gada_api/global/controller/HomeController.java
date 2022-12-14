package com.smartmobility.gada_api.global.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HomeController {
    @Value("${server.port}")
    private String port;
    @GetMapping("/")
    public String home() {
        return "hello gada 0.1.19";
    }

    @GetMapping("/port")
    public String getPort(){
        return port;
    }
}