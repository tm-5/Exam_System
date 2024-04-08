package com.examBE.BackendExamSys.models.Dto;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAnswerDto {
    private int idContest;
    private List<AnswerQuestionDto> answerQuestion;
}
