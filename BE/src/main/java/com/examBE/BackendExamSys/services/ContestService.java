package com.examBE.BackendExamSys.services;

import com.examBE.BackendExamSys.entities.ContestEntity;
import com.examBE.BackendExamSys.models.ContestExamModel;
import com.examBE.BackendExamSys.models.ContestUserModel;
import com.examBE.BackendExamSys.models.Dto.ContestDto;
import com.examBE.BackendExamSys.models.ContestDetailModel;
import com.examBE.BackendExamSys.utils.ErrorException;

import java.util.List;

public interface ContestService {
    List<ContestEntity> getAll();
    ContestEntity searchById (int id) throws ErrorException;
    ContestDetailModel searchDetailById(int id) throws ErrorException;
    List<ContestDetailModel>  searchDetailByIdUser (int id) throws ErrorException;
    List<ContestEntity> searchByIdExam(int id) throws ErrorException;
    void create(ContestDto newContest) throws ErrorException;
    void edit(ContestEntity newContest) throws ErrorException;
    void delete(int id) throws ErrorException;

    List<ContestUserModel> findWithUser();
    List<ContestExamModel> findWithExam();


}
