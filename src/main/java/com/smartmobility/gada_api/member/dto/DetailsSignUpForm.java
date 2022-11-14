package com.smartmobility.gada_api.member.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
@ApiModel(value = "상세 회원가입 정보 입력 폼")
public class DetailsSignUpForm {
    @ApiModelProperty(value = "닉네임")
    private String nickname;

    @ApiModelProperty(value = "필수약관동의여부")
    private int requiredTermsYn;

    @ApiModelProperty(value = "선택약관동의여부")
    private int optionalTermsYn;

    @ApiModelProperty(value = "약자유형 (DISABLED, PREGNANT, ELDERLY, NONE)")
    private String underPrivileged;

    @ApiModelProperty(value = "사용하는 장애 도구 (WHEELCHAIR, CANE, COLORBLIND, ETC)")
    private String toolType;

    @ApiModelProperty(value = "선호하는 교통수단 (BUS, SUBWAY, TAXI, FOOT, NONE))")
    private String preferredMobil;

    @ApiModelProperty(value = "지하철 즐겨찾기")
    private String favoriteStation;
}
