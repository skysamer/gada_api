package com.smartmobility.gada_api.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
@ApiModel(value = "로그인 폼")
public class LoginForm {
    @ApiModelProperty(value = "핸드폰번호")
    private String cell;

    @ApiModelProperty(value = "비밀번호")
    private String password;
}
