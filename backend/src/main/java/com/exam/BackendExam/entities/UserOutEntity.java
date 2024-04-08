package com.exam.BackendExam.entities;

import jakarta.persistence.*;

@Entity

@Table(name = "users", schema = "BackendExamSys")
public class UserOutEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "name", nullable = false, length = 20)
    private String name;
    @Basic
    @Column(name = "gender", nullable = false)
    private Boolean gender;
    @Basic
    @Column(name = "email", nullable = false, length = 50)
    private String email;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Boolean getGender() {
		return gender;
	}
	public void setGender(Boolean gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public UserOutEntity(int id, String name, Boolean gender, String email) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.email = email;
	}
	public UserOutEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
    
}