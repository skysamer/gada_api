package com.smartmobility.gada_api.store.controller;

import com.smartmobility.gada_api.member.domain.Member;
import com.smartmobility.gada_api.store.dto.RecommendedStoreDto;
import com.smartmobility.gada_api.store.dto.StoresDto;
import com.smartmobility.gada_api.store.dto.TotalStoreInfoDto;
import com.smartmobility.gada_api.store.service.StoreService;
import com.smartmobility.gada_api.store.util.UserInfoExtractor;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"함께 가게 정보 api"})
@RestController
@RequestMapping("/store")
@RequiredArgsConstructor
public class StoreController {
    private final StoreService service;
    private final UserInfoExtractor extractor;
    private final Log log = LogFactory.getLog(getClass());

    @ApiOperation(value = "자치단체 별 가게 리스트 정보")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "region", value = "행정구 명 (송파구, 마포구 등)"),
            @ApiImplicitParam(name = "keywords", value = "wheelchair, disabledToilet, babyCar, voiceGuide, childOk, parkingLot, toilet " +
                    "(해당항목을 리스트에 넣어 요청, 필터링 조건이 없을 경우 'none' 값을 넣어 요청)")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "조회성공")
    })
    @GetMapping("/list/{region}/{keywords}")
    public ResponseEntity<List<StoresDto>> getStores(@PathVariable String region, @PathVariable List<String> keywords){
        List<StoresDto> stores = service.getStores(region, keywords);
        return new ResponseEntity<>(stores, HttpStatus.OK);
    }

    @ApiOperation(value = "검색어로 가게 검색하기")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "가게이름 검색어")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "조회성공")
    })
    @GetMapping("/search/{name}")
    public ResponseEntity<List<StoresDto>> searchStores(@PathVariable String name){
        List<StoresDto> stores = service.searchStores(name);
        return new ResponseEntity<>(stores, HttpStatus.OK);
    }

    @ApiOperation(value = "가게 상세정보")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "가게의 고유 시퀀스")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "조회성공")
    })
    @GetMapping("/{id}")
    public ResponseEntity<TotalStoreInfoDto> getStore(@PathVariable Long id, @RequestHeader("X-AUTH-TOKEN") String token){
        Member member;
        try {
            member = extractor.extract(token);
        }catch (Exception e){
            TotalStoreInfoDto store = service.getStore(id, new Member(-1L));
            return new ResponseEntity<>(store, HttpStatus.OK);
        }
        TotalStoreInfoDto store = service.getStore(id, member);
        return new ResponseEntity<>(store, HttpStatus.OK);
    }

    @ApiOperation(value = "가다추천 함께가게 조회")
    @ApiResponses({
            @ApiResponse(code = 200, message = "조회성공")
    })
    @GetMapping("/recommend")
    public ResponseEntity<List<RecommendedStoreDto>> getRecommendedStores(@RequestHeader("X-AUTH-TOKEN") String token){
        Member member;
        try {
            member = extractor.extract(token);
        }catch (Exception e){
            List<RecommendedStoreDto> result = service.getRecommendedStores(new Member(-1L));
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        List<RecommendedStoreDto> result = service.getRecommendedStores(member);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
