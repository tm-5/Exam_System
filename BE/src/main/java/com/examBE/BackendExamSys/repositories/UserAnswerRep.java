package com.examBE.BackendExamSys.repositories;

import com.examBE.BackendExamSys.entities.UserAnswerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAnswerRep extends JpaRepository<UserAnswerEntity, Integer> {
    boolean existsById(int id);
    UserAnswerEntity findFirstById(int id);
    List<UserAnswerEntity> findByIdContest(int id);
    List<UserAnswerEntity> findByIdQuestion(int id);
}
