package com.smartmobility.gada_api.service;

import com.smartmobility.gada_api.dto.footprint.*;
import com.smartmobility.gada_api.mapper.FootprintMapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.Log;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FootprintService {
    private final FootprintMapper footprintMapper;
    private final ModelMapper modelMapper;
    private final Log log;

    public void registerMultipleAnswer(FootprintMultipleForm footprintMultipleForm){
        Integer existCount = footprintMapper.findByQuestionNameAndNumber(footprintMultipleForm.getQuestion_name(), footprintMultipleForm.getNumber());
        existCount = calculateCount(existCount);
        FootPrintMultiple footPrintMultiple = modelMapper.map(footprintMultipleForm, FootPrintMultiple.class);
        footPrintMultiple.setCount(existCount);

        if(existCount == 1){
            footprintMapper.saveMultiple(footPrintMultiple);
            return;
        }
        footprintMapper.updateMultipleCount(footPrintMultiple);
    }

    private int calculateCount(Integer existCount){
        if(existCount == null){
            return 1;
        }
        existCount++;
        return existCount;
    }

    public double getCountRatio(FootprintMultipleForm footprintMultipleForm){
        Double totalCount = footprintMapper.totalCountByQuestionName();
        Integer existCount = footprintMapper.findByQuestionNameAndNumber(footprintMultipleForm.getQuestion_name(), footprintMultipleForm.getNumber());
        if(totalCount == null || existCount == null){
            return -1;
        }

        return ((double) existCount / totalCount) * 100.0;
    }

    public void registerSubjectiveAnswer(FootprintSubjectiveForm footprintSubjectiveForm){
        footprintMapper.saveSubjective(footprintSubjectiveForm);
    }

    public List<AnswerCountForm> getAnswerRatioList(QuestionNameForm questionNameForm){
        Double totalCount = footprintMapper.totalCountByQuestionName();
        List<AnswerCountForm> answerCountList = footprintMapper.getAnswerCount(questionNameForm.getQuestionName());

        for(AnswerCountForm answer : answerCountList){
            answer.calculateAnswerRatio(totalCount);
        }
        return answerCountList;
    }
}
