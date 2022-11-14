package com.smartmobility.gada_api.store.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.smartmobility.gada_api.store.dto.QStoreReviewImageDto is a Querydsl Projection type for StoreReviewImageDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QStoreReviewImageDto extends ConstructorExpression<StoreReviewImageDto> {

    private static final long serialVersionUID = 1601989452L;

    public QStoreReviewImageDto(com.querydsl.core.types.Expression<Long> reviewId, com.querydsl.core.types.Expression<String> imageUrl) {
        super(StoreReviewImageDto.class, new Class<?>[]{long.class, String.class}, reviewId, imageUrl);
    }

}

