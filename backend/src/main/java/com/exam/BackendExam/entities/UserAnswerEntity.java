package com.exam.BackendExam.entities;

import jakarta.persistence.*;

@Entity

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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdContest() {
		return idContest;
	}
	public void setIdContest(int idContest) {
		this.idContest = idContest;
	}
	public int getIdQuestion() {
		return idQuestion;
	}
	public void setIdQuestion(int idQuestion) {
		this.idQuestion = idQuestion;
	}
	public Integer getAnswer() {
		return answer;
	}
	public void setAnswer(Integer answer) {
		this.answer = answer;
	}
	public UserAnswerEntity(int id, int idContest, int idQuestion, Integer answer) {
		super();
		this.id = id;
		this.idContest = idContest;
		this.idQuestion = idQuestion;
		this.answer = answer;
	}
	public UserAnswerEntity() {
		super();
	}

}
