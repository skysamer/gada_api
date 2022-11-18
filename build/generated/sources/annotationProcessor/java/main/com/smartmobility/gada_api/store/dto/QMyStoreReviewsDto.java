package com.smartmobility.gada_api.store.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.smartmobility.gada_api.store.dto.QMyStoreReviewsDto is a Querydsl Projection type for MyStoreReviewsDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QMyStoreReviewsDto extends ConstructorExpression<MyStoreReviewsDto> {

    private static final long serialVersionUID = -1692417528L;

    public QMyStoreReviewsDto(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<String> nickname, com.querydsl.core.types.Expression<String> tag, com.querydsl.core.types.Expression<String> reviews, com.querydsl.core.types.Expression<java.time.LocalDateTime> createdAt, com.querydsl.core.types.Expression<Long> storeId, com.querydsl.core.types.Expression<String> storeName) {
        super(MyStoreReviewsDto.class, new Class<?>[]{long.class, String.class, String.class, String.class, java.time.LocalDateTime.class, long.class, String.class}, id, nickname, tag, reviews, createdAt, storeId, storeName);
    }

}

