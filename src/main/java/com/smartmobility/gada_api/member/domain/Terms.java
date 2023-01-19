package com.smartmobility.gada_api.member.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable @Getter
@AllArgsConstructor @NoArgsConstructor
@ApiModel(value = "약관동의여부")
public class Terms {
    @ApiModelProperty(value = "필수약관동의여부")
    @Column(name = "required_terms_yn")
    private int requiredTermsYn;

    @ApiModelProperty(value = "선택약관동의여부")
    @Column(name = "optional_terms_yn")
    private int optionalTermsYn;
}
