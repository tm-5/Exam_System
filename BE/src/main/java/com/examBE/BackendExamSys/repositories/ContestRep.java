package com.examBE.BackendExamSys.repositories;

import com.examBE.BackendExamSys.entities.ContestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContestRep extends JpaRepository<ContestEntity, Integer> {
    boolean existsById(int id);
    ContestEntity findFirstById(int id);
    List<ContestEntity> findByIdUser(int id);
    List<ContestEntity> findByIdExam(int id);
}
