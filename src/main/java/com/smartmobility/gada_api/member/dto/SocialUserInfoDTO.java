package com.smartmobility.gada_api.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor @AllArgsConstructor
public class SocialUserInfoDTO {
    private String email;
    private String username;
    private boolean isExist;
}
