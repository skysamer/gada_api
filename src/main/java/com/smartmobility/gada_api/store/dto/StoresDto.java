package com.smartmobility.gada_api.store.dto;

import com.querydsl.core.annotations.QueryProjection;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
@ApiModel(value = "가게 리스트 dto")
public class StoresDto {
    @ApiModelProperty(value = "고유번호")
    private final Long id;

    @ApiModelProperty(value = "자치단체구분코드")
    private final String localCode;

    @ApiModelProperty(value = "제어코드")
    private final String controlNumber;

    @ApiModelProperty(value = "이름")
    private final String name;

    @ApiModelProperty(value = "지번주소")
    private final String numberAddress;

    @ApiModelProperty(value = "도로명주소")
    private final String streetAddress;

    @ApiModelProperty(value = "전화번호")
    private final String phone;

    @ApiModelProperty(value = "업종명")
    private final String businessType;

    @ApiModelProperty(value = "위도")
    private final String lat;

    @ApiModelProperty(value = "경도")
    private final String lon;

    @QueryProjection
    public StoresDto(Long id, String localCode, String controlNumber, String name, String numberAddress,
                     String streetAddress, String phone, String businessType, String lat, String lon) {
        this.id = id;
        this.localCode = localCode;
        this.controlNumber = controlNumber;
        this.name = name;
        this.numberAddress = numberAddress;
        this.streetAddress = streetAddress;
        this.phone = phone;
        this.businessType = businessType;
        this.lat = lat;
        this.lon = lon;
    }
}
