package com.kodigo.mvc_srn.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Subject")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idSubject;

    @Column(name = "teacher_id", nullable = false)
    private long teacherId;

    @Column(name = "student_id", nullable = false)
    private long studentId;

    @Column(name = "subject_name", nullable = false)
    private String nameSubject;

    @Column(name = "subject_date", nullable = false)
    private String nameDate;
}
