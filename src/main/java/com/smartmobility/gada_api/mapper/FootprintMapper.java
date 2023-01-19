package com.smartmobility.gada_api.mapper;

import com.smartmobility.gada_api.dto.footprint.AnswerCountForm;
import com.smartmobility.gada_api.dto.footprint.FootPrintMultiple;
import com.smartmobility.gada_api.dto.footprint.FootprintSubjectiveForm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FootprintMapper {
    void saveMultiple(FootPrintMultiple footPrintMultiple);
    void updateMultipleCount(FootPrintMultiple footPrintMultiple);
    Integer findByQuestionNameAndNumber(@Param("questionName") String questionName, @Param("number") int number);
    Double totalCountByQuestionName();
    void saveSubjective(FootprintSubjectiveForm footprintSubjectiveForm);
    List<AnswerCountForm> getAnswerCount(String questionName);
}
