package com.smartmobility.gada_api.store.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
@ApiModel(value = "가게 제보정보 입력 폼")
public class StoreDetailsForm {
    @ApiModelProperty(value = "가게고유번호")
    private Long storeId;

    @ApiModelProperty(value = "휠체어가 가능한지 (YES, NO, NONE)")
    private int isWheelchair;

    @ApiModelProperty(value = "유아차 동반가능여부 (YES, NO, NONE)")
    private int isBabyCar;

    @ApiModelProperty(value = "장애인 화장실이 있나요 (YES, NO, NONE)")
    private int isDisabledToilet;

    @ApiModelProperty(value = "어린이도 갈 수 있나요 (YES, NO, NONE)")
    private int isChildOk;

    @ApiModelProperty(value = "음성 안내, 점자 메뉴판이 있나요 (YES, NO, NONE)")
    private int isVoiceGuide;

    @ApiModelProperty(value = "에스컬레이터가 있나요 (YES, NO, NONE)")
    private int isEscalator;

    @ApiModelProperty(value = "주차 시설이 있나요 (YES, NO, NONE)")
    private int isParkingLot;

    @ApiModelProperty(value = "와이파이가 있나요 (YES, NO, NONE)")
    private int isWifi;

    @ApiModelProperty(value = "화장실이 있나요 (YES, NO, NONE)")
    private int isToilet;

    @ApiModelProperty(value = "방문을 환영하는 사람")
    private String recommendedPerson;

    @ApiModelProperty(value = "기타 건의사항")
    private String etc;
}
