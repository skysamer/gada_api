package com.smartmobility.gada_api.store.repository;

import com.smartmobility.gada_api.member.domain.Member;
import com.smartmobility.gada_api.store.domain.Store;
import com.smartmobility.gada_api.store.domain.StoreFavorites;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface StoreFavoritesRepository extends JpaRepository<StoreFavorites, Long> {
    boolean existsByStoreAndMember(Store store, Member member);
    void deleteByStoreAndMember(Store store, Member member);
}
