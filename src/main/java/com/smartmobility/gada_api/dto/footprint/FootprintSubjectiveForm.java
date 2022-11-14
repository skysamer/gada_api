package com.smartmobility.gada_api.dto.footprint;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
@ApiModel(value = "발자국 주관식 문항 답안 전송 폼")
public class FootprintSubjectiveForm {
    @ApiModelProperty(value = "문항")
    private String question_name;

    @ApiModelProperty(value = "답변")
    private String answer;

    @ApiModelProperty(value = "이유")
    private String reason;
}
