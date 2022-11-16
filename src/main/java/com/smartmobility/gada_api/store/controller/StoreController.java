package com.smartmobility.gada_api.store.controller;

import com.smartmobility.gada_api.member.domain.Member;
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
            @ApiImplicitParam(name = "region", value = "행정구 명 (송파구, 마포구 등)")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "조회성공")
    })
    @GetMapping("/list/{region}")
    public ResponseEntity<List<StoresDto>> getStores(@PathVariable String region){
        List<StoresDto> stores = service.getStores(region);
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
}
