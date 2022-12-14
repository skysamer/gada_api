package com.smartmobility.gada_api.store.repository;

import com.smartmobility.gada_api.member.domain.Member;
import com.smartmobility.gada_api.store.domain.Store;
import com.smartmobility.gada_api.store.domain.StoreDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface StoreDetailsRepository extends JpaRepository<StoreDetails, Long> {
    boolean existsByStoreAndMemberAndIsCertificated(Store store, Member member, int isCertificated);
}
