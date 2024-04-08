package com.exam.BackendExam.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity

@Table(name = "questions", schema = "BackendExamSys")
public class QuestionEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "id_exam", nullable = true)
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
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public int getIdExam() {
		return idExam;
	}
	public void setIdExam(int idExam) {
		this.idExam = idExam;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAnswer1() {
		return answer1;
	}
	public void setAnswer1(String answer1) {
		this.answer1 = answer1;
	}
	public String getAnswer2() {
		return answer2;
	}
	public void setAnswer2(String answer2) {
		this.answer2 = answer2;
	}
	public String getAnswer3() {
		return answer3;
	}
	public void setAnswer3(String answer3) {
		this.answer3 = answer3;
	}
	public String getAnswer4() {
		return answer4;
	}
	public void setAnswer4(String answer4) {
		this.answer4 = answer4;
	}
	public int getRightAnswer() {
		return rightAnswer;
	}
	public void setRightAnswer(int rightAnswer) {
		this.rightAnswer = rightAnswer;
	}
	public String getExplain() {
		return explain;
	}
	public void setExplain(String explain) {
		this.explain = explain;
	}
	public QuestionEntity(Integer id, int idExam, String content, String answer1, String answer2, String answer3,
			String answer4, int rightAnswer, String explain) {
		super();
		this.id = id;
		this.idExam = idExam;
		this.content = content;
		this.answer1 = answer1;
		this.answer2 = answer2;
		this.answer3 = answer3;
		this.answer4 = answer4;
		this.rightAnswer = rightAnswer;
		this.explain = explain;
	}
	public QuestionEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
    
}
