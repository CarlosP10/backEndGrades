package com.kodigo.mvc_srn.models;

import lombok.*;

import javax.persistence.*;

@Entity(name = "Student")
@Table
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_sequence")
    @Column(updatable = false, nullable = false)
    private long id;

    @Column(name = "institution_id", nullable = false)
    private long institutionId;

    @Column(name = "name_student", nullable = false)
    private String nameStudent;

    @Column(name = "lastname_student", nullable = false)
    private String lastnameStudent;

    @Column(name = "birthdate", nullable = false)
    private String birthdate;
}
