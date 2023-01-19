package com.smartmobility.gada_api.store.repository;

import com.smartmobility.gada_api.store.domain.StoreReview;
import com.smartmobility.gada_api.store.domain.StoreReviewImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface StoreReviewImageRepository extends JpaRepository<StoreReviewImage, Long> {
    List<StoreReviewImage> findAllByStoreReview(StoreReview review);
}
