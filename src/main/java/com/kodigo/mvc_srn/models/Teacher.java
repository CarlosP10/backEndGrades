package com.kodigo.mvc_srn.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Teacher")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idTeacher;

    @Column(name = "institution_id", nullable = false)
    private long institutionId;

    @Column(name = "name_teacher", nullable = false)
    private String nameTeacher;
}
