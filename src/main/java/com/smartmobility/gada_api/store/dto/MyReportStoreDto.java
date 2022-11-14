package com.smartmobility.gada_api.store.dto;

import com.querydsl.core.annotations.QueryProjection;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@ApiModel(value = "내가 제보한 가게정보 dto")
public class MyReportStoreDto {
    @ApiModelProperty(value = "고유번호")
    private Long id;

    @ApiModelProperty(value = "이름")
    private String name;

    @ApiModelProperty(value = "지번주소")
    private String numberAddress;

    @ApiModelProperty(value = "도로명주소")
    private String streetAddress;

    @ApiModelProperty(value = "업종명")
    private String businessType;

    @ApiModelProperty(value = "위도")
    private String lat;

    @ApiModelProperty(value = "경도")
    private String lon;

    @ApiModelProperty(value = "제보날짜")
    private LocalDateTime createdAt;

    @QueryProjection
    public MyReportStoreDto(Long id, String name, String numberAddress, String streetAddress, String businessType, String lat, String lon, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.numberAddress = numberAddress;
        this.streetAddress = streetAddress;
        this.businessType = businessType;
        this.lat = lat;
        this.lon = lon;
        this.createdAt = createdAt;
    }
}
