package com.kodigo.mvc_srn.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "Institute")
@Table
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class Institute {
    @Id
    @SequenceGenerator(
            name = "institute_sequence",
            sequenceName = "institute_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "institute_sequence")
    @Column(updatable = false, nullable = false)
    private long id;

    @JsonIgnore
    @OneToMany(mappedBy = "institute")
    private Set<Teacher> teachers;

    @JsonIgnore
    @OneToMany(mappedBy = "institute")
    private Set<Student> students;

    @Column(name = "institution_name", nullable = false)
    private String institutionName;
}
