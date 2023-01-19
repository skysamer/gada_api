package com.smartmobility.gada_api.store.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.smartmobility.gada_api.member.domain.Member;
import com.smartmobility.gada_api.member.domain.QMember;
import com.smartmobility.gada_api.store.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.smartmobility.gada_api.store.domain.QStore.store;
import static com.smartmobility.gada_api.member.domain.QMember.member;
import static com.smartmobility.gada_api.store.domain.QStoreReview.storeReview;
import static com.smartmobility.gada_api.store.domain.QStoreReviewImage.storeReviewImage;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StoreReviewQueryRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public long getReviewCount(Long storeId){
        return jpaQueryFactory
                .select(storeReview.count())
                .from(storeReview)
                .join(store).on(storeReview.store.eq(store))
                .join(member).on(storeReview.member.eq(member))
                .where(store.id.eq(storeId))
                .fetchFirst();
    }

    public List<StoreReviewDto> getReviews(Long storeId, Pageable pageable){
        return jpaQueryFactory
                .select(new QStoreReviewDto(storeReview.id, storeReview.tag, storeReview.reviews,
                        member.nickname,storeReview.createdAt))
                .from(storeReview)
                .join(store).on(storeReview.store.eq(store))
                .join(member).on(storeReview.member.eq(member))
                .where(store.id.eq(storeId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(storeReview.id.desc())
                .fetch();
    }

    public List<StoreReviewImageDto> getReviewImages(Long storeId){
        return jpaQueryFactory
                .select(new QStoreReviewImageDto(storeReview.id.as("reviewId"), storeReviewImage.imageUrl))
                .from(storeReviewImage)
                .join(storeReview).on(storeReviewImage.storeReview.eq(storeReview))
                .join(store).on(storeReview.store.eq(store))
                .where(store.id.eq(storeId))
                .fetch();
    }

    public List<MyStoreReviewsDto> getMyReviews(Member member, Pageable pageable){
        return jpaQueryFactory
                .select(new QMyStoreReviewsDto(storeReview.id, QMember.member.nickname, storeReview.tag,
                        storeReview.reviews, storeReview.createdAt, store.id.as("storeId"), store.name.as("storeName")))
                .from(storeReview)
                .join(store).on(storeReview.store.eq(store))
                .join(QMember.member).on(storeReview.member.eq(QMember.member))
                .where(QMember.member.eq(member))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    public long getMyReviewCount(Member member){
        return jpaQueryFactory
                .select(new QMyStoreReviewsDto(storeReview.id, QMember.member.nickname, storeReview.tag,
                        storeReview.reviews, storeReview.createdAt, store.id.as("storeId"), store.name.as("storeName")))
                .from(storeReview)
                .join(store).on(storeReview.store.eq(store))
                .join(QMember.member).on(storeReview.member.eq(QMember.member))
                .where(QMember.member.eq(member))
                .stream().count();
    }

    public List<StoreReviewImageDto> getMyReviewImages(Member member){
        return jpaQueryFactory
                .select(new QStoreReviewImageDto(storeReview.id.as("reviewId"), storeReviewImage.imageUrl))
                .from(storeReviewImage)
                .join(storeReview).on(storeReviewImage.storeReview.eq(storeReview))
                .join(QMember.member).on(storeReview.member.eq(QMember.member))
                .where(QMember.member.eq(member))
                .fetch();
    }
}
