package com.smartmobility.gada_api.member.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable @Builder @Getter
@NoArgsConstructor @AllArgsConstructor
@ApiModel(value = "유저세부정보")
public class MemberDetails {
    @ApiModelProperty(value = "약자유형 (DISABLED, PREGNANT, ELDERLY, NONE)")
    @Column(name = "under_privileged")
    private String underPrivileged;

    @ApiModelProperty(value = "사용하는 장애 도구 (WHEELCHAIR, CANE, COLORBLIND, ETC)")
    @Column(name = "tool_type")
    private String toolType;

    @ApiModelProperty(value = "선호하는 교통수단 (BUS, SUBWAY, TAXI, FOOT, NONE))")
    @Column(name = "preferred_mobil")
    @Enumerated(value = EnumType.STRING)
    private PreferredMobil preferredMobil;

    @ApiModelProperty(value = "지하철 즐겨찾기")
    @Column(name = "favorite_station")
    private String favoriteStation;
}
