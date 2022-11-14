package com.smartmobility.gada_api.dto.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor @NoArgsConstructor
public class MemberUpdateForm {
    @ApiModelProperty(value = "핸드폰번호")
    private String cell_num;

    @ApiModelProperty(value = "닉네임")
    private String nickname;

    @ApiModelProperty(value = "선호하는 교통수단 1:버스, 2:지하철, 3: 장애인콜택시, 4:도보, 5:없음")
    private int f_mobil;

    @ApiModelProperty(value = "해당항목")
    private String category;

    @ApiModelProperty(value = "사용하는 장애 도구")
    private String tool_type;

    @ApiModelProperty(value = "프로필이미지url")
    private String profile_img_url;

    @ApiModelProperty(value = "닉네임 변경 여부")
    private Boolean is_nickname_update;
   
    @ApiModelProperty(value = "지하철 즐겨찾기")
    private String favorite_station;

}