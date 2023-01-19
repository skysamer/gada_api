package com.smartmobility.gada_api.store.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStoreReviewImage is a Querydsl query type for StoreReviewImage
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStoreReviewImage extends EntityPathBase<StoreReviewImage> {

    private static final long serialVersionUID = 138085898L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStoreReviewImage storeReviewImage = new QStoreReviewImage("storeReviewImage");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imageUrl = createString("imageUrl");

    public final QStoreReview storeReview;

    public QStoreReviewImage(String variable) {
        this(StoreReviewImage.class, forVariable(variable), INITS);
    }

    public QStoreReviewImage(Path<? extends StoreReviewImage> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStoreReviewImage(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStoreReviewImage(PathMetadata metadata, PathInits inits) {
        this(StoreReviewImage.class, metadata, inits);
    }

    public QStoreReviewImage(Class<? extends StoreReviewImage> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.storeReview = inits.isInitialized("storeReview") ? new QStoreReview(forProperty("storeReview"), inits.get("storeReview")) : null;
    }

}

