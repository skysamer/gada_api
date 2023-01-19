package com.smartmobility.gada_api.member.util;

import com.smartmobility.gada_api.member.dto.GoogleUserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "googleClient", url = "https://www.googleapis.com/oauth2/v1")
public interface GoogleClient {
    @GetMapping(value = "/userinfo")
    GoogleUserInfo getGoogleUserInfo(@RequestHeader("Authorization") String token);
}
