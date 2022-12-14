package com.smartmobility.gada_api.store.repository;

import com.smartmobility.gada_api.store.domain.StoreReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface StoreReviewRepository extends JpaRepository<StoreReview, Long> {
}
