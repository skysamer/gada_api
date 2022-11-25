package com.smartmobility.gada_api.store.repository;

import com.smartmobility.gada_api.store.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface StoreRepository extends JpaRepository<Store, Long> {
    Store findByName(String name);
}
