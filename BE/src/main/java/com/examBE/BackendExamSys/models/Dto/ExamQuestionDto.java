package com.examBE.BackendExamSys.models.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExamQuestionDto {
    private String name;
    private String description;
    private Boolean type;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Integer timeDuration;
    private List<QuestionDto> question;
}
