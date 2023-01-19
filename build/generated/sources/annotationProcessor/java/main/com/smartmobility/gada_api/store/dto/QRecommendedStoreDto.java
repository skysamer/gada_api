package com.smartmobility.gada_api.store.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.Generated;

/**
 * com.smartmobility.gada_api.store.dto.QRecommendedStoreDto is a Querydsl Projection type for RecommendedStoreDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QRecommendedStoreDto extends ConstructorExpression<RecommendedStoreDto> {

    private static final long serialVersionUID = -1269386552L;

    public QRecommendedStoreDto(com.querydsl.core.types.Expression<Long> storeId, com.querydsl.core.types.Expression<String> name, com.querydsl.core.types.Expression<String> businessType, com.querydsl.core.types.Expression<Integer> isWheelchair, com.querydsl.core.types.Expression<Integer> isBabyCar, com.querydsl.core.types.Expression<Integer> isDisabledToilet, com.querydsl.core.types.Expression<Integer> isChildOk, com.querydsl.core.types.Expression<Integer> isVoiceGuide, com.querydsl.core.types.Expression<Integer> isParkingLot, com.querydsl.core.types.Expression<Integer> isToilet, com.querydsl.core.types.Expression<String> imageUrl) {
        super(RecommendedStoreDto.class, new Class<?>[]{long.class, String.class, String.class, int.class, int.class, int.class, int.class, int.class, int.class, int.class, String.class}, storeId, name, businessType, isWheelchair, isBabyCar, isDisabledToilet, isChildOk, isVoiceGuide, isParkingLot, isToilet, imageUrl);
    }

}

