package com.kodigo.mvc_srn.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Student")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
