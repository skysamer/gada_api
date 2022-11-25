package com.smartmobility.gada_api.store.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QStore is a Querydsl query type for Store
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStore extends EntityPathBase<Store> {

    private static final long serialVersionUID = 382386457L;

    public static final QStore store = new QStore("store");

    public final StringPath businessType = createString("businessType");

    public final StringPath controlNumber = createString("controlNumber");

    public final StringPath hours = createString("hours");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath lat = createString("lat");

    public final StringPath localCode = createString("localCode");

    public final StringPath lon = createString("lon");

    public final StringPath name = createString("name");

    public final StringPath numberAddress = createString("numberAddress");

    public final StringPath phone = createString("phone");

    public final StringPath streetAddress = createString("streetAddress");

    public QStore(String variable) {
        super(Store.class, forVariable(variable));
    }

    public QStore(Path<? extends Store> path) {
        super(path.getType(), path.getMetadata());
    }

    public QStore(PathMetadata metadata) {
        super(Store.class, metadata);
    }

}

