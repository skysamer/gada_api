package com.smartmobility.gada_api.global.util;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberTemplate;
import org.springframework.stereotype.Component;

import static com.smartmobility.gada_api.store.domain.QStore.store;

@Component
public class NativeQueryBuilder {
    /*match against 쿼리 구문을 반환하는 메서드*/
    public BooleanBuilder getMatchAgainstQuery(String region){
        BooleanBuilder builder = new BooleanBuilder();
        NumberTemplate<Double> booleanTemplateNumber = Expressions.numberTemplate(Double.class,
                "function('match',{0},{1})", store.numberAddress, region);
        NumberTemplate<Double> booleanTemplateStreet = Expressions.numberTemplate(Double.class,
                "function('match',{0},{1})", store.streetAddress, region);

        builder.and(booleanTemplateNumber.gt(0));
        builder.or(booleanTemplateStreet.gt(0));

        return builder;
    }
}
