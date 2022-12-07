package com.smartmobility.gada_api.store.dto;

import com.querydsl.core.annotations.QueryProjection;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter @ApiModel(value = "가게별 상세정보")
public class TotalStoreInfoDto {
    @ApiModelProperty(value = "가게 고유번호")
    private final Long id;

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

    @ApiModelProperty(value = "휠체어가 가능한지 (1 이상일 경우 존재)")
    private final int isWheelchair;

    @ApiModelProperty(value = "유아차 동반가능여부 (1 이상일 경우 존재)")
    private final int isBabyCar;

    @ApiModelProperty(value = "장애인 화장실이 있나요 (1 이상일 경우 존재)")
    private final int isDisabledToilet;

    @ApiModelProperty(value = "어린이도 갈 수 있나요 (1 이상일 경우 존재)")
    private final int isChildOk;

    @ApiModelProperty(value = "음성 안내, 점자 메뉴판이 있나요 (1 이상일 경우 존재)")
    private final int isVoiceGuide;

    @ApiModelProperty(value = "주차 시설이 있나요 (1 이상일 경우 존재)")
    private final int isParkingLot;

    @ApiModelProperty(value = "화장실이 있나요 (1 이상일 경우 존재)")
    private final int isToilet;

    @ApiModelProperty(value = "즐겨찾기 등록여부")
    private boolean isMyFavorites;

    @ApiModelProperty(value = "리뷰개수")
    private long reviewCount;

    @QueryProjection
    public TotalStoreInfoDto(Long id, String name, String numberAddress, String streetAddress, String phone, String businessType, String lat, String lon,
                             int isWheelchair, int isBabyCar, int isDisabledToilet, int isChildOk, int isVoiceGuide, int isParkingLot, int isToilet) {
        this.id = id;
        this.name = name;
        this.numberAddress = numberAddress;
        this.streetAddress = streetAddress;
        this.phone = phone;
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

    public void countReview(long count){
        this.reviewCount = count;
    }

    public void checkFavorites(boolean isExists){
        this.isMyFavorites = isExists;
    }
}
