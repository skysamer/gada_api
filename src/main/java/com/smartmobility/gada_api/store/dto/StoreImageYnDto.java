package com.smartmobility.gada_api.store.dto;

import io.swagger.annotations.ApiModel;
import lombok.Getter;

@Getter
@ApiModel(value = "가게 제보별 이미지 업로드 여부 검증 dto")
public class StoreImageYnDto {
    private Long storeId;
}
