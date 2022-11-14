package com.smartmobility.gada_api.dto.challenge;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@ApiModel(value = "함께 챌린지 이벤트 데이터")
public class ChallengeEventForm {
    @ApiModelProperty(value = "시퀀스")
    private Long id;

    @ApiModelProperty(value = "제목")
    private String title;

    @ApiModelProperty(value = "내용")
    private String content;

    @ApiModelProperty(value = "시작일")
    private LocalDate started_at;

    @ApiModelProperty(value = "종료일")
    private LocalDate ended_at;

    @ApiModelProperty(value = "이미지 데이터")
    private byte[] image;

    public void setImage(byte[] image){
        this.image = image;
    }
}
