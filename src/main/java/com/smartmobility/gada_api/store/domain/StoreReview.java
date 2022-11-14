package com.smartmobility.gada_api.store.domain;

import com.smartmobility.gada_api.global.dto.BaseTimeEntity;
import com.smartmobility.gada_api.member.domain.Member;
import com.smartmobility.gada_api.store.dto.StoreReviewForm;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity @Table(name = "store_review")
@Getter @ApiModel(value = "가게 후기 엔티티")
@NoArgsConstructor
public class StoreReview extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "시퀀스")
    private Long id;

    @ApiModelProperty(value = "태그 (TASTE, KINDNESS, TOILET, WHEELCHAIR, BABY_CAR, CHILDREN, DISABLED_TOILET)")
    private String tag;

    @ApiModelProperty(value = "후기")
    private String reviews;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "storeReview")
    private List<StoreReviewImage> images = new ArrayList<>();

    public StoreReview(StoreReviewForm reviewForm, Store store, Member member){
        this.tag = reviewForm.getTag();
        this.reviews = reviewForm.getReviews();
        this.store = store;
        this.member = member;
    }
}
