package com.smartmobility.gada_api.member.dto;

import com.smartmobility.gada_api.member.domain.MemberDetails;
import com.smartmobility.gada_api.member.domain.Role;
import com.smartmobility.gada_api.member.domain.Terms;
import com.smartmobility.gada_api.member.type.Provider;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embedded;

@Getter
@ApiModel(value = "로그인 반환 객체")
@RequiredArgsConstructor
public class LoginResultDTO {
    @ApiModelProperty(value = "유저번호")
    @Column(name = "member_id", insertable = false)
    private Long id;

    @ApiModelProperty(value = "이메일")
    private String email;

    @ApiModelProperty(value = "이름")
    private String username;

    @ApiModelProperty(value = "닉네임")
    private String nickname;

    @ApiModelProperty(value = "소셜로그인 (GOOGLE, APPLE, KAKAO)")
    private Provider provider;

    @ApiModelProperty(value = "권한등급")
    private Role role;

    @ApiModelProperty(value = "프로필 이미지 url")
    private String profileImgUrl;

    @ApiModelProperty(value = "약관동의여부")
    @Embedded
    private Terms terms;

    @ApiModelProperty(value = "유저 세부정보")
    @Embedded
    private MemberDetails details;

    public LoginResultDTO(Long id){
        this.id = id;
    }
}
