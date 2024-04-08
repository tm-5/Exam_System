package com.examBE.BackendExamSys.models.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnswerQuestionDto {
    private int idQuestion;
    private Integer answer;
}
