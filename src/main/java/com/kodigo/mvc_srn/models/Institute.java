package com.kodigo.mvc_srn.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Institution")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Institute {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "institution_name", nullable = false)
    private String institutionName;
}
