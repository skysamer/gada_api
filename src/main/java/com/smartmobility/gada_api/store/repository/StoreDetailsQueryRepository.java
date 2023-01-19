package com.smartmobility.gada_api.store.repository;

import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.smartmobility.gada_api.member.domain.Member;
import com.smartmobility.gada_api.store.dto.MyReportStoreDto;
import com.smartmobility.gada_api.store.dto.QMyReportStoreDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.smartmobility.gada_api.store.domain.QStore.store;
import static com.smartmobility.gada_api.store.domain.QStoreDetails.storeDetails;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StoreDetailsQueryRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public List<MyReportStoreDto> getMyStores(Member member, Pageable pageable){
        return jpaQueryFactory
                .select(new QMyReportStoreDto(store.id, store.name, store.numberAddress, store.streetAddress,
                        store.businessType, store.lat, store.lon, storeDetails.createdAt, storeDetails.isCertificated))
                .from(storeDetails)
                .join(store).on(store.eq(storeDetails.store))
                .where(storeDetails.member.eq(member)
                        .and(storeDetails.createdAt.in(
                                JPAExpressions
                                        .select(storeDetails.createdAt.max())
                                        .from(storeDetails)
                                        .where(storeDetails.member.eq(member))
                                        .groupBy(storeDetails.store)
                        ))
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .groupBy(store.id)
                .fetch();
    }

    public long getMyStoreCount(Member member){
        return jpaQueryFactory
                .select(storeDetails)
                .from(storeDetails)
                .join(store).on(store.eq(storeDetails.store))
                .where(storeDetails.member.eq(member)
                        .and(storeDetails.createdAt.in(
                                JPAExpressions
                                        .select(storeDetails.createdAt.max())
                                        .from(storeDetails)
                                        .where(storeDetails.member.eq(member))
                                        .groupBy(storeDetails.store)
                        ))
                )
                .groupBy(store.id)
                .stream().count();
    }
}
