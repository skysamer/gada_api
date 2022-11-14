package com.smartmobility.gada_api.store.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
@ApiModel(value = "가게 리뷰등록 폼")
public class StoreReviewForm {
    @ApiModelProperty(value = "태그 (TASTE, KINDNESS, TOILET, WHEELCHAIR, BABY_CAR, CHILDREN, DISABLED_TOILET)")
    private String tag;

    @ApiModelProperty(value = "후기")
    private String reviews;

    @ApiModelProperty(value = "가게고유번호")
    private Long storeId;
}
