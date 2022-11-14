package com.smartmobility.gada_api.dto.footprint;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
@ApiModel(value = "발자국 객관식 문항 답변 전송 엔티티")
public class FootPrintMultiple {
    @ApiModelProperty(value = "해당 문항 별 객관식 답안 선택 개수")
    private Integer count;

    @ApiModelProperty(value = "선택번호")
    private int number;

    @ApiModelProperty(value = "문항")
    private String question_name;

    public void setCount(Integer count){
        this.count = count;
    }
}
