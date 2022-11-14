package com.smartmobility.gada_api.store.domain;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.Valid;

@Entity @Getter
@ApiModel(value = "함께 가게 정보 엔티티")
@Table(name = "store")
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "고유번호")
    private Long id;

    @ApiModelProperty(value = "자치단체구분코드")
    @Column(name = "local_code")
    private String localCode;

    @ApiModelProperty(value = "제어코드")
    @Column(name = "control_number")
    private String controlNumber;

    @ApiModelProperty(value = "이름")
    private String name;

    @ApiModelProperty(value = "지번주소")
    @Column(name = "number_address")
    private String numberAddress;

    @ApiModelProperty(value = "도로명주소")
    @Column(name = "street_address")
    private String streetAddress;

    @ApiModelProperty(value = "전화번호")
    private String phone;

    @ApiModelProperty(value = "업종명")
    @Column(name = "business_type")
    private String businessType;

    @ApiModelProperty(value = "위도")
    private String lat;

    @ApiModelProperty(value = "경도")
    private String lon;

}
