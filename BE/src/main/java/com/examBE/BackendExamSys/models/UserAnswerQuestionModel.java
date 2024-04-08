package com.examBE.BackendExamSys.models;

import com.examBE.BackendExamSys.entities.QuestionEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAnswerQuestionModel {
    private Integer userAnswer;
    private QuestionEntity question;
}
