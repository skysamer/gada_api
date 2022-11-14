package com.smartmobility.gada_api.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.springframework.web.bind.annotation.*;

import com.smartmobility.gada_api.mapper.TaxiMapper;
import com.smartmobility.gada_api.dto.taxi.TaxiInfoDTO;
import com.smartmobility.gada_api.dto.taxi.TaxiListDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/taxi/*")
@RequiredArgsConstructor
public class TaxiController {
	private final TaxiMapper taxiMapper;
	private final Log log;

	@GetMapping("/list")
	public List<TaxiListDTO> taxiList() throws Exception {
		return taxiMapper.selectAll();
	}
	
	@GetMapping("/list/{division}")
	public List<TaxiListDTO> taxiListByDivision(@PathVariable String division) throws Exception {
		return taxiMapper.selectAllByRegion(division);
	}

	@GetMapping("/info/{idx}")
	public TaxiInfoDTO taxiInfoByIdx(@PathVariable int idx) throws Exception {
		return taxiMapper.selectTaxiInfoByIdx(idx);
	}

}
