package com.smartmobility.gada_api.member.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartmobility.gada_api.member.domain.Member;
import com.smartmobility.gada_api.member.dto.AppleUserInfo;
import com.smartmobility.gada_api.member.dto.GoogleUserInfo;
import com.smartmobility.gada_api.member.dto.KakaoUserInfo;
import com.smartmobility.gada_api.member.dto.SocialUserInfoDTO;
import com.smartmobility.gada_api.member.repository.MemberRepository;
import com.smartmobility.gada_api.member.type.Provider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.Base64.Decoder;

@Component
@RequiredArgsConstructor
public class OAuthTokenValidator {
    private final ObjectMapper objectMapper;
    private final MemberRepository memberRepository;
    private final GoogleClient googleClient;
    private final KakaoClient kakaoClient;

    public SocialUserInfoDTO verifyGoogleToken(String token) {
        GoogleUserInfo userInfo = googleClient.getGoogleUserInfo("Bearer " + token);
        boolean isExist = findGoogleUserInfo(userInfo);
        return new SocialUserInfoDTO(userInfo.getEmail(), userInfo.getName(), isExist);
    }

    public boolean findGoogleUserInfo(GoogleUserInfo userInfo){
        String email = userInfo.getEmail();
        return memberRepository.existsByEmailAndProvider(email, Provider.GOOGLE);
    }

    public SocialUserInfoDTO verifyKakaoToken(String token) throws Exception {
        KakaoUserInfo userInfo = kakaoClient.getKakaoUserInfo("Bearer " + token);
        boolean isExist = findKakaoUserInfo(userInfo);
        return new SocialUserInfoDTO((String) userInfo.getKakao_account().get("email"), userInfo.getProperties().get("nickname"), isExist);
    }

    public boolean findKakaoUserInfo(KakaoUserInfo userInfo){
        String email = (String) userInfo.getKakao_account().get("email");
        return memberRepository.existsByEmailAndProvider(email, Provider.KAKAO);
    }

    public SocialUserInfoDTO verifyAppleToken(String token, String sub) throws Exception {
        Member member = memberRepository.findByUsername(sub);
        if(member != null){
            return new SocialUserInfoDTO(member.getEmail(), member.getUsername(), true);
        }

        String[] check = token.split("\\.");
        Decoder decoder = Base64.getDecoder();
        String payload = new String(decoder.decode(check[1]));
        AppleUserInfo appleUserInfo = objectMapper.readValue(payload, AppleUserInfo.class);
        return new SocialUserInfoDTO(appleUserInfo.getEmail(), appleUserInfo.getSub(), false);
    }

}