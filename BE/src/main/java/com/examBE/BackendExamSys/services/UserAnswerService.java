package com.examBE.BackendExamSys.services;

import com.examBE.BackendExamSys.entities.UserAnswerEntity;
import com.examBE.BackendExamSys.models.Dto.UserAnswerDto;
import com.examBE.BackendExamSys.models.UserAnswerDetailModel;
import com.examBE.BackendExamSys.utils.ErrorException;

import java.util.List;

public interface UserAnswerService {
    List<UserAnswerEntity> getAll();
    UserAnswerEntity searchById (int id) throws ErrorException;
    UserAnswerDetailModel searchDetailByIdContest (int id) throws ErrorException;
    void start(UserAnswerEntity userAnswerEntity) throws ErrorException;
    void submit(UserAnswerDto userAnswerDto) throws ErrorException;
    void edit(UserAnswerEntity user) throws ErrorException;
    void delete(int id) throws ErrorException;
}
