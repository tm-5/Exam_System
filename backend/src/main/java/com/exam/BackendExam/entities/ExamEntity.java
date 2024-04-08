package com.exam.BackendExam.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity

@Table(name = "exams", schema = "BackendExamSys")
public class ExamEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "name", nullable = false, length = 100)
    private String name;
    @Basic
    @Column(name = "description", length = 100)
    private String description;
    @Basic
    @Column(name = "type", nullable = false)
    private Boolean type;
    @Basic
    @Column(name = "start_time")
    private LocalDateTime startTime;
    @Basic
    @Column(name = "end_time")
    private LocalDateTime endTime;
    @Basic
    @Column(name = "time_duration")
    private Integer timeDuration;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Boolean getType() {
		return type;
	}
	public void setType(Boolean type) {
		this.type = type;
	}
	public LocalDateTime getStartTime() {
		return startTime;
	}
	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}
	public LocalDateTime getEndTime() {
		return endTime;
	}
	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}
	public Integer getTimeDuration() {
		return timeDuration;
	}
	public void setTimeDuration(Integer timeDuration) {
		this.timeDuration = timeDuration;
	}
	public ExamEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ExamEntity(int id, String name, String description, Boolean type, LocalDateTime startTime,
			LocalDateTime endTime, Integer timeDuration) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.type = type;
		this.startTime = startTime;
		this.endTime = endTime;
		this.timeDuration = timeDuration;
	}
    
}
