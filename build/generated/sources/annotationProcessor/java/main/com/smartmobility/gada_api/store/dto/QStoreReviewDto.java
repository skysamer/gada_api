package com.smartmobility.gada_api.store.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.Generated;

/**
 * com.smartmobility.gada_api.store.dto.QStoreReviewDto is a Querydsl Projection type for StoreReviewDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QStoreReviewDto extends ConstructorExpression<StoreReviewDto> {

    private static final long serialVersionUID = -2017468393L;

    public QStoreReviewDto(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<String> tag, com.querydsl.core.types.Expression<String> reviews, com.querydsl.core.types.Expression<String> nickname, com.querydsl.core.types.Expression<java.time.LocalDateTime> createdAt) {
        super(StoreReviewDto.class, new Class<?>[]{long.class, String.class, String.class, String.class, java.time.LocalDateTime.class}, id, tag, reviews, nickname, createdAt);
    }

}

