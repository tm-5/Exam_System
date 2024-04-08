package com.examBE.BackendExamSys.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "create")
@Table(name = "user_answers", schema = "BackendExamSys")
public class UserAnswerEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "id_contest", nullable = false)
    private int idContest;
    @Basic
    @Column(name = "id_question", nullable = false)
    private int idQuestion;
    @Basic
    @Column(name = "answer")
    private Integer answer;

}
