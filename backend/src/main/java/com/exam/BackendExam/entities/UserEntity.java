package com.exam.BackendExam.entities;

import jakarta.persistence.*;

@Entity

@Table(name = "users", schema = "BackendExamSys")
public class UserEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "name", nullable = false)
    private String name;
    @Basic
    @Column(name = "gender", nullable = false)
    private Boolean gender;
    @Basic
    @Column(name = "email", nullable = false, length = 50)
    private String email;
    @Basic
    @Column(name = "password", nullable = false, length = 2147483647)
    private String password;
    @Basic
    @Column(name = "roles", length = 50)
    private String roles;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
	public UserEntity(int id, String name, Boolean gender, String email, String password, String roles) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.email = email;
		this.password = password;
		this.roles = roles;
	}
	public UserEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
    
}
