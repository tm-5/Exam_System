package com.exam.BackendExam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.exam.BackendExam.entities.*;

@Repository
public interface ExamRepository extends JpaRepository<ExamEntity, Integer> {
}
