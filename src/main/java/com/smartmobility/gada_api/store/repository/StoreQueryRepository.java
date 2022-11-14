package com.smartmobility.gada_api.store.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.smartmobility.gada_api.store.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.smartmobility.gada_api.store.domain.QStore.store;
import static com.smartmobility.gada_api.store.domain.QStoreDetails.storeDetails;
import static com.smartmobility.gada_api.store.domain.QStoreReview.storeReview;
import static com.smartmobility.gada_api.member.domain.QMember.member;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StoreQueryRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public List<StoresDto> getStores(String region){
        return jpaQueryFactory
                .select(new QStoresDto(store.id, store.localCode, store.controlNumber, store.name,
                        store.numberAddress, store.streetAddress, store.phone, store.businessType,
                        store.lat, store.lon))
                .from(store)
                .leftJoin(storeDetails).on(storeDetails.store.eq(store))
                .where(store.numberAddress.contains(region)
                        .and(storeDetails.id.isNotNull())
                )
                .groupBy(store.id)
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
                        storeDetails.isVoiceGuide.sum().as("isVoiceGuide"), storeDetails.isEscalator.sum().as("isEscalator"),
                        storeDetails.isParkingLot.sum().as("isParkingLot"), storeDetails.isWifi.sum().as("isWifi"),
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
}
