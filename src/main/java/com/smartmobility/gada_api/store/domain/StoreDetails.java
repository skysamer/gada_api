package com.smartmobility.gada_api.store.domain;

import com.smartmobility.gada_api.global.dto.BaseTimeEntity;
import com.smartmobility.gada_api.member.domain.Member;
import com.smartmobility.gada_api.store.dto.StoreDetailsForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity @Getter @NoArgsConstructor
@Table(name = "store_details")
@ApiModel(value = "함께 가게 공유정보 엔티티")
@AllArgsConstructor @Builder
public class StoreDetails extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "시퀀스")
    private Long id;

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

    @ApiModelProperty(value = "방문을 환영하는 사람")
    @Column(name = "recommended_person")
    private String recommendedPerson;

    @ApiModelProperty(value = "기타 건의사항")
    private String etc;

    @ApiModelProperty(value = "봉사인증여부")
    @Column(name = "is_certificated")
    private int isCertificated;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public StoreDetails(StoreDetailsForm detailsForm, Store store, Member member) {
        this.isWheelchair = detailsForm.getIsWheelchair();
        this.isBabyCar = detailsForm.getIsBabyCar();
        this.isDisabledToilet = detailsForm.getIsDisabledToilet();
        this.isChildOk = detailsForm.getIsChildOk();
        this.isVoiceGuide = detailsForm.getIsVoiceGuide();
        this.isParkingLot = detailsForm.getIsParkingLot();
        this.isToilet = detailsForm.getIsToilet();
        this.recommendedPerson = detailsForm.getRecommendedPerson();
        this.etc = detailsForm.getEtc();
        this.store = store;
        this.member = member;
    }

    public void checkCertificate(int isCertificated){
        this.isCertificated = isCertificated;
    }
}
