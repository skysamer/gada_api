package com.smartmobility.gada_api.store.controller;

import com.smartmobility.gada_api.global.dto.HttpBodyMessage;
import com.smartmobility.gada_api.global.dto.PageResult;
import com.smartmobility.gada_api.member.domain.Member;
import com.smartmobility.gada_api.store.dto.MyStoreReviewsDto;
import com.smartmobility.gada_api.store.dto.StoreReviewDto;
import com.smartmobility.gada_api.store.dto.StoreReviewForm;
import com.smartmobility.gada_api.store.service.StoreReviewService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Api(tags = {"함께 가게 리뷰 api"})
@RestController
@RequestMapping("/store")
@RequiredArgsConstructor
public class StoreReviewController {
    private final StoreReviewService service;
    private final Log log = LogFactory.getLog(getClass());

    @ApiOperation(value = "함께가게 리뷰등록")
    @ApiResponses({
            @ApiResponse(code = 201, message = "리뷰등록성공"),
            @ApiResponse(code = 400, message = "가게정보없음 // 토큰 오류"),
            @ApiResponse(code = 500, message = "이미지 업로드 오류"),
    })
    @PostMapping("/review")
    public ResponseEntity<HttpBodyMessage> post(@RequestPart(value = "review") StoreReviewForm reviewForm,
                                                @Nullable @RequestPart(value = "image", required = false) List<MultipartFile> images,
                                                @AuthenticationPrincipal Member member){
        if(member == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        HttpBodyMessage result = service.post(reviewForm, images, member);
        if(result.getCode().equals("fail")){
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @ApiOperation(value = "가게별 리뷰 리스트 조회")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "가게의 고유 시퀀스"),
            @ApiImplicitParam(name = "page", value = "가게의 고유 시퀀스"),
            @ApiImplicitParam(name = "size", value = "가게의 고유 시퀀스")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "조회성공"),
            @ApiResponse(code = 500, message = "이미지 불러오기 오류"),
    })
    @GetMapping("/review/{id}/{page}/{size}")
    public ResponseEntity<PageResult<StoreReviewDto>> getReviews(@PathVariable Long id,
                                                                 @PathVariable Integer page,
                                                                 @PathVariable Integer size){
        PageResult<StoreReviewDto> result = service.getReviews(id, PageRequest.of(page, size));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiOperation(value = "리뷰삭제")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "리뷰의 고유 시퀀스")
    })
    @ApiResponses({
            @ApiResponse(code = 204, message = "삭제성공"),
            @ApiResponse(code = 400, message = "리뷰정보 없음"),
    })
    @DeleteMapping("/review/{id}")
    public ResponseEntity<Object> remove(@PathVariable Long id){
        try{
            service.remove(id);
        }catch (Exception e){
            log.error(e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "리뷰수정")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "리뷰의 고유 시퀀스")
    })
    @ApiResponses({
            @ApiResponse(code = 204, message = "수정성공"),
            @ApiResponse(code = 400, message = "리뷰정보 없음"),
            @ApiResponse(code = 500, message = "이미지 업로드 오류")
    })
    @PutMapping("/review/{id}")
    public ResponseEntity<Object> modify(@PathVariable Long id,
                                         @RequestPart(value = "review") StoreReviewForm reviewForm,
                                         @Nullable @RequestPart(value = "image", required = false) List<MultipartFile> images){
        try{
            service.modify(id, reviewForm, images);
        }catch (Throwable e){
            log.error(e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "나의 리뷰 목록 조회")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "페이지번호"),
            @ApiImplicitParam(name = "size", value = "페이지 당 개수")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "조회성공"),
            @ApiResponse(code = 400, message = "유효하지 않은 토큰")
    })
    @GetMapping("/review/my/{page}/{size}")
    public ResponseEntity<PageResult<MyStoreReviewsDto>> getMyReviews(@AuthenticationPrincipal Member member,
                                                                      @PathVariable Integer page,
                                                                      @PathVariable Integer size){
        if(member == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        PageResult<MyStoreReviewsDto> result = service.getMyReviews(member, PageRequest.of(page, size));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
