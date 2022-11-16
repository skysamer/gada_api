package com.smartmobility.gada_api.store.service;

import com.smartmobility.gada_api.member.domain.Member;
import com.smartmobility.gada_api.store.dto.StoresDto;
import com.smartmobility.gada_api.store.dto.TotalStoreInfoDto;
import com.smartmobility.gada_api.store.repository.*;
import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class StoreService {
    private final StoreQueryRepository storeQueryRepository;
    private final StoreFavoritesQueryRepository favoritesQueryRepository;
    private final Log log = LogFactory.getLog(getClass());

    public List<StoresDto> getStores(String region){
        return storeQueryRepository.getStores(region);
    }

    public List<StoresDto> searchStores(String name){
        return storeQueryRepository.searchStores(name);
    }

    public TotalStoreInfoDto getStore(Long id, Member member){
        TotalStoreInfoDto storeInfoDto = storeQueryRepository.getStoreInfo(id);

        long reviewCount = storeQueryRepository.getReviewCount(id);
        storeInfoDto.countReview(reviewCount);

        boolean isFavoritesExists = favoritesQueryRepository.isMyFavoritesExists(id, member);
        storeInfoDto.checkFavorites(isFavoritesExists);
        return storeInfoDto;
    }
}
