package com.smartmobility.gada_api.store.service;

import com.smartmobility.gada_api.global.dto.HttpBodyMessage;
import com.smartmobility.gada_api.member.domain.Member;
import com.smartmobility.gada_api.store.domain.Store;
import com.smartmobility.gada_api.store.domain.StoreFavorites;
import com.smartmobility.gada_api.store.dto.MyFavoritesStoreDto;
import com.smartmobility.gada_api.store.repository.StoreFavoritesQueryRepository;
import com.smartmobility.gada_api.store.repository.StoreFavoritesRepository;
import com.smartmobility.gada_api.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class StoreFavoritesService {
    private final StoreFavoritesRepository favoritesRepository;
    private final StoreFavoritesQueryRepository favoritesQueryRepository;
    private final StoreRepository storeRepository;

    public HttpBodyMessage register(Long storeId, Member member){
        Store store = storeRepository.findById(storeId).orElse(null);
        boolean isExists = favoritesRepository.existsByStoreAndMember(store, member);

        if(isExists){
            favoritesRepository.deleteByStoreAndMember(store, member);
            return new HttpBodyMessage("success", "즐겨찾기 등록해제");
        }

        favoritesRepository.save(new StoreFavorites(store, member));
        return new HttpBodyMessage("success", "즐겨찾기 등록");
    }

    public List<MyFavoritesStoreDto> getMyFavorites(Member member){
        return favoritesQueryRepository.getMyFavoritesStores(member);
    }
}
