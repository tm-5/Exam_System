package com.examBE.BackendExamSys.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContestExamModel {
    private int idExam;
//    private Double score;
    private Long rightAnswer;
    private Long wrongAnswer;
    private Long blankAnswer;
    private Long finishExam;
    private Long totalExam;
    public ContestExamModel(Object[] obj){
        this.idExam = (int) obj[0];
//        this.score = (Double) obj[1];
        this.rightAnswer = (Long) obj[1];
        this.wrongAnswer = (Long) obj[2];
        this.blankAnswer = (Long) obj[3];
        this.finishExam = (Long) obj[4];
        this.totalExam = (Long) obj[5];
    }
}
