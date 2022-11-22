package com.smartmobility.gada_api.scheduler;

import com.smartmobility.gada_api.store.dto.RecommendedStoreDto;
import com.smartmobility.gada_api.store.repository.StoreQueryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class WeeklyStoreTest {
    @Autowired
    StoreQueryRepository storeQueryRepository;

    @Test
    void get_제보된_가게_전체조회(){
        List<RecommendedStoreDto> recommendedStores = storeQueryRepository.getReportedStore();
        System.out.println(recommendedStores.size());
        for(int i=0; i< recommendedStores.size(); i++){
            System.out.println("recommendedStores = " + recommendedStores.get(i).getStoreId());
        }
    }

}
