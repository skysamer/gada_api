package com.smartmobility.gada_api.member.util;

import com.smartmobility.gada_api.member.dto.KakaoUserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "kakaoClient", url = "https://kapi.kakao.com/v2")
public interface KakaoClient {
    @PostMapping(value = "/user/me")
    KakaoUserInfo getKakaoUserInfo(@RequestHeader("Authorization") String token);
}
