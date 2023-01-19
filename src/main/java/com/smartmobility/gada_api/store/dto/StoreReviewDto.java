package com.smartmobility.gada_api.store.dto;

import com.querydsl.core.annotations.QueryProjection;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter @ApiModel(value = "가게 별 리뷰 조회 객체")
public class StoreReviewDto {
    @ApiModelProperty(value = "리뷰의 순번")
    private Long id;
    @ApiModelProperty(value = "태그 (TASTE, KINDNESS, TOILET, WHEELCHAIR, BABY_CAR, CHILDREN, DISABLED_TOILET)")
    private String tag;
    @ApiModelProperty(value = "후기")
    private String reviews;
    @ApiModelProperty(value = "닉네임")
    private String nickname;
    @ApiModelProperty(value = "생성일")
    private LocalDateTime createdAt;

    @ApiModelProperty(value = "리뷰 별 이미지")
    private final List<StoreReviewImageDto> imageUrls = new ArrayList<>();

    @QueryProjection
    public StoreReviewDto(Long id, String tag, String reviews, String nickname, LocalDateTime createdAt) {
        this.id = id;
        this.tag = tag;
        this.reviews = reviews;
        this.nickname = nickname;
        this.createdAt = createdAt;
    }

    public StoreReviewDto(){

    }
}
