package com.examBE.BackendExamSys.repositories;

import com.examBE.BackendExamSys.entities.ExamEntity;
import com.examBE.BackendExamSys.models.ExamDetailModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ExamRep extends JpaRepository<ExamEntity, Integer> {
    boolean existsById(int id);
    ExamEntity findFirstById(int id);
    ExamEntity findFirstByStartTime(Date startTime);

}
