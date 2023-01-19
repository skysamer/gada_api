package com.smartmobility.gada_api.scheduler;

import com.smartmobility.gada_api.store.domain.RecommendedStore;
import com.smartmobility.gada_api.store.dto.RecommendedStoreDto;
import com.smartmobility.gada_api.store.repository.RecommendedStoreRepository;
import com.smartmobility.gada_api.store.repository.StoreQueryRepository;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class WeeklyStoreTest {
    @Autowired
    StoreQueryRepository storeQueryRepository;

    @Autowired
    RecommendedStoreRepository recommendedStoreRepository;

    @Autowired
    ModelMapper modelMapper;

    @Test
    void get_제보된_가게_전체조회(){
        List<RecommendedStoreDto> reportedStores = storeQueryRepository.getReportedStoreWithImage();
        if(reportedStores.size() == 0){
            reportedStores = storeQueryRepository.getReportedStore();
        }

        if(reportedStores.size() < 3){
            return;
        }

        recommendedStoreRepository.deleteAll();
        for(int i=2; i>=0; i--){
            RecommendedStore recommendedStore = modelMapper.map(reportedStores.get(i), RecommendedStore.class);
            recommendedStoreRepository.save(recommendedStore);
        }
    }

    @Test
    void get_이미지가_존재하는_제보가게가_없을경우(){
        List<RecommendedStoreDto> reportedStores = storeQueryRepository.getReportedStore();
        for(RecommendedStoreDto store : reportedStores){
            System.out.println("store = " + store.getImageUrl());
        }
    }
}
