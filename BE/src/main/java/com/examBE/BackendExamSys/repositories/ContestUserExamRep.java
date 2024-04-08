package com.examBE.BackendExamSys.repositories;

import com.examBE.BackendExamSys.entities.ContestUserExamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ContestUserExamRep extends JpaRepository<ContestUserExamEntity, Integer> {
    //lay theo user
    @Query("SELECT c.user.id, c.user.name, sum(c.rightAnswer), sum(c.wrongAnswer), sum(c.blankAnswer), count(c.submitTime), count(c.user.id) from ContestUserExamEntity c group by c.user.id")
    List<Object[]> statisticByUser();

    //lay theo exam
    @Query("SELECT c.exam.id, sum(c.rightAnswer), sum(c.wrongAnswer), sum(c.blankAnswer), count(c.submitTime), count(c.exam.id) from ContestUserExamEntity c group by c.exam.id")
    List<Object[]> statisticByExam();

}
