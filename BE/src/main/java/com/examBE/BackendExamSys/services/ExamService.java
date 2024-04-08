package com.examBE.BackendExamSys.services;

import com.examBE.BackendExamSys.entities.ExamEntity;
import com.examBE.BackendExamSys.models.Dto.ExamQuestionDto;
import com.examBE.BackendExamSys.models.ExamDetailModel;
import com.examBE.BackendExamSys.utils.ErrorException;
import com.examBE.BackendExamSys.models.Dto.ExamDto;

import java.util.List;

public interface ExamService {
    List<ExamEntity> getAll();
    List<ExamDetailModel> getAllDetail();
    ExamEntity searchById (int id) throws ErrorException;
    ExamDetailModel searchDetailById(int id) throws ErrorException;
    void create(ExamDto examDto) throws ErrorException;
    void createAll(ExamQuestionDto examQuestionDto) throws ErrorException;
    void edit(ExamEntity examEntity) throws ErrorException;
    void editAll(ExamDetailModel examDetailModel) throws ErrorException;
    void delete(int id) throws ErrorException;
}
