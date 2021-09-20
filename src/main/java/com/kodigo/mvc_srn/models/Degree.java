package com.kodigo.mvc_srn.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Degree")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Degree {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idDegree;

    @Column(name = "teacher_id", nullable = false)
    private long teacherId;

    @Column(name = "student_id", nullable = false)
    private long studentId;

    @Column(name = "subject_id", nullable = false)
    private long subjectId;

    @Column(name = "degree_name", nullable = false)
    private String nameDegree;
}
