package com.smartmobility.gada_api.store.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import javax.persistence.*;

@Entity @Table(name = "recommended_store")
@ApiModel(value = "가다 추천 함께가게")
@Getter
public class RecommendedStore {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "시퀀스")
    private Long id;

    @Column(name = "store_id")
    @ApiModelProperty(value = "가게고유번호")
    private Long storeId;

    @ApiModelProperty(value = "가게이름")
    private String name;

    @ApiModelProperty(value = "업종명")
    @Column(name = "business_type")
    private String businessType;

    @ApiModelProperty(value = "휠체어가 가능한지 (1, 0, -1)")
    @Column(name = "is_wheelchair")
    private int isWheelchair;

    @ApiModelProperty(value = "유아차 동반가능여부 (1, 0, -1)")
    @Column(name = "is_baby_car")
    private int isBabyCar;

    @ApiModelProperty(value = "장애인 화장실이 있나요 (1, 0, -1)")
    @Column(name = "is_disabled_toilet")
    private int isDisabledToilet;

    @ApiModelProperty(value = "어린이도 갈 수 있나요 (1, 0, -1)")
    @Column(name = "is_child_ok")
    private int isChildOk;

    @ApiModelProperty(value = "음성 안내, 점자 메뉴판이 있나요 (1, 0, -1)")
    @Column(name = "is_voice_guide")
    private int isVoiceGuide;

    @ApiModelProperty(value = "주차 시설이 있나요 (1, 0, -1)")
    @Column(name = "is_parking_lot")
    private int isParkingLot;

    @ApiModelProperty(value = "화장실이 있나요 (1, 0, -1)")
    @Column(name = "is_toilet")
    private int isToilet;

    @ApiModelProperty(value = "이미지 url")
    @Column(name = "image_url")
    private String imageUrl;
}
