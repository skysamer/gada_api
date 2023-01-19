package com.smartmobility.gada_api.store.service;

import com.smartmobility.gada_api.global.dto.HttpBodyMessage;
import com.smartmobility.gada_api.global.dto.PageResult;
import com.smartmobility.gada_api.member.domain.Member;
import com.smartmobility.gada_api.store.domain.Store;
import com.smartmobility.gada_api.store.domain.StoreDetails;
import com.smartmobility.gada_api.store.domain.StoreDetailsImage;
import com.smartmobility.gada_api.store.dto.MyReportStoreDto;
import com.smartmobility.gada_api.store.dto.StoreDetailsForm;
import com.smartmobility.gada_api.store.repository.StoreDetailsImageRepository;
import com.smartmobility.gada_api.store.repository.StoreDetailsQueryRepository;
import com.smartmobility.gada_api.store.repository.StoreDetailsRepository;
import com.smartmobility.gada_api.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class StoreDetailsService {
    private final StoreRepository storeRepository;
    private final StoreDetailsRepository detailsRepository;
    private final S3Client s3Upload;
    private final StoreDetailsImageRepository detailsImageRepository;
    private final StoreDetailsQueryRepository detailsQueryRepository;
    private final Log log = LogFactory.getLog(getClass());

    /*가게 배리어프리 정보 제보하기*/
    public HttpBodyMessage report(StoreDetailsForm detailsForm, List<MultipartFile> images, Member member) throws IOException {
        Store store = storeRepository.findById(detailsForm.getStoreId()).orElse(null);
        if(store == null){
            return new HttpBodyMessage("fail", "가게정보없음");
        }
        StoreDetails storeDetails = new StoreDetails(detailsForm, store, member);

        boolean isAlreadyCertificated = detailsRepository.existsByStoreAndMemberAndIsCertificated(store, member, 1);
        int isCertificated = checkCertificated(images, detailsForm.getIsAround(), isAlreadyCertificated);
        storeDetails.checkCertificate(isCertificated);

        uploadImage(images, storeDetails);
        detailsRepository.save(storeDetails);
        return new HttpBodyMessage("success", "제보하기 성공");
    }

    /*봉사인증이 가능한지 체크하는 로직*/
    private int checkCertificated(List<MultipartFile> images, boolean isAround, boolean isAlreadyCertificated){
        if (images == null || images.size() == 0 || !isAround || isAlreadyCertificated) {
            return 0;
        }
        return 1;
    }

    /*제보 이미지 업로드*/
    private void uploadImage(List<MultipartFile> images, StoreDetails details) throws IOException {
        if (images == null || images.size() == 0) {
            return;
        }

        for(MultipartFile image : images){
            String imageUrl = s3Upload.upload(image);
            StoreDetailsImage imageEntity = new StoreDetailsImage(imageUrl, details);
            detailsImageRepository.save(imageEntity);
        }
    }

    /*내가 제보한 가게목록 조회*/
    public PageResult<MyReportStoreDto> getMyStores(Member member, Pageable pageable){
        List<MyReportStoreDto> myStores = detailsQueryRepository.getMyStores(member, pageable);
        long count = detailsQueryRepository.getMyStoreCount(member);
        return new PageResult<>(myStores, count);
    }
}
