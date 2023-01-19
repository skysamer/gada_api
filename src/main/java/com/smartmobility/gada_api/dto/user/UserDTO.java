package com.smartmobility.gada_api.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Random;

@Getter @Builder
@AllArgsConstructor @NoArgsConstructor
@ApiModel(value = "유저 정보 엔티티")
public class UserDTO implements UserDetails {
    @ApiModelProperty(value = "시퀀스")
    private Long id;

    @ApiModelProperty(value = "핸드폰번호")
    private String cell_num;

    @ApiModelProperty(value = "비밀번호")
    private String passwd;

    @ApiModelProperty(value = "닉네임")
    private String nickname;

    @ApiModelProperty(value = "필수약관동의여부")
    private int required_terms_yn;

    @ApiModelProperty(value = "선택약관동의여부")
    private int optional_terms_yn;

    @ApiModelProperty(value = "권한")
    private String role;

    @ApiModelProperty(value = "프로필이미지url")
    private String profile_img_url;

    @ApiModelProperty(value = "약자유형")
    private String category;

    @ApiModelProperty(value = "사용하는 장애 도구")
    private String tool_type;

    @ApiModelProperty(value = "선호하는 교통수단 1:버스, 2:지하철, 3: 장애인콜택시, 4:도보, 5:없음")
    private int f_mobil;

    @ApiModelProperty(value = "지하철 즐겨찾기")
    private String favorite_station;

    public void setForSignUp(String encodedPassword){
        this.passwd = encodedPassword;
        this.role = "ROLE_USER";

        Random random = new Random();
        this.nickname = nickname + "_" + random.ints(48, 122 + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(5)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return passwd;
    }

    @Override
    public String getUsername() {
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
