package com.examBE.BackendExamSys.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "create")
@Table(name = "contest", schema = "BackendExamSys")
public class ContestEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "id_user", nullable = false)
    private int idUser;
    @Basic
    @Column(name = "id_exam", nullable = false)
    private int idExam;

    @Basic
    @Column(name = "start_time")
    private LocalDateTime startTime;
    @Basic
    @Column(name = "submit_time")
    private LocalDateTime submitTime;
    @Basic
    @Column(name = "right_answer")
    private Integer rightAnswer;
    @Basic
    @Column(name = "wrong_answer")
    private Integer wrongAnswer;
    @Basic
    @Column(name = "blank_answer")
    private Integer blankAnswer;
}
