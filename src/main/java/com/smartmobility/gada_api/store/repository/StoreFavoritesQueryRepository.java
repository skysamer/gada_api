package com.smartmobility.gada_api.store.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.smartmobility.gada_api.member.domain.Member;
import com.smartmobility.gada_api.store.dto.MyFavoritesStoreDto;
import com.smartmobility.gada_api.store.dto.QMyFavoritesStoreDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.smartmobility.gada_api.store.domain.QStore.store;
import static com.smartmobility.gada_api.store.domain.QStoreDetails.storeDetails;
import static com.smartmobility.gada_api.store.domain.QStoreFavorites.storeFavorites;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StoreFavoritesQueryRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public List<MyFavoritesStoreDto> getMyFavoritesStores(Member member){
        return jpaQueryFactory
                .select(new QMyFavoritesStoreDto(store.id.as("storeId"), store.name, store.numberAddress,
                        store.streetAddress, store.businessType, store.lat, store.lon,
                        storeDetails.isWheelchair.sum().as("isWheelchair"), storeDetails.isBabyCar.sum().as("isBabyCar"),
                        storeDetails.isDisabledToilet.sum().as("isDisabledToilet"), storeDetails.isChildOk.sum().as("isChildOk"),
                        storeDetails.isVoiceGuide.sum().as("isVoiceGuide"), storeDetails.isParkingLot.sum().as("isParkingLot"),
                        storeDetails.isToilet.sum().as("isToilet")))
                .from(store)
                .join(storeFavorites).on(storeFavorites.store.eq(store))
                .leftJoin(storeDetails).on(storeDetails.store.eq(store))
                .where(storeFavorites.member.eq(member))
                .groupBy(store.id)
                .orderBy(storeFavorites.id.desc())
                .fetch();
    }

    public boolean isMyFavoritesExists(Long storeId, Member member){
        Integer result = jpaQueryFactory
                .selectOne()
                .from(storeFavorites)
                .where(storeFavorites.store.id.eq(storeId)
                        .and(storeFavorites.member.eq(member))
                )
                .fetchFirst();
        return result != null;
    }
}
