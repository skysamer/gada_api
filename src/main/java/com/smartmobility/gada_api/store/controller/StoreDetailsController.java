package com.smartmobility.gada_api.store.controller;

import com.smartmobility.gada_api.global.dto.HttpBodyMessage;
import com.smartmobility.gada_api.global.dto.PageResult;
import com.smartmobility.gada_api.member.domain.Member;
import com.smartmobility.gada_api.store.dto.MyReportStoreDto;
import com.smartmobility.gada_api.store.dto.StoreDetailsForm;
import com.smartmobility.gada_api.store.service.StoreDetailsService;
import com.smartmobility.gada_api.store.util.UserInfoExtractor;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Api(tags = {"함께 가게 제보하기 api"})
@RestController
@RequestMapping("/store")
@RequiredArgsConstructor
public class StoreDetailsController {
    private final StoreDetailsService service;

    @ApiOperation(value = "함께가게 제보하기")
    @ApiResponses({
            @ApiResponse(code = 201, message = "제보하기 성공"),
            @ApiResponse(code = 400, message = "가게정보 없음 // 유효하지 않은 jwt 토큰"),
            @ApiResponse(code = 500, message = "이미지 업로드 오류"),
    })
    @PostMapping("/report")
    public ResponseEntity<HttpBodyMessage> report(@RequestPart(value = "details") StoreDetailsForm detailsForm,
                                                  @Nullable @RequestPart(value = "image", required = false) List<MultipartFile> images,
                                                  @AuthenticationPrincipal Member member) throws IOException {
        if(member == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        HttpBodyMessage result = service.report(detailsForm, images, member);
        if(result.getCode().equals("fail")){
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @ApiOperation(value = "내가 제보한 가게 리스트 조회")
    @ApiResponses({
            @ApiResponse(code = 200, message = "조회성공"),
            @ApiResponse(code = 400, message = "유효하지 않은 jwt 토큰")
    })
    @GetMapping("/report/my/{page}/{size}")
    public ResponseEntity<PageResult<MyReportStoreDto>> getMyReportStores(@AuthenticationPrincipal Member member,
                                                                          @PathVariable Integer page,
                                                                          @PathVariable Integer size){
        if(member == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        PageResult<MyReportStoreDto> result = service.getMyStores(member, PageRequest.of(page, size));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
