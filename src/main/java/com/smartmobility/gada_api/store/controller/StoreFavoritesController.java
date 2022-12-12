package com.smartmobility.gada_api.store.controller;

import com.smartmobility.gada_api.global.dto.HttpBodyMessage;
import com.smartmobility.gada_api.member.domain.Member;
import com.smartmobility.gada_api.store.dto.MyFavoritesStoreDto;
import com.smartmobility.gada_api.store.service.StoreFavoritesService;
import com.smartmobility.gada_api.store.util.UserInfoExtractor;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"함께 가게 즐겨찾기 api"})
@RestController
@RequestMapping("/store")
@RequiredArgsConstructor
public class StoreFavoritesController {
    private final StoreFavoritesService service;
    private final Log log = LogFactory.getLog(getClass());

    @ApiOperation(value = "함께가게 즐겨찾기 액션 api")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "store-id", value = "가게고유번호")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "즐겨찾기 등록 // 즐겨찾기 등록해제"),
            @ApiResponse(code = 400, message = "토큰이 유효하지 않음")
    })
    @PutMapping("/favorites/{store-id}")
    public ResponseEntity<HttpBodyMessage> register(@AuthenticationPrincipal Member member,
                                                    @PathVariable("store-id") Long storeId){
        if(member == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        HttpBodyMessage result = service.register(storeId, member);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiOperation(value = "내가 즐겨찾기한 함께가게 목록 조회 (디폴트가 기본순조회)")
    @ApiResponses({
            @ApiResponse(code = 200, message = "조회성공"),
            @ApiResponse(code = 400, message = "토큰이 유효하지 않음")
    })
    @GetMapping("/favorites/my")
    public ResponseEntity<List<MyFavoritesStoreDto>> getMyFavoritesStores(@AuthenticationPrincipal Member member){
        if(member == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<MyFavoritesStoreDto> result = service.getMyFavorites(member);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
