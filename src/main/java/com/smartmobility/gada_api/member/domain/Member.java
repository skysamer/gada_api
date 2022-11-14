package com.smartmobility.gada_api.member.domain;

import com.smartmobility.gada_api.member.type.Provider;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Random;

@Getter @Entity
@ApiModel(value = "직원 정보 엔티티")
@Table(name = "members")
public class Member implements UserDetails {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "유저번호")
    @Column(name = "member_id", insertable = false)
    private Long id;

    @ApiModelProperty(value = "이메일")
    private String email;

    @ApiModelProperty(value = "이름")
    private String username;

    @ApiModelProperty(value = "닉네임")
    @Column(unique = true)
    private String nickname;

    @ApiModelProperty(value = "소셜로그인 (GOOGLE, APPLE, KAKAO)")
    @Enumerated(value = EnumType.STRING)
    private Provider provider;

    @ApiModelProperty(value = "권한등급")
    @Enumerated(value = EnumType.STRING)
    private Role role;

    @ApiModelProperty(value = "프로필 이미지 url")
    @Column(name = "profile_img_url")
    private String profileImgUrl;

    @ApiModelProperty(value = "약관동의여부")
    @Embedded
    private Terms terms;

    @ApiModelProperty(value = "유저 세부정보")
    @Embedded
    private MemberDetails details;

    public void setSocialProvider(Provider provider){
        this.provider = provider;
    }

    public void detailsSignUp(Terms terms, MemberDetails details, String nickname){
        this.terms = terms;
        this.details = details;
        this.role = Role.ROLE_USER;
        this.nickname = createNickname(nickname);
    }

    private String createNickname(String nickname){
        Random random = new Random();
        String randomNickname = nickname + "_" + random.ints(48, 122 + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(5)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return this.nickname == null ? randomNickname : nickname;
    }

    public void register(String profileImgUrl){
        this.profileImgUrl = profileImgUrl;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", nickname='" + nickname + '\'' +
                ", provider=" + provider +
                ", role=" + role +
                ", profileImgUrl='" + profileImgUrl + '\'' +
                ", terms=" + terms +
                ", details=" + details +
                '}';
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
