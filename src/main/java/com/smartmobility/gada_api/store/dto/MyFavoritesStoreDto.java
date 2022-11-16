package com.smartmobility.gada_api.store.dto;

import com.querydsl.core.annotations.QueryProjection;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import javax.persistence.Column;

@Getter
@ApiModel(value = "내가 즐겨찾기한 가게 정보 dto")
public class MyFavoritesStoreDto {
    @ApiModelProperty(value = "가게고유번호")
    private final Long storeId;

    @ApiModelProperty(value = "이름")
    private final String name;

    @ApiModelProperty(value = "지번주소")
    @Column(name = "number_address")
    private final String numberAddress;

    @ApiModelProperty(value = "도로명주소")
    @Column(name = "street_address")
    private final String streetAddress;

    @ApiModelProperty(value = "업종명")
    @Column(name = "business_type")
    private final String businessType;

    @ApiModelProperty(value = "위도")
    private final String lat;

    @ApiModelProperty(value = "경도")
    private final String lon;

    @ApiModelProperty(value = "휠체어가 가능한지 (1, 0, -1)")
    @Column(name = "is_wheelchair")
    private final int isWheelchair;

    @ApiModelProperty(value = "유아차 동반가능여부 (1, 0, -1)")
    @Column(name = "is_baby_car")
    private final int isBabyCar;

    @ApiModelProperty(value = "장애인 화장실이 있나요 (1, 0, -1)")
    @Column(name = "is_disabled_toilet")
    private final int isDisabledToilet;

    @ApiModelProperty(value = "어린이도 갈 수 있나요 (1, 0, -1)")
    @Column(name = "is_child_ok")
    private final int isChildOk;

    @ApiModelProperty(value = "음성 안내, 점자 메뉴판이 있나요 (1, 0, -1)")
    @Column(name = "is_voice_guide")
    private final int isVoiceGuide;

    @ApiModelProperty(value = "주차 시설이 있나요 (1, 0, -1)")
    @Column(name = "is_parking_lot")
    private final int isParkingLot;

    @ApiModelProperty(value = "화장실이 있나요 (1, 0, -1)")
    @Column(name = "is_toilet")
    private final int isToilet;

    @QueryProjection
    public MyFavoritesStoreDto(Long storeId, String name, String numberAddress, String streetAddress, String businessType, String lat, String lon,
                               int isWheelchair, int isBabyCar, int isDisabledToilet, int isChildOk, int isVoiceGuide, int isParkingLot, int isToilet) {
        this.storeId = storeId;
        this.name = name;
        this.numberAddress = numberAddress;
        this.streetAddress = streetAddress;
        this.businessType = businessType;
        this.lat = lat;
        this.lon = lon;
        this.isWheelchair = isWheelchair;
        this.isBabyCar = isBabyCar;
        this.isDisabledToilet = isDisabledToilet;
        this.isChildOk = isChildOk;
        this.isVoiceGuide = isVoiceGuide;
        this.isParkingLot = isParkingLot;
        this.isToilet = isToilet;
    }
}
