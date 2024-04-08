package com.exam.BackendExam.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity

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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public UserOutEntity getUser() {
		return user;
	}

	public void setUser(UserOutEntity user) {
		this.user = user;
	}

	public ExamEntity getExam() {
		return exam;
	}

	public void setExam(ExamEntity exam) {
		this.exam = exam;
	}

	public ContestUserExamEntity(int id, LocalDateTime startTime, LocalDateTime submitTime, Integer rightAnswer,
			Integer wrongAnswer, Integer blankAnswer, UserOutEntity user, ExamEntity exam) {
		super();
		this.id = id;
		this.startTime = startTime;
		this.submitTime = submitTime;
		this.rightAnswer = rightAnswer;
		this.wrongAnswer = wrongAnswer;
		this.blankAnswer = blankAnswer;
		this.user = user;
		this.exam = exam;
	}

	public ContestUserExamEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

}
