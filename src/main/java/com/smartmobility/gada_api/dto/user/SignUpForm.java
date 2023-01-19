package com.smartmobility.gada_api.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
@ApiModel(value = "회원가입 폼")
public class SignUpForm {
    @ApiModelProperty(value = "핸드폰번호")
    private String cell_num;

    @ApiModelProperty(value = "비밀번호")
    private String passwd;

    @ApiModelProperty(value = "닉네임")
    private String nickname;

    @ApiModelProperty(value = "필수약관동의여부")
    private int required_terms_yn;

    @ApiModelProperty(value = "선택약관동의여부")
    private int optional_terms_yn;

    @ApiModelProperty(value = "해당항목")
    private String category;

    @ApiModelProperty(value = "사용하는 장애 도구")
    private String tool_type;

    @ApiModelProperty(value = "선호하는 교통수단 1:버스, 2:지하철, 3: 장애인콜택시, 4:도보, 5:없음")
    private int f_mobil;
}
