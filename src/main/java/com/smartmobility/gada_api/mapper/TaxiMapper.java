package com.smartmobility.gada_api.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.smartmobility.gada_api.dto.taxi.TaxiInfoDTO;
import com.smartmobility.gada_api.dto.taxi.TaxiListDTO;

@Mapper
public interface TaxiMapper {
	List<TaxiListDTO> selectAll() throws Exception;
	List<TaxiListDTO> selectAllByRegion(String division) throws Exception;
	TaxiInfoDTO selectTaxiInfoByIdx(int idx) throws Exception;
}
