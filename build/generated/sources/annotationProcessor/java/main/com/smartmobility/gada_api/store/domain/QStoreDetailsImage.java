package com.smartmobility.gada_api.store.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStoreDetailsImage is a Querydsl query type for StoreDetailsImage
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStoreDetailsImage extends EntityPathBase<StoreDetailsImage> {

    private static final long serialVersionUID = -1475471374L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStoreDetailsImage storeDetailsImage = new QStoreDetailsImage("storeDetailsImage");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imageUrl = createString("imageUrl");

    public final QStoreDetails storeDetails;

    public QStoreDetailsImage(String variable) {
        this(StoreDetailsImage.class, forVariable(variable), INITS);
    }

    public QStoreDetailsImage(Path<? extends StoreDetailsImage> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStoreDetailsImage(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStoreDetailsImage(PathMetadata metadata, PathInits inits) {
        this(StoreDetailsImage.class, metadata, inits);
    }

    public QStoreDetailsImage(Class<? extends StoreDetailsImage> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.storeDetails = inits.isInitialized("storeDetails") ? new QStoreDetails(forProperty("storeDetails"), inits.get("storeDetails")) : null;
    }

}

