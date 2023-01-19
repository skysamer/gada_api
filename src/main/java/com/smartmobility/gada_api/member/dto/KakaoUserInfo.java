package com.smartmobility.gada_api.member.dto;

import lombok.Getter;
import lombok.ToString;

import java.util.Map;

@Getter @ToString
public class KakaoUserInfo {
    private String id;
    private String connected_at;
    private Map<String, String> properties;
    private Map<String, Object> kakao_account;
}
