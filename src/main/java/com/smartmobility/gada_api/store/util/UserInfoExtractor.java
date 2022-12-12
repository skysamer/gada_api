package com.smartmobility.gada_api.store.util;

import com.smartmobility.gada_api.config.JwtTokenProvider;
import com.smartmobility.gada_api.member.domain.Member;
import com.smartmobility.gada_api.member.repository.MemberRepository;
import com.smartmobility.gada_api.member.type.Provider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserInfoExtractor {
    private final JwtTokenProvider jwtTokenProvider;
    private final MemberRepository memberRepository;

    public Member extract(String token) {
        String[] pk;
        pk = jwtTokenProvider.getUserPk(token).split("&");
        return memberRepository.findByEmailAndProvider(pk[0], Provider.valueOf(pk[1]));
    }
}
