package com.smartmobility.gada_api.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface VersionDAO {
	String versionCall() throws Exception;

}
