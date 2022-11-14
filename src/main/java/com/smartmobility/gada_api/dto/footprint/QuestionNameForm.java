package com.smartmobility.gada_api.dto.footprint;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
@ApiModel(value = "질문이름 전송 폼")
public class QuestionNameForm {
    @ApiModelProperty(value = "질문이름")
    private String questionName;
}
