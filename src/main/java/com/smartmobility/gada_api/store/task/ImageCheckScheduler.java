package com.smartmobility.gada_api.store.task;

import com.smartmobility.gada_api.store.domain.StoreDetailsImage;
import com.smartmobility.gada_api.store.domain.StoreReviewImage;
import com.smartmobility.gada_api.store.repository.StoreDetailsImageRepository;
import com.smartmobility.gada_api.store.repository.StoreReviewImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ImageCheckScheduler {
    private final StoreDetailsImageRepository detailsImageRepository;
    private final StoreReviewImageRepository reviewImageRepository;

    @Scheduled(cron = "0 0 2 * * *")
    public void checkImage(){
        List<StoreDetailsImage> detailsImages = detailsImageRepository.findAll();


        List<StoreReviewImage> reviewImages = reviewImageRepository.findAll();
    }
}
