package com.kodigo.mvc_srn.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "Teacher")
@Table
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {
    @Id
    @SequenceGenerator(
            name = "teacher_sequence",
            sequenceName = "teacher_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "teacher_sequence")
    @Column(updatable = false, nullable = false)
    private long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "institute_id", referencedColumnName = "id")
    private Institute institute;

    @JsonIgnore
    @OneToMany(mappedBy = "teacher")
    private Set<Degree> degrees;

    @JsonIgnore
    @OneToMany(mappedBy = "teacher")
    private Set<Subject> subjects;

    @Column(name = "name_teacher", nullable = false)
    private String nameTeacher;
}
