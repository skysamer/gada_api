package com.smartmobility.gada_api.store.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.smartmobility.gada_api.store.dto.QTotalStoreInfoDto is a Querydsl Projection type for TotalStoreInfoDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QTotalStoreInfoDto extends ConstructorExpression<TotalStoreInfoDto> {

    private static final long serialVersionUID = -1866886813L;

    public QTotalStoreInfoDto(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<String> name, com.querydsl.core.types.Expression<String> numberAddress, com.querydsl.core.types.Expression<String> streetAddress, com.querydsl.core.types.Expression<String> phone, com.querydsl.core.types.Expression<String> businessType, com.querydsl.core.types.Expression<String> lat, com.querydsl.core.types.Expression<String> lon, com.querydsl.core.types.Expression<Integer> isWheelchair, com.querydsl.core.types.Expression<Integer> isBabyCar, com.querydsl.core.types.Expression<Integer> isDisabledToilet, com.querydsl.core.types.Expression<Integer> isChildOk, com.querydsl.core.types.Expression<Integer> isVoiceGuide, com.querydsl.core.types.Expression<Integer> isParkingLot, com.querydsl.core.types.Expression<Integer> isToilet) {
        super(TotalStoreInfoDto.class, new Class<?>[]{long.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, int.class, int.class, int.class, int.class, int.class, int.class, int.class}, id, name, numberAddress, streetAddress, phone, businessType, lat, lon, isWheelchair, isBabyCar, isDisabledToilet, isChildOk, isVoiceGuide, isParkingLot, isToilet);
    }

}

