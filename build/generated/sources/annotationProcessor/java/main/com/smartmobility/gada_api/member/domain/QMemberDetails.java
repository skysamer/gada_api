package com.smartmobility.gada_api.member.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMemberDetails is a Querydsl query type for MemberDetails
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QMemberDetails extends BeanPath<MemberDetails> {

    private static final long serialVersionUID = -653737251L;

    public static final QMemberDetails memberDetails = new QMemberDetails("memberDetails");

    public final StringPath favoriteStation = createString("favoriteStation");

    public final EnumPath<PreferredMobil> preferredMobil = createEnum("preferredMobil", PreferredMobil.class);

    public final StringPath toolType = createString("toolType");

    public final StringPath underPrivileged = createString("underPrivileged");

    public QMemberDetails(String variable) {
        super(MemberDetails.class, forVariable(variable));
    }

    public QMemberDetails(Path<? extends MemberDetails> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMemberDetails(PathMetadata metadata) {
        super(MemberDetails.class, metadata);
    }

}

