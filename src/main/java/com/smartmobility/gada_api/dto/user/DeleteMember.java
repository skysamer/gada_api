package com.smartmobility.gada_api.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Random;

@Getter @Builder
@AllArgsConstructor @NoArgsConstructor
public class DeleteMember {
    @ApiModelProperty(value = "핸드폰번호")
    private String cell_num;

    @ApiModelProperty(value = "닉네임")
    private String nickname;

    
}
