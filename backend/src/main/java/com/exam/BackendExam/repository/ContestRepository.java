package com.exam.BackendExam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.exam.BackendExam.entities.ContestEntity;

@Repository
public interface ContestRepository extends JpaRepository<ContestEntity, Integer> {
	

}
