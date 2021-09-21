package com.kodigo.mvc_srn.models;

import lombok.*;

import javax.persistence.*;

@Entity(name = "Degree")
@Table
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Degree {
    @Id
    @SequenceGenerator(
            name = "degree_sequence",
            sequenceName = "degree_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "degree_sequence")
    @Column(updatable = false, nullable = false)
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
