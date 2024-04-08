package com.examBE.BackendExamSys.models;

import com.examBE.BackendExamSys.entities.QuestionEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionHideAnswerModel {
    private int id;

    private int idExam;

    private String content;

    private String answer1;

    private String answer2;

    private String answer3;

    private String answer4;
    public QuestionHideAnswerModel(QuestionEntity questionEntity){
        this.id = questionEntity.getId();
        this.idExam = questionEntity.getIdExam();
        this.content = questionEntity.getContent();
        this.answer1 = questionEntity.getAnswer1();
        this.answer2 = questionEntity.getAnswer2();
        this.answer3 = questionEntity.getAnswer3();
        this.answer4 = questionEntity.getAnswer4();
    }
}
