package com.smartmobility.gada_api.store.dto;

import com.querydsl.core.annotations.QueryProjection;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
@ApiModel(value = "가다 추천 함께가게 dto")
public class RecommendedStoreDto {
    @ApiModelProperty(value = "가게고유번호")
    private final Long storeId;

    @ApiModelProperty(value = "이름")
    private final String name;

    @ApiModelProperty(value = "업종명")
    private final String businessType;

    @ApiModelProperty(value = "휠체어가 가능한지 (1, 0, -1)")
    private final int isWheelchair;

    @ApiModelProperty(value = "유아차 동반가능여부 (1, 0, -1)")
    private final int isBabyCar;

    @ApiModelProperty(value = "장애인 화장실이 있나요 (1, 0, -1)")
    private final int isDisabledToilet;

    @ApiModelProperty(value = "어린이도 갈 수 있나요 (1, 0, -1)")
    private final int isChildOk;

    @ApiModelProperty(value = "음성 안내, 점자 메뉴판이 있나요 (1, 0, -1)")
    private final int isVoiceGuide;

    @ApiModelProperty(value = "주차 시설이 있나요 (1, 0, -1)")
    private final int isParkingLot;

    @ApiModelProperty(value = "화장실이 있나요 (1, 0, -1)")
    private final int isToilet;

    @ApiModelProperty(value = "이미지 url")
    private final String imageUrl;

    @ApiModelProperty(value = "즐겨찾기 추가 여부")
    private boolean isFavorites;

    @QueryProjection
    public RecommendedStoreDto(Long storeId, String name, String businessType, int isWheelchair, int isBabyCar,
                               int isDisabledToilet, int isChildOk, int isVoiceGuide, int isParkingLot, int isToilet, String imageUrl) {
        this.storeId = storeId;
        this.name = name;
        this.businessType = businessType;
        this.isWheelchair = isWheelchair;
        this.isBabyCar = isBabyCar;
        this.isDisabledToilet = isDisabledToilet;
        this.isChildOk = isChildOk;
        this.isVoiceGuide = isVoiceGuide;
        this.isParkingLot = isParkingLot;
        this.isToilet = isToilet;
        this.imageUrl = imageUrl;
    }

    public void checkIsMyFavorites(boolean isFavorites){
        this.isFavorites = isFavorites;
    }
}
