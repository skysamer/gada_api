package com.smartmobility.gada_api.member.controller;

import com.smartmobility.gada_api.config.JwtTokenProvider;
import com.smartmobility.gada_api.global.dto.HttpBodyMessage;
import com.smartmobility.gada_api.member.domain.Member;
import com.smartmobility.gada_api.member.dto.DetailsSignUpForm;
import com.smartmobility.gada_api.member.dto.LoginResultDTO;
import com.smartmobility.gada_api.member.dto.SocialLoginDTO;
import com.smartmobility.gada_api.member.service.OauthService;
import com.smartmobility.gada_api.member.type.Provider;
import com.smartmobility.gada_api.store.dto.NicknameForm;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/member")
@Api(tags = {"로그인 및 회원가입 api"})
@RequiredArgsConstructor
public class OauthController {
    private final OauthService oauthService;
    private final JwtTokenProvider jwtTokenProvider;
    private final Log log = LogFactory.getLog(getClass());

    @ApiOperation(value = "소셜로그인 (헤더추가필요 -> access-token : ${access-token}, 애플'로그인'의 경우 'sub' 값 전달)")
    @ResponseHeader(name = "X-AUTH-TOKEN", description = "JWT 토큰값 전송")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "provider", value = "소셜로그인 타입(google, kakao, apple)"),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "로그인 성공"),
            @ApiResponse(code = 201, message = "회원가입 완료 (추가정보 입력 필요)"),
            @ApiResponse(code = 400, message = "토큰오류")
    })
    @PostMapping(value = "/oauth2")
    public ResponseEntity<LoginResultDTO> socialLogin(@RequestBody SocialLoginDTO loginDTO,
                                                       @RequestHeader(name = "access-token") String token,
                                                       HttpServletResponse response) {
        Provider socialLoginType = Provider.valueOf(loginDTO.getProvider().toUpperCase());
        LoginResultDTO resultDTO;
        try {
            resultDTO = oauthService.socialLogin(token, socialLoginType, loginDTO.getSub());
        }catch (Exception e){
            log.error(e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if(resultDTO.getRole() == null){
            return new ResponseEntity<>(resultDTO, HttpStatus.CREATED);
        }
        String jwtToken = jwtTokenProvider.createTokenLogin(resultDTO.getEmail() + "&" + resultDTO.getProvider().name(),
                resultDTO.getRole());
        response.setHeader("X-AUTH-TOKEN", jwtToken);
        return new ResponseEntity<>(resultDTO, HttpStatus.OK);
    }


    @ApiOperation(value = "회원가입을 위한 추가정보 입력 api")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "1차 회원가입 시 반환된 유저 고유 번호"),
    })
    @ApiResponses({
            @ApiResponse(code = 204, message = "추가정보 입력 성공"),
            @ApiResponse(code = 400, message = "요청값은 null일 수 없습니다. // 유저 정보가 존재하지 않음")
    })
    @PutMapping("/details/{id}")
    public ResponseEntity<Object> detailsSignUp(@PathVariable Long id, @RequestBody DetailsSignUpForm detailsSignUpForm){
        try{
            oauthService.detailsSignUp(id, detailsSignUpForm);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "닉네임변경")
    @ApiResponses({
            @ApiResponse(code = 200, message = "닉네임변경완료"),
            @ApiResponse(code = 409, message = "닉네임중복")
    })
    @PatchMapping("/nickname")
    public ResponseEntity<HttpBodyMessage> modify(@AuthenticationPrincipal Member member, @RequestBody NicknameForm nicknameForm){
        HttpBodyMessage result = oauthService.modify(member, nicknameForm.getNickname());
        if(result.getCode().equals("fail")){
            return new ResponseEntity<>(result, HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ApiOperation(value = "회원정보삭제")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "유저 고유 번호"),
    })
    @ApiResponses({
            @ApiResponse(code = 204, message = "삭제성공"),
            @ApiResponse(code = 400, message = "유저 정보가 존재하지 않음"),
            @ApiResponse(code = 409, message = "닉네임 중복")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> remove(@PathVariable Long id){
        try{
            oauthService.remove(id);
        }catch (Exception e){
            log.error(e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "프로필이미지등록")
    @ApiResponses({
            @ApiResponse(code = 204, message = "등록성공"),
            @ApiResponse(code = 400, message = "유효하지 않은 토큰"),
            @ApiResponse(code = 500, message = "이미지등록실패")
    })
    @PutMapping("/profile-img")
    public ResponseEntity<Object> register(@AuthenticationPrincipal Member member, @RequestPart MultipartFile image){
        if(member == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try{
            oauthService.register(member, image);
        }catch (IOException e){
            log.error(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "프로필이미지삭제")
    @ApiResponses({
            @ApiResponse(code = 204, message = "프로필이미지 삭제 성공"),
            @ApiResponse(code = 400, message = "유효하지 않은 토큰")
    })
    @DeleteMapping("/profile-img")
    public ResponseEntity<Object> remove(@AuthenticationPrincipal Member member){
        if(member == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        oauthService.removeProfileImg(member);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
