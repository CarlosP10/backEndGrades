package com.kodigo.mvc_srn.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

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
    private long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "teacher_id", referencedColumnName = "id")
    private Teacher teacher;

    @JsonIgnore
    @OneToMany(mappedBy = "degree")
    private Set<Student> students;

    @Column(name = "degree_name", nullable = false)
    private String nameDegree;
}
