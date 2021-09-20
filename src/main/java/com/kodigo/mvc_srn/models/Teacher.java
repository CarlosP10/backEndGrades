package com.kodigo.mvc_srn.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "Teacher")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    private long idTeacher;

    @Column(name = "institution_id", nullable = false)
    private long institutionId;

    @Column(name = "name_teacher", nullable = false)
    private String nameTeacher;
}
