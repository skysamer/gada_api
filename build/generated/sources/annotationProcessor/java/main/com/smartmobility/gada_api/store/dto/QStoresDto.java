package com.smartmobility.gada_api.store.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.Generated;

/**
 * com.smartmobility.gada_api.store.dto.QStoresDto is a Querydsl Projection type for StoresDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QStoresDto extends ConstructorExpression<StoresDto> {

    private static final long serialVersionUID = -1139013220L;

    public QStoresDto(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<String> localCode, com.querydsl.core.types.Expression<String> name, com.querydsl.core.types.Expression<String> numberAddress, com.querydsl.core.types.Expression<String> streetAddress, com.querydsl.core.types.Expression<String> phone, com.querydsl.core.types.Expression<String> businessType, com.querydsl.core.types.Expression<String> lat, com.querydsl.core.types.Expression<String> lon) {
        super(StoresDto.class, new Class<?>[]{long.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class}, id, localCode, name, numberAddress, streetAddress, phone, businessType, lat, lon);
    }

}

