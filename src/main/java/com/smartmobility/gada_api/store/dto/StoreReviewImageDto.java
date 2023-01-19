package com.smartmobility.gada_api.store.dto;

import com.querydsl.core.annotations.QueryProjection;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
@ApiModel(value = "가게 리뷰 이미지 조회 객체")
public class StoreReviewImageDto {
    @ApiModelProperty(value = "리뷰 시퀀스")
    private final Long reviewId;

    @ApiModelProperty(value = "이미지 url")
    private final String imageUrl;

    @QueryProjection
    public StoreReviewImageDto(Long reviewId, String imageUrl) {
        this.reviewId = reviewId;
        this.imageUrl = imageUrl;
    }
}
