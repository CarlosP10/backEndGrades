package com.kodigo.mvc_srn.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "institute_id", referencedColumnName = "id")
    private Institute institute;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "degree_id", referencedColumnName = "id")
    private Degree degree;

    @JsonIgnore
    @ManyToMany(mappedBy = "enrolledStudents")
    private Set<Subject> subjects = new HashSet<>();


    @ManyToMany(mappedBy = "enrolledStudentsQuiz")
    private Set<Quiz> quizzes = new HashSet<>();

    @Column(name = "name_student", nullable = false)
    private String nameStudent;

    @Column(name = "lastname_student", nullable = false)
    private String lastnameStudent;

    @Column(name = "birthdate", nullable = false)
    private String birthdate;
}
