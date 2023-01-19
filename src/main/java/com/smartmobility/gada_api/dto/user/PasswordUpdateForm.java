package com.smartmobility.gada_api.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
@ApiModel(value = "비밀번호 변경 폼")
public class PasswordUpdateForm {
    @ApiModelProperty(value = "핸드폰 번호")
    private String cell_num;

    @ApiModelProperty(value = "비밀번호")
    private String passwd;

    public void encode(String encodedPasswd){
        this.passwd = encodedPasswd;
    }
}
