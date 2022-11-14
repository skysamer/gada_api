package com.smartmobility.gada_api.store.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.smartmobility.gada_api.store.dto.QMyReportStoreDto is a Querydsl Projection type for MyReportStoreDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QMyReportStoreDto extends ConstructorExpression<MyReportStoreDto> {

    private static final long serialVersionUID = -283585617L;

    public QMyReportStoreDto(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<String> name, com.querydsl.core.types.Expression<String> numberAddress, com.querydsl.core.types.Expression<String> streetAddress, com.querydsl.core.types.Expression<String> businessType, com.querydsl.core.types.Expression<String> lat, com.querydsl.core.types.Expression<String> lon, com.querydsl.core.types.Expression<java.time.LocalDateTime> createdAt) {
        super(MyReportStoreDto.class, new Class<?>[]{long.class, String.class, String.class, String.class, String.class, String.class, String.class, java.time.LocalDateTime.class}, id, name, numberAddress, streetAddress, businessType, lat, lon, createdAt);
    }

}

