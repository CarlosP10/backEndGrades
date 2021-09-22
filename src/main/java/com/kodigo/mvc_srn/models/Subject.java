package com.kodigo.mvc_srn.models;

import lombok.*;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "Subject")
@Table
@Builder
@Getter @Setter
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
    private long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "teacher_id", referencedColumnName = "id")
    private Teacher teacher;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "note_id", referencedColumnName = "id")
    private Note note;


    @ManyToMany
    @JoinTable(
            name = "student_subject",
            joinColumns = @JoinColumn(name = "subject_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    public Set<Student> enrolledStudents = new HashSet<>();

    @Column(name = "subject_name", nullable = false)
    private String nameSubject;

}
