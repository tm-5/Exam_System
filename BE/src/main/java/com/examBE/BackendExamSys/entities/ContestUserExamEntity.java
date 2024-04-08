package com.examBE.BackendExamSys.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "contest")
public class ContestUserExamEntity {
    @Id
    @Column(name = "id", nullable = false)
    private int id;

//    @Column(name = "score")
//    private Float score;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "submit_time")
    private LocalDateTime submitTime;

    @Column(name = "right_answer")
    private Integer rightAnswer;

    @Column(name = "wrong_answer")
    private Integer wrongAnswer;

    @Column(name = "blank_answer")
    private Integer blankAnswer;

    @OneToOne
    @JoinColumn(name="id_user")
    private UserOutEntity user;

    @OneToOne
    @JoinColumn(name="id_exam")
    private ExamEntity exam;

}
