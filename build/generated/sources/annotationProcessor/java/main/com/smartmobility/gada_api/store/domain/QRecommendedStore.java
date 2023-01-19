package com.smartmobility.gada_api.store.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QRecommendedStore is a Querydsl query type for RecommendedStore
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRecommendedStore extends EntityPathBase<RecommendedStore> {

    private static final long serialVersionUID = -1457106930L;

    public static final QRecommendedStore recommendedStore = new QRecommendedStore("recommendedStore");

    public final StringPath businessType = createString("businessType");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imageUrl = createString("imageUrl");

    public final NumberPath<Integer> isBabyCar = createNumber("isBabyCar", Integer.class);

    public final NumberPath<Integer> isChildOk = createNumber("isChildOk", Integer.class);

    public final NumberPath<Integer> isDisabledToilet = createNumber("isDisabledToilet", Integer.class);

    public final NumberPath<Integer> isParkingLot = createNumber("isParkingLot", Integer.class);

    public final NumberPath<Integer> isToilet = createNumber("isToilet", Integer.class);

    public final NumberPath<Integer> isVoiceGuide = createNumber("isVoiceGuide", Integer.class);

    public final NumberPath<Integer> isWheelchair = createNumber("isWheelchair", Integer.class);

    public final StringPath name = createString("name");

    public final NumberPath<Long> storeId = createNumber("storeId", Long.class);

    public QRecommendedStore(String variable) {
        super(RecommendedStore.class, forVariable(variable));
    }

    public QRecommendedStore(Path<? extends RecommendedStore> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRecommendedStore(PathMetadata metadata) {
        super(RecommendedStore.class, metadata);
    }

}

