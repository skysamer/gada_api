package com.smartmobility.gada_api.member.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QTerms is a Querydsl query type for Terms
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QTerms extends BeanPath<Terms> {

    private static final long serialVersionUID = -125127908L;

    public static final QTerms terms = new QTerms("terms");

    public final NumberPath<Integer> optionalTermsYn = createNumber("optionalTermsYn", Integer.class);

    public final NumberPath<Integer> requiredTermsYn = createNumber("requiredTermsYn", Integer.class);

    public QTerms(String variable) {
        super(Terms.class, forVariable(variable));
    }

    public QTerms(Path<? extends Terms> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTerms(PathMetadata metadata) {
        super(Terms.class, metadata);
    }

}

