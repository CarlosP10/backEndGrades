package com.kodigo.mvc_srn.models;

import lombok.*;

import javax.persistence.*;

@Entity(name = "Institution")
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

    @Column(name = "institution_name", nullable = false)
    private String institutionName;
}
