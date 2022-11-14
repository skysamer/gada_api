package com.smartmobility.gada_api.member.util;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "oAuthClient", url = "https://appleid.apple.com/auth")
public interface AppleClient {
    @GetMapping(value = "/keys")
    ApplePublicKeyResponse getAppleAuthPublicKey();

}
