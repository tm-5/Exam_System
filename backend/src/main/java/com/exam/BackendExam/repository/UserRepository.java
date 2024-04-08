package com.exam.BackendExam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.BackendExam.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity,Integer>{
	UserEntity findByEmail(String email);

}
