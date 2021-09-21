package com.kodigo.mvc_srn.models;

import lombok.*;

import javax.persistence.*;

@Entity(name = "Subject")
@Table
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Subject {
    @Id
    @SequenceGenerator(
            name = "subject_sequence",
            sequenceName = "subject_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "subject_sequence")
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
