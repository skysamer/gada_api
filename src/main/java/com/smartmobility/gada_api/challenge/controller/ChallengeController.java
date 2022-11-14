package com.smartmobility.gada_api.challenge.controller;

import com.smartmobility.gada_api.challenge.service.ChallengeService;
import com.smartmobility.gada_api.dto.challenge.ChallengeEventForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@Api(tags = {"함께 챌린지 api"})
@RestController
@RequestMapping("/challenge")
@RequiredArgsConstructor
public class ChallengeController {
    private final ChallengeService service;

    @ApiOperation(value = "메인 이벤트")
    @GetMapping("/event")
    public List<ChallengeEventForm> getEvent() throws IOException {
        return service.getEvent();
    }
}
