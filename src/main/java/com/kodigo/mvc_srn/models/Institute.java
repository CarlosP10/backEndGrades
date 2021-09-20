package com.kodigo.mvc_srn.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "Institution")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class Institute {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    private long id;

    @Column(name = "institution_name", nullable = false)
    private String institutionName;
}
