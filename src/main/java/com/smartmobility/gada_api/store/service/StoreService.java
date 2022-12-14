package com.smartmobility.gada_api.store.service;

import com.smartmobility.gada_api.member.domain.Member;
import com.smartmobility.gada_api.store.dto.RecommendedStoreDto;
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
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StoreService {
    private final StoreQueryRepository storeQueryRepository;
    private final StoreFavoritesQueryRepository favoritesQueryRepository;

    private final Log log = LogFactory.getLog(getClass());

    public List<StoresDto> getStores(String region, List<String> keywords){
        return storeQueryRepository.getStores(region, keywords);
    }

    public List<StoresDto> searchStores(String name){
        return storeQueryRepository.searchStores(name);
    }

    public TotalStoreInfoDto getStore(Long id, Member member){
        TotalStoreInfoDto storeInfoDto = storeQueryRepository.getStoreInfo(id);
        setStoreReviewCountInfo(id, storeInfoDto);
        setStoreIsMyFavoritesInfo(id, storeInfoDto, member);
        return storeInfoDto;
    }

    private void setStoreReviewCountInfo(Long id, TotalStoreInfoDto storeInfoDto){
        long reviewCount = storeQueryRepository.getReviewCount(id);
        storeInfoDto.countReview(reviewCount);
    }

    private void setStoreIsMyFavoritesInfo(Long id, TotalStoreInfoDto storeInfoDto, Member member){
        boolean isFavoritesExists = favoritesQueryRepository.isMyFavoritesExists(id, member);
        storeInfoDto.checkFavorites(isFavoritesExists);
    }

    public List<RecommendedStoreDto> getRecommendedStores(Member member){
        List<RecommendedStoreDto> recommendedStores = storeQueryRepository.getRecommendedStore();

        for(RecommendedStoreDto recommendedStore : recommendedStores){
            boolean isFavoritesExists = favoritesQueryRepository.isMyFavoritesExists(recommendedStore.getStoreId(), member);
            recommendedStore.checkIsMyFavorites(isFavoritesExists);
        }
        return recommendedStores;
    }
}
