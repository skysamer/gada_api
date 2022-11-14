package com.smartmobility.gada_api.global.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@ApiModel(value = "api 응답 메시지 객체")
public class HttpBodyMessage {
    @ApiModelProperty(value = "코드 (success // fail)")
    private String code;
    @ApiModelProperty(value = "내용")
    private String message;
}
