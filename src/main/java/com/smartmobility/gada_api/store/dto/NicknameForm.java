package com.smartmobility.gada_api.store.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
@ApiModel(value = "닉네임 변경을 위한 폼")
public class NicknameForm {
    @ApiModelProperty(value = "닉네임")
    private String nickname;
}
