package com.examBE.BackendExamSys.repositories;

import com.examBE.BackendExamSys.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRep extends JpaRepository<UserEntity, Integer> {
    List<UserEntity> findAllByRoles(String role);
    boolean existsById(int id);
    boolean existsByEmail(String email);
    UserEntity findFirstById(int id);
    UserEntity findFirstByEmail(String email);
    Optional<UserEntity> findByEmail(String email);

}
