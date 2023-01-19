package com.smartmobility.gada_api.store.task;

import com.smartmobility.gada_api.store.domain.RecommendedStore;
import com.smartmobility.gada_api.store.dto.RecommendedStoreDto;
import com.smartmobility.gada_api.store.repository.RecommendedStoreRepository;
import com.smartmobility.gada_api.store.repository.StoreQueryRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelmapper.ModelMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RecommendedStoreScheduler {
    private final StoreQueryRepository storeQueryRepository;
    private final RecommendedStoreRepository recommendedStoreRepository;
    private final ModelMapper modelMapper;
    private final Log log = LogFactory.getLog(getClass());

    /*가다추천 함께가게를 매월 1일, 15일에 업데이트하는 스케줄*/
    @Scheduled(cron = "0 0 0 1,15 * *")
    public void updateRecommendedStore(){
        List<RecommendedStoreDto> reportedStores = storeQueryRepository.getReportedStoreWithImage();
        if(reportedStores.size() == 0){
            log.info("이미지가 존재하는 제보가게가 없음");
            reportedStores = storeQueryRepository.getReportedStore();
        }

        if(reportedStores.size() < 3){
            log.info("제보된 가게가 3개미만");
            return;
        }

        recommendedStoreRepository.deleteAll();
        log.info("기존 가다추천 함께가게 삭제완료");

        for(int i=2; i>=0; i--){
            RecommendedStore recommendedStore = modelMapper.map(reportedStores.get(i), RecommendedStore.class);
            recommendedStoreRepository.save(recommendedStore);
            log.info("신규 가다추천 함께가게 등록 완료 : " + i);
        }
        log.info("가다추천 함께가게 신규 업데이트 완료");
    }

}
