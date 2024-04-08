package com.examBE.BackendExamSys.repositories;

import com.examBE.BackendExamSys.entities.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRep extends JpaRepository<QuestionEntity, Integer> {
    boolean existsById(int id);
    QuestionEntity findFirstById(int id);
    List<QuestionEntity> findByIdExam(int id);

}
