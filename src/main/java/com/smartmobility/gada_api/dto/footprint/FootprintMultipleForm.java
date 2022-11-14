package com.smartmobility.gada_api.dto.footprint;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class FootprintMultipleForm {
    @ApiModelProperty(value = "선택번호")
    private int number;

    @ApiModelProperty(value = "문항")
    private String question_name;
}
