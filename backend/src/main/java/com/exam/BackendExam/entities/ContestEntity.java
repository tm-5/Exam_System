package com.exam.BackendExam.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public int getIdExam() {
		return idExam;
	}
	public void setIdExam(int idExam) {
		this.idExam = idExam;
	}
	public LocalDateTime getStartTime() {
		return startTime;
	}
	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}
	public LocalDateTime getSubmitTime() {
		return submitTime;
	}
	public void setSubmitTime(LocalDateTime submitTime) {
		this.submitTime = submitTime;
	}
	public Integer getRightAnswer() {
		return rightAnswer;
	}
	public void setRightAnswer(Integer rightAnswer) {
		this.rightAnswer = rightAnswer;
	}
	public Integer getWrongAnswer() {
		return wrongAnswer;
	}
	public void setWrongAnswer(Integer wrongAnswer) {
		this.wrongAnswer = wrongAnswer;
	}
	public Integer getBlankAnswer() {
		return blankAnswer;
	}
	public void setBlankAnswer(Integer blankAnswer) {
		this.blankAnswer = blankAnswer;
	}
	public ContestEntity(int id, int idUser, int idExam, LocalDateTime startTime, LocalDateTime submitTime,
			Integer rightAnswer, Integer wrongAnswer, Integer blankAnswer) {
		super();
		this.id = id;
		this.idUser = idUser;
		this.idExam = idExam;
		this.startTime = startTime;
		this.submitTime = submitTime;
		this.rightAnswer = rightAnswer;
		this.wrongAnswer = wrongAnswer;
		this.blankAnswer = blankAnswer;
	}
	public ContestEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
    
}
