package com.smartmobility.gada_api.store.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStoreReview is a Querydsl query type for StoreReview
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStoreReview extends EntityPathBase<StoreReview> {

    private static final long serialVersionUID = -1507480111L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStoreReview storeReview = new QStoreReview("storeReview");

    public final com.smartmobility.gada_api.global.dto.QBaseTimeEntity _super = new com.smartmobility.gada_api.global.dto.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<StoreReviewImage, QStoreReviewImage> images = this.<StoreReviewImage, QStoreReviewImage>createList("images", StoreReviewImage.class, QStoreReviewImage.class, PathInits.DIRECT2);

    public final com.smartmobility.gada_api.member.domain.QMember member;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final StringPath reviews = createString("reviews");

    public final QStore store;

    public final StringPath tag = createString("tag");

    public QStoreReview(String variable) {
        this(StoreReview.class, forVariable(variable), INITS);
    }

    public QStoreReview(Path<? extends StoreReview> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStoreReview(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStoreReview(PathMetadata metadata, PathInits inits) {
        this(StoreReview.class, metadata, inits);
    }

    public QStoreReview(Class<? extends StoreReview> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new com.smartmobility.gada_api.member.domain.QMember(forProperty("member"), inits.get("member")) : null;
        this.store = inits.isInitialized("store") ? new QStore(forProperty("store")) : null;
    }

}

