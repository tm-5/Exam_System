package com.examBE.BackendExamSys.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "create")
@Table(name = "questions", schema = "BackendExamSys")
public class QuestionEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "id_exam", nullable = false)
    private int idExam;
    @Basic
    @Column(name = "content", nullable = false)
    private String content;
    @Basic
    @Column(name = "answer1", nullable = false)
    private String answer1;
    @Basic
    @Column(name = "answer2", nullable = false)
    private String answer2;
    @Basic
    @Column(name = "answer3", nullable = false)
    private String answer3;
    @Basic
    @Column(name = "answer4", nullable = false)
    private String answer4;
    @Basic
    @Column(name = "right_answer", nullable = false)
    private int rightAnswer;
    @Basic
    @Column(name = "explains")
    private String explain;
}
