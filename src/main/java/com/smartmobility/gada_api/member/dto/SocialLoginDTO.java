package com.smartmobility.gada_api.member.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@ApiModel(value = "소셜로그인 body 객체")
public class SocialLoginDTO {
    @ApiModelProperty(value = "소셜로그인 정보 (google, kakao, apple)")
    @NotNull(message = "요청값은 null일 수 없습니다")
    private String provider;

    @ApiModelProperty(value = "애플의 닉네임 정보")
    @NotNull(message = "요청값은 null일 수 없습니다")
    private String sub;
}
