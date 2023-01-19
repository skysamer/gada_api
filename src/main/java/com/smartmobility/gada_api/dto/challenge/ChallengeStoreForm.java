package com.smartmobility.gada_api.dto.challenge;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class ChallengeStoreForm {
    @ApiModelProperty(value = "시퀀스")
    private Long id;

    @ApiModelProperty(value = "가게이름")
    private String title;

    @ApiModelProperty(value = "좋아요 수")
    private int like_count;

    @ApiModelProperty(value = "카테고리")
    private String category;

    @ApiModelProperty(value = "설명")
    private String description;

    @ApiModelProperty(value = "주소")
    private String location;

    @ApiModelProperty(value = "연락처")
    private String contact;

    @ApiModelProperty(value = "영업시간")
    private String operating_time;

    @ApiModelProperty(value = "주출입구")
    private String main_entrance;

    @ApiModelProperty(value = "정보안내")
    private String info_guide;

    @ApiModelProperty(value = "주차여부")
    private String parking;

    @ApiModelProperty(value = "편의시설")
    private String facilities;

    @ApiModelProperty(value = "이미지 데이터")
    private byte[] image;

    @ApiModelProperty(value = "좋아요")
    private int likes;

    public void setImage(byte[] image){
        this.image = image;
    }

}
