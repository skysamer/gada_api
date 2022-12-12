package com.smartmobility.gada_api.global.handler;

import com.smartmobility.gada_api.global.dto.HttpBodyMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.SignatureException;

@RestController
public class ExceptionController {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected Object handleMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
        final HttpBodyMessage errorResponse = new HttpBodyMessage("fail", "요청값은 null일 수 없습니다");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(SignatureException.class)
    protected Object handleJWTTokenInvalidException(SignatureException e, HttpServletRequest request) {
        request.getHeader("X-AUTH-TOKEN");
        final HttpBodyMessage errorResponse = new HttpBodyMessage("fail", "토큰 오류");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
}
