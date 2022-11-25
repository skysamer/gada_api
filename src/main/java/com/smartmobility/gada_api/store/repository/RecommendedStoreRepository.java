package com.smartmobility.gada_api.store.repository;

import com.smartmobility.gada_api.store.domain.RecommendedStore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecommendedStoreRepository extends JpaRepository<RecommendedStore, Long> {
}
