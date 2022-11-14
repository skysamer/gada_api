package com.smartmobility.gada_api.store.dto;

import com.querydsl.core.annotations.QueryProjection;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@ApiModel(value = "내가 작성한 가게 리뷰 조회 폼")
public class MyStoreReviewsDto {
    @ApiModelProperty(value = "리뷰 시퀀스")
    private final Long id;

    @ApiModelProperty(value = "닉네임")
    private final String nickname;

    @ApiModelProperty(value = "태그 (TASTE, KINDNESS, TOILET, WHEELCHAIR, BABY_CAR, CHILDREN, DISABLED_TOILET)")
    private final String tag;

    @ApiModelProperty(value = "후기")
    private final String reviews;

    @ApiModelProperty(value = "생성일")
    private final LocalDateTime createdAt;

    @ApiModelProperty(value = "가게 시퀀스")
    private final Long storeId;

    @ApiModelProperty(value = "리뷰 이미지 리스트")
    private final List<StoreReviewImageDto> imageUrls = new ArrayList<>();

    @QueryProjection
    public MyStoreReviewsDto(Long id, String nickname, String tag,
                             String reviews, LocalDateTime createdAt, Long storeId) {
        this.id = id;
        this.nickname = nickname;
        this.tag = tag;
        this.reviews = reviews;
        this.createdAt = createdAt;
        this.storeId = storeId;
    }
}
