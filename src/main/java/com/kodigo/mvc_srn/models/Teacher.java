package com.kodigo.mvc_srn.models;

import lombok.*;

import javax.persistence.*;

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
    private long idTeacher;

    @Column(name = "institution_id", nullable = false)
    private long institutionId;

    @Column(name = "name_teacher", nullable = false)
    private String nameTeacher;
}
