package com.smartmobility.gada_api.store.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberTemplate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.smartmobility.gada_api.store.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.smartmobility.gada_api.store.domain.QRecommendedStore.recommendedStore;
import static com.smartmobility.gada_api.store.domain.QStore.store;
import static com.smartmobility.gada_api.store.domain.QStoreDetails.storeDetails;
import static com.smartmobility.gada_api.store.domain.QStoreDetailsImage.storeDetailsImage;
import static com.smartmobility.gada_api.store.domain.QStoreReview.storeReview;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StoreQueryRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public List<StoresDto> getStores(String region, List<String> keywords){
        BooleanBuilder builder = new BooleanBuilder();
        NumberTemplate booleanTemplateNumber = Expressions.numberTemplate(Double.class,
                "function('match',{0},{1})", store.numberAddress, region);
        NumberTemplate booleanTemplateStreet = Expressions.numberTemplate(Double.class,
                "function('match',{0},{1})", store.streetAddress, region);
        builder.and(booleanTemplateNumber.gt(0));
        builder.or(booleanTemplateStreet.gt(0));

        return jpaQueryFactory
                .select(new QStoresDto(store.id, store.localCode, store.controlNumber, store.name,
                        store.numberAddress, store.streetAddress, store.phone, store.businessType,
                        store.lat, store.lon))
                .from(storeDetails)
                .join(store).on(storeDetails.store.eq(store))
                .where(builder)
                .groupBy(store.id)
                .having(Expressions.asBoolean(true).isTrue()
                        .and(checkWheelchair(keywords))
                        .and(checkBabyCar(keywords))
                        .and(checkChildOk(keywords))
                        .and(checkToilet(keywords))
                        .and(checkParkingLot(keywords))
                        .and(checkVoiceGuide(keywords))
                        .and(checkDisabledToilet(keywords))
                )
                .fetch();
    }

    public List<StoresDto> searchStores(String name){
        return jpaQueryFactory
                .select(new QStoresDto(store.id, store.localCode, store.controlNumber, store.name,
                        store.numberAddress, store.streetAddress, store.phone, store.businessType,
                        store.lat, store.lon))
                .from(store)
                .where(store.name.contains(name))
                .fetch();
    }

    public TotalStoreInfoDto getStoreInfo(Long storeId){
        return jpaQueryFactory
                .select(new QTotalStoreInfoDto(store.id, store.controlNumber, store.name, store.numberAddress,
                        store.streetAddress, store.phone, store.businessType, store.lat, store.lon,
                        storeDetails.isWheelchair.sum().as("isWheelchair"), storeDetails.isBabyCar.sum().as("isBabyCar"),
                        storeDetails.isDisabledToilet.sum().as("isDisabledToilet"), storeDetails.isChildOk.sum().as("isChildOk"),
                        storeDetails.isVoiceGuide.sum().as("isVoiceGuide"), storeDetails.isParkingLot.sum().as("isParkingLot"),
                        storeDetails.isToilet.sum().as("isToilet")))
                .from(store)
                .leftJoin(storeDetails).on(store.eq(storeDetails.store))
                .where(store.id.eq(storeId))
                .fetchOne();
    }

    public long getReviewCount(Long storeId){
        return jpaQueryFactory
                .select(storeReview.count())
                .from(storeReview)
                .where(storeReview.store.id.eq(storeId))
                .fetchFirst();
    }

    public List<RecommendedStoreDto> getReportedStoreWithImage(){
        return jpaQueryFactory
                .select(new QRecommendedStoreDto(store.id.as("storeId"), store.name, store.businessType,
                        storeDetails.isWheelchair.sum().as("isWheelchair"), storeDetails.isBabyCar.sum().as("isBabyCar"),
                        storeDetails.isDisabledToilet.sum().as("isDisabledToilet"), storeDetails.isChildOk.sum().as("isChildOk"),
                        storeDetails.isVoiceGuide.sum().as("isVoiceGuide"), storeDetails.isParkingLot.sum().as("isParkingLot"),
                        storeDetails.isToilet.sum().as("isToilet"), storeDetailsImage.imageUrl))
                .from(store)
                .join(storeDetails).on(storeDetails.store.eq(store))
                .leftJoin(storeDetailsImage).on(storeDetailsImage.storeDetails.eq(storeDetails))
                .groupBy(store)
                .having(storeDetailsImage.imageUrl.isNotNull())
                .orderBy(storeDetails.createdAt.desc())
                .fetch();
    }

    public List<RecommendedStoreDto> getReportedStore(){
        return jpaQueryFactory
                .select(new QRecommendedStoreDto(store.id.as("storeId"), store.name, store.businessType,
                        storeDetails.isWheelchair.sum().as("isWheelchair"), storeDetails.isBabyCar.sum().as("isBabyCar"),
                        storeDetails.isDisabledToilet.sum().as("isDisabledToilet"), storeDetails.isChildOk.sum().as("isChildOk"),
                        storeDetails.isVoiceGuide.sum().as("isVoiceGuide"), storeDetails.isParkingLot.sum().as("isParkingLot"),
                        storeDetails.isToilet.sum().as("isToilet"), storeDetailsImage.imageUrl))
                .from(store)
                .join(storeDetails).on(storeDetails.store.eq(store))
                .leftJoin(storeDetailsImage).on(storeDetailsImage.storeDetails.eq(storeDetails))
                .groupBy(store)
                .orderBy(storeDetails.createdAt.desc())
                .fetch();
    }

//    public List<Long> getStoresWithImage(){
//        return jpaQueryFactory
//                .select(store.id)
//                .from(store)
//                .join(storeDetails).on(storeDetails.store.eq(store))
//                .leftJoin(storeDetailsImage).on(storeDetailsImage.storeDetails.eq(storeDetails))
//                .groupBy(store)
//                .having(storeDetailsImage.imageUrl.count().goe(1))
//                .fetch();
//    }

    public List<RecommendedStoreDto> getRecommendedStore(){
        return jpaQueryFactory
                .select(new QRecommendedStoreDto(recommendedStore.storeId, recommendedStore.name, recommendedStore.businessType,
                        recommendedStore.isWheelchair, recommendedStore.isBabyCar, recommendedStore.isDisabledToilet,
                        recommendedStore.isChildOk, recommendedStore.isVoiceGuide, recommendedStore.isParkingLot,
                        recommendedStore.isToilet, recommendedStore.imageUrl))
                .from(recommendedStore)
                .fetch();
    }

    private BooleanExpression checkWheelchair(List<String> keywords){
        return keywords.contains("wheelchair") ? storeDetails.isWheelchair.sum().goe(1) : null;
    }

    private BooleanExpression checkBabyCar(List<String> keywords){
        return keywords.contains("babyCar") ? storeDetails.isBabyCar.sum().goe(1) : null;
    }

    private BooleanExpression checkDisabledToilet(List<String> keywords){
        return keywords.contains("disabledToilet") ? storeDetails.isDisabledToilet.sum().goe(1) : null;
    }

    private BooleanExpression checkToilet(List<String> keywords){
        return keywords.contains("toilet") ? storeDetails.isToilet.sum().goe(1) : null;
    }

    private BooleanExpression checkChildOk(List<String> keywords){
        return keywords.contains("childOk") ? storeDetails.isChildOk.sum().goe(1) : null;
    }

    private BooleanExpression checkParkingLot(List<String> keywords){
        return keywords.contains("parkingLot") ? storeDetails.isParkingLot.sum().goe(1) : null;
    }

    private BooleanExpression checkVoiceGuide(List<String> keywords){
        return keywords.contains("voiceGuide") ? storeDetails.isVoiceGuide.sum().goe(1) : null;
    }
}
