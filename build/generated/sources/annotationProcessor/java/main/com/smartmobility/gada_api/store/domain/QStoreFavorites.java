package com.smartmobility.gada_api.store.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStoreFavorites is a Querydsl query type for StoreFavorites
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStoreFavorites extends EntityPathBase<StoreFavorites> {

    private static final long serialVersionUID = 854848830L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStoreFavorites storeFavorites = new QStoreFavorites("storeFavorites");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.smartmobility.gada_api.member.domain.QMember member;

    public final QStore store;

    public QStoreFavorites(String variable) {
        this(StoreFavorites.class, forVariable(variable), INITS);
    }

    public QStoreFavorites(Path<? extends StoreFavorites> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStoreFavorites(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStoreFavorites(PathMetadata metadata, PathInits inits) {
        this(StoreFavorites.class, metadata, inits);
    }

    public QStoreFavorites(Class<? extends StoreFavorites> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new com.smartmobility.gada_api.member.domain.QMember(forProperty("member"), inits.get("member")) : null;
        this.store = inits.isInitialized("store") ? new QStore(forProperty("store")) : null;
    }

}

