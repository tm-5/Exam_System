package com.examBE.BackendExamSys.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "create")
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
}
