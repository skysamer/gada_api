package com.smartmobility.gada_api.store.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity @Table(name = "store_review_image")
@Getter @ApiModel(value = "함께가게 후기 이미지 엔티티")
@NoArgsConstructor
public class StoreReviewImage {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "시퀀스")
    private Long id;

    @ApiModelProperty(value = "이미지 url")
    @Column(name = "image_url")
    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_review_id")
    private StoreReview storeReview;

    public StoreReviewImage(String imageUrl, StoreReview storeReview) {
        this.imageUrl = imageUrl;
        this.storeReview = storeReview;
    }
}
