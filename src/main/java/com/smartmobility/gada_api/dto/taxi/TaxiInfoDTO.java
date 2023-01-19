package com.smartmobility.gada_api.dto.taxi;

import lombok.Getter;

@Getter
public class TaxiInfoDTO {
	private int idx;
	private String target_use;
	private String operating_area;
	private String operating_time;
	private String how_to_use;
	private String usage_fee;
}