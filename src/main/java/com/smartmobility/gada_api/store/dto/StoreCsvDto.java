package com.smartmobility.gada_api.store.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter @Builder
@ToString
public class StoreCsvDto {
    @ApiModelProperty(value = "자치단체구분코드")
    private String localCode;

    @ApiModelProperty(value = "이름")
    private String name;

    @ApiModelProperty(value = "지번주소")
    private String numberAddress;

    @ApiModelProperty(value = "도로명주소")
    private String streetAddress;

    @ApiModelProperty(value = "전화번호")
    private String phone;

    @ApiModelProperty(value = "업종명")
    private String businessType;

    @ApiModelProperty(value = "영업시간")
    private String hours;

    @ApiModelProperty(value = "위도")
    private String lat;

    @ApiModelProperty(value = "경도")
    private String lon;
}
