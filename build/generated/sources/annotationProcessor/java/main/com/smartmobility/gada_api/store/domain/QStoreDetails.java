package com.smartmobility.gada_api.store.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStoreDetails is a Querydsl query type for StoreDetails
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStoreDetails extends EntityPathBase<StoreDetails> {

    private static final long serialVersionUID = 970525417L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStoreDetails storeDetails = new QStoreDetails("storeDetails");

    public final com.smartmobility.gada_api.global.dto.QBaseTimeEntity _super = new com.smartmobility.gada_api.global.dto.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath etc = createString("etc");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> isBabyCar = createNumber("isBabyCar", Integer.class);

    public final NumberPath<Integer> isChildOk = createNumber("isChildOk", Integer.class);

    public final NumberPath<Integer> isDisabledToilet = createNumber("isDisabledToilet", Integer.class);

    public final NumberPath<Integer> isEscalator = createNumber("isEscalator", Integer.class);

    public final NumberPath<Integer> isParkingLot = createNumber("isParkingLot", Integer.class);

    public final NumberPath<Integer> isToilet = createNumber("isToilet", Integer.class);

    public final NumberPath<Integer> isVoiceGuide = createNumber("isVoiceGuide", Integer.class);

    public final NumberPath<Integer> isWheelchair = createNumber("isWheelchair", Integer.class);

    public final NumberPath<Integer> isWifi = createNumber("isWifi", Integer.class);

    public final com.smartmobility.gada_api.member.domain.QMember member;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final StringPath recommendedPerson = createString("recommendedPerson");

    public final QStore store;

    public QStoreDetails(String variable) {
        this(StoreDetails.class, forVariable(variable), INITS);
    }

    public QStoreDetails(Path<? extends StoreDetails> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStoreDetails(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStoreDetails(PathMetadata metadata, PathInits inits) {
        this(StoreDetails.class, metadata, inits);
    }

    public QStoreDetails(Class<? extends StoreDetails> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new com.smartmobility.gada_api.member.domain.QMember(forProperty("member"), inits.get("member")) : null;
        this.store = inits.isInitialized("store") ? new QStore(forProperty("store")) : null;
    }

}

