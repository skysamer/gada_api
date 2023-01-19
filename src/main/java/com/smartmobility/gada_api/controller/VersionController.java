package com.smartmobility.gada_api.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartmobility.gada_api.mapper.VersionDAO;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class VersionController {
	private final VersionDAO versionDao;

    @GetMapping("/version")
    public String versionCheck() throws Exception {
		return versionDao.versionCall();
    }
}
