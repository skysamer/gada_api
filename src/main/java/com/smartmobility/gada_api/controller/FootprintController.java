package com.smartmobility.gada_api.controller;

import com.smartmobility.gada_api.dto.footprint.AnswerCountForm;
import com.smartmobility.gada_api.dto.footprint.FootprintMultipleForm;
import com.smartmobility.gada_api.dto.footprint.FootprintSubjectiveForm;
import com.smartmobility.gada_api.dto.ReturnStatusCode;
import com.smartmobility.gada_api.dto.footprint.QuestionNameForm;
import com.smartmobility.gada_api.service.FootprintService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"오늘의 발자국 api"})
@RestController
@RequestMapping("/footprint/*")
@RequiredArgsConstructor
public class FootprintController {
    private final FootprintService service;

    @ApiOperation(value = "객관식 답변 전송")
    @PostMapping("/multiple/answer")
    public ReturnStatusCode answerMultiple(@RequestBody FootprintMultipleForm footprintMultipleForm){
        service.registerMultipleAnswer(footprintMultipleForm);
        return new ReturnStatusCode("200");
    }

    @ApiOperation(value = "객관식 각 문항의 답변 별 비율 조회")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "question-name", value = "문항내용"),
            @ApiImplicitParam(name = "number", value = "답변번호"),
    })
    @PostMapping("/multiple/ratio")
    public double getMultipleAnswerRatio(@RequestBody FootprintMultipleForm footprintMultipleForm){
        return service.getCountRatio(footprintMultipleForm);
    }

    @ApiOperation(value = "객관식 문항 전체 비율 조회")
    @PostMapping("/multiple/ratio/list")
    public List<AnswerCountForm> getMultipleAnswerRatio(@RequestBody QuestionNameForm questionNameForm){
        return service.getAnswerRatioList(questionNameForm);
    }

    @ApiOperation(value = "주관식 문항에 대한 답변 전송")
    @PostMapping("/subjective/answer")
    public ReturnStatusCode answerSubjective(@RequestBody FootprintSubjectiveForm footprintSubjectiveForm){
        service.registerSubjectiveAnswer(footprintSubjectiveForm);
        return new ReturnStatusCode("200");
    }
}
