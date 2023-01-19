package com.smartmobility.gada_api.store.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import javax.persistence.*;

@Entity @Table(name = "store_details_image")
@Getter @ApiModel(value = "함께가게 제보정보 이미지 엔티티")
public class StoreDetailsImage {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "시퀀스")
    private Long id;

    @ApiModelProperty(value = "이미지 경로")
    @Column(name = "image_url")
    private final String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_details_id")
    private final StoreDetails storeDetails;

    public StoreDetailsImage(String imageUrl, StoreDetails details) {
        this.imageUrl = imageUrl;
        this.storeDetails = details;
    }
}
