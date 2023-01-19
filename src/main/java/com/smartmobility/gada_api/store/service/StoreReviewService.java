package com.smartmobility.gada_api.store.service;

import com.smartmobility.gada_api.global.dto.HttpBodyMessage;
import com.smartmobility.gada_api.global.dto.PageResult;
import com.smartmobility.gada_api.member.domain.Member;
import com.smartmobility.gada_api.store.domain.*;
import com.smartmobility.gada_api.store.dto.MyStoreReviewsDto;
import com.smartmobility.gada_api.store.dto.StoreReviewDto;
import com.smartmobility.gada_api.store.dto.StoreReviewForm;
import com.smartmobility.gada_api.store.dto.StoreReviewImageDto;
import com.smartmobility.gada_api.store.repository.*;
import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class StoreReviewService {
    private final StoreRepository storeRepository;
    private final StoreReviewRepository reviewRepository;
    private final StoreReviewImageRepository imageRepository;
    private final S3Client s3Client;
    private final StoreReviewQueryRepository reviewQueryRepository;
    private final StoreReviewImageRepository reviewImageRepository;
    private final ModelMapper modelMapper;
    private final Log log = LogFactory.getLog(getClass());

    /*가게 리뷰 등록*/
    public HttpBodyMessage post(StoreReviewForm reviewForm, List<MultipartFile> images, Member member) {
        Store store = storeRepository.findById(reviewForm.getStoreId()).orElse(null);
        if (store == null) {
            return new HttpBodyMessage("fail", "가게정보없음");
        }

        StoreReview review = new StoreReview(reviewForm, store, member);
        try{
            uploadImages(images, review);
        }catch (Exception e){
            log.error(e.getMessage());
            throw new RuntimeException();
        }
        reviewRepository.save(review);
        return new HttpBodyMessage("success", "리뷰등록성공");
    }

    /*리뷰 이미지 업로드*/
    private void uploadImages(List<MultipartFile> images, StoreReview review) throws IOException {
        if(images == null){
            return;
        }

        for(MultipartFile image : images){
            String imageUrl = s3Client.upload(image);
            StoreReviewImage imageEntity = new StoreReviewImage(imageUrl, review);
            imageRepository.save(imageEntity);
        }
    }

    /*가게별 리뷰목록 조회*/
    public PageResult<StoreReviewDto> getReviews(Long storeId, Pageable pageable){
        List<StoreReviewDto> reviews = reviewQueryRepository.getReviews(storeId, pageable);
        List<StoreReviewImageDto> images = reviewQueryRepository.getReviewImages(storeId);
        reviews.forEach(review -> {
            review.getImageUrls().addAll(images.stream()
                    .filter(image -> image.getReviewId().equals(review.getId()))
                    .collect(Collectors.toList()));
        });

        long count = reviewQueryRepository.getReviewCount(storeId);
        return new PageResult<>(reviews, count);
    }

    /*리뷰삭제*/
    public void remove(Long id){
        StoreReview review = reviewRepository.findById(id).orElse(null);
        if(review == null){
            log.error("remove error");
            throw new RuntimeException();
        }

        List<StoreReviewImage> reviewImages = reviewImageRepository.findAllByStoreReview(review);
        removeImage(reviewImages);
        reviewImageRepository.deleteAll(reviewImages);
        reviewRepository.delete(review);
    }

    /*리뷰의 이미지 삭제*/
    private void removeImage(List<StoreReviewImage> reviewImages){
        for(StoreReviewImage reviewImage : reviewImages){
            s3Client.remove(reviewImage.getImageUrl());
        }
    }

    /*리뷰수정*/
    public void modify(Long id, StoreReviewForm reviewForm, List<MultipartFile> images) throws Throwable {
        StoreReview review = reviewRepository.findById(id).orElseThrow(() -> new Throwable("id is null"));
        modelMapper.map(reviewForm, review);
        List<StoreReviewImage> reviewImages = reviewImageRepository.findAllByStoreReview(review);
        removeImage(reviewImages);
        reviewImageRepository.deleteAll(reviewImages);

        try{
            uploadImages(images, review);
        }catch (Exception e){
            log.error(e);
            throw new RuntimeException();
        }
    }

    /*나의 리뷰목록 조회*/
    public PageResult<MyStoreReviewsDto> getMyReviews(Member member, Pageable pageable){
        List<MyStoreReviewsDto> myReviews = reviewQueryRepository.getMyReviews(member, pageable);
        List<StoreReviewImageDto> images = reviewQueryRepository.getMyReviewImages(member);
        myReviews.forEach(review -> {
            review.getImageUrls().addAll(images.stream()
                    .filter(image -> image.getReviewId().equals(review.getId()))
                    .collect(Collectors.toList()));
        });
        long count = reviewQueryRepository.getMyReviewCount(member);
        return new PageResult<>(myReviews, count);
    }
}
