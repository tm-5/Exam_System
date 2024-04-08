package com.examBE.BackendExamSys.models;

import com.examBE.BackendExamSys.entities.ExamEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExamDetailHideAnswerModel {
    private int id;
    private String name;
    private String description;
    private Boolean type;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Integer timeDuration;
    List<QuestionHideAnswerModel> question;
    public ExamDetailHideAnswerModel(ExamEntity exam, List<QuestionHideAnswerModel> question){
        this.id = exam.getId();
        this.name = exam.getName();
        this.description = exam.getDescription();
        this.type = exam.getType();
        this.startTime = exam.getStartTime();
        this.endTime = exam.getEndTime();
        this.timeDuration = exam.getTimeDuration();
        this.question = question;
    }
}
