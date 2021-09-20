package com.kodigo.mvc_srn.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "Subject")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
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
