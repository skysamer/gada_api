package com.smartmobility.gada_api.member.service;

import com.smartmobility.gada_api.global.dto.HttpBodyMessage;
import com.smartmobility.gada_api.member.domain.*;
import com.smartmobility.gada_api.member.dto.DetailsSignUpForm;
import com.smartmobility.gada_api.member.dto.LoginResultDTO;
import com.smartmobility.gada_api.member.dto.SocialUserInfoDTO;
import com.smartmobility.gada_api.member.repository.MemberRepository;
import com.smartmobility.gada_api.member.type.Provider;
import com.smartmobility.gada_api.member.util.OAuthTokenValidator;
import com.smartmobility.gada_api.store.service.S3Client;
import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@Transactional
@RequiredArgsConstructor
public class OauthService implements UserDetailsService {
    private final MemberRepository memberRepository;
    private final S3Client s3Client;
    private final OAuthTokenValidator tokenValidator;
    private final ModelMapper modelMapper;

    @Override
    public UserDetails loadUserByUsername(String sub) throws UsernameNotFoundException {
        String[] pk = sub.split("&");
        return memberRepository.findByEmailAndProvider(pk[0], Provider.valueOf(pk[1]));
    }

    /*제공자 별 1차 소셜로그인*/
    public LoginResultDTO socialLogin(String token, Provider provider, String sub) throws Exception {
        SocialUserInfoDTO userInfo = new SocialUserInfoDTO();
        switch (provider){
            case APPLE:
                userInfo = tokenValidator.verifyAppleToken(token, sub);
                break;
            case KAKAO:
                userInfo = tokenValidator.verifyKakaoToken(token);
                break;
            case GOOGLE:
                userInfo = tokenValidator.verifyGoogleToken(token);
                break;
        }

        if(userInfo.isExist()){
            return login(userInfo.getEmail(), provider);
        }
        return socialSignUp(userInfo, provider);
    }

    /*신규 가입자의 1차 회원가입 로직*/
    public LoginResultDTO socialSignUp(SocialUserInfoDTO userInfo, Provider provider){
        Member member = modelMapper.map(userInfo, Member.class);
        member.setSocialProvider(provider);
        memberRepository.save(member);
        return new LoginResultDTO(member.getId());
    }

    /*로그인*/
    public LoginResultDTO login(String email, Provider provider){
        Member member = memberRepository.findByEmailAndProvider(email, provider);
        return modelMapper.map(member, LoginResultDTO.class);
    }

    /*추가적인 회원가입 로직*/
    public void detailsSignUp(Long id, DetailsSignUpForm detailsSignUpForm){
        Member member = memberRepository.findById(id).orElse(null);
        if(member == null){
            throw new RuntimeException("초기회원정보없음");
        }

        Terms terms = new Terms(detailsSignUpForm.getRequiredTermsYn(), detailsSignUpForm.getOptionalTermsYn());
        MemberDetails details = MemberDetails.builder()
                .underPrivileged(detailsSignUpForm.getUnderPrivileged())
                .favoriteStation(detailsSignUpForm.getFavoriteStation())
                .preferredMobil(PreferredMobil.valueOf(detailsSignUpForm.getPreferredMobil()))
                .toolType(detailsSignUpForm.getToolType())
                .build();
        member.detailsSignUp(terms, details, detailsSignUpForm.getNickname());
    }

    /*닉네임변경로직*/
    public HttpBodyMessage modify(Member member, String nickname){
        boolean isDuplicated = checkNicknameIsDuplicate(nickname);
        if(isDuplicated){
            return new HttpBodyMessage("fail", "닉네임중복");
        }

        member.update(nickname);
        memberRepository.save(member);
        return new HttpBodyMessage("success", "닉네임변경완료");
    }

    /*닉네임이 중복되는지 체크*/
    private boolean checkNicknameIsDuplicate(String nickname){
        return memberRepository.existsByNickname(nickname);
    }

    /*회원정보삭제 (영구삭제)*/
    public void remove(Long id){
        Member member = memberRepository.findById(id).orElse(null);
        if(member == null){
            throw new RuntimeException();
        }
        memberRepository.delete(member);
    }

    /*프로필이미지 등록 혹은 수정*/
    public void register(Member member, MultipartFile image) throws IOException {
        if(member.getProfileImgUrl() != null){
            s3Client.remove(member.getProfileImgUrl());
            member.register(null);
        }
        String imageUrl = s3Client.upload(image);
        member.register(imageUrl);
        memberRepository.save(member);
    }

    /*프로필이미지 삭제*/
    public void removeProfileImg(Member member){
        s3Client.remove(member.getProfileImgUrl());
        member.register(null);
        memberRepository.save(member);
    }
}
