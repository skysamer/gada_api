package com.smartmobility.gada_api.dto.footprint;

import lombok.Getter;

@Getter
public class AnswerCountForm {
    private int number;
    private double count;
    private double ratio;

    public void calculateAnswerRatio(double totalCount){
        this.ratio = (this.count / totalCount) * 100;
    }
}
