package com.examBE.BackendExamSys.models;

import com.examBE.BackendExamSys.entities.ExamEntity;
import com.examBE.BackendExamSys.entities.ContestEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContestDetailModel {
    private int id;
    private int idUser;
    private ExamEntity exam;
//    private Float score;
    private LocalDateTime startTime;
    private LocalDateTime submitTime;
    private Integer rightAnswer;
    private Integer wrongAnswer;
    private Integer blankAnswer;
    public ContestDetailModel(ContestEntity contest, ExamEntity exam){
        this.id = contest.getId();
        this.idUser = contest.getIdUser();
//        this.score = contest.getScore();
        this.startTime = contest.getStartTime();
        this.submitTime = contest.getSubmitTime();
        this.rightAnswer = contest.getRightAnswer();
        this.wrongAnswer = contest.getWrongAnswer();
        this.blankAnswer = contest.getBlankAnswer();
        this.exam = exam;
    }
}
