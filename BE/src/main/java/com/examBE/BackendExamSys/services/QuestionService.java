package com.examBE.BackendExamSys.services;

import com.examBE.BackendExamSys.entities.QuestionEntity;
import com.examBE.BackendExamSys.models.Dto.QuestionDto;
import com.examBE.BackendExamSys.utils.ErrorException;

import java.util.List;

public interface QuestionService {
    List<QuestionEntity> getAll();
    QuestionEntity searchById (int id) throws ErrorException;
    void create(QuestionDto question) throws ErrorException;
    void edit(QuestionEntity question) throws ErrorException;
    void delete(int id) throws ErrorException;
}
