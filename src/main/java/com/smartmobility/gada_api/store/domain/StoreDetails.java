package com.smartmobility.gada_api.store.domain;

import com.smartmobility.gada_api.global.dto.BaseTimeEntity;
import com.smartmobility.gada_api.member.domain.Member;
import com.smartmobility.gada_api.store.dto.StoreDetailsForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import javax.persistence.*;

@Entity @Getter
@Table(name = "store_details")
@ApiModel(value = "함께 가게 공유정보 엔티티")
public class StoreDetails extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "시퀀스")
    private Long id;

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

    @ApiModelProperty(value = "에스컬레이터가 있나요 (1, 0, -1)")
    @Column(name = "is_escalator")
    private final int isEscalator;

    @ApiModelProperty(value = "주차 시설이 있나요 (1, 0, -1)")
    @Column(name = "is_parking_lot")
    private final int isParkingLot;

    @ApiModelProperty(value = "와이파이가 있나요 (1, 0, -1)")
    @Column(name = "is_wifi")
    private final int isWifi;

    @ApiModelProperty(value = "화장실이 있나요 (1, 0, -1)")
    @Column(name = "is_toilet")
    private final int isToilet;

    @ApiModelProperty(value = "방문을 환영하는 사람")
    @Column(name = "recommended_person")
    private final String recommendedPerson;

    @ApiModelProperty(value = "기타 건의사항")
    private final String etc;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private final Store store;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private final Member member;

    public StoreDetails(StoreDetailsForm detailsForm, Store store, Member member) {
        this.isWheelchair = detailsForm.getIsWheelchair();
        this.isBabyCar = detailsForm.getIsBabyCar();
        this.isDisabledToilet = detailsForm.getIsDisabledToilet();
        this.isChildOk = detailsForm.getIsChildOk();
        this.isVoiceGuide = detailsForm.getIsVoiceGuide();
        this.isEscalator = detailsForm.getIsEscalator();
        this.isParkingLot = detailsForm.getIsParkingLot();
        this.isWifi = detailsForm.getIsWifi();
        this.isToilet = detailsForm.getIsToilet();
        this.recommendedPerson = detailsForm.getRecommendedPerson();
        this.etc = detailsForm.getEtc();
        this.store = store;
        this.member = member;
    }
}
