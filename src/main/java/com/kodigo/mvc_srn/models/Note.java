package com.kodigo.mvc_srn.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "Note")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    private long idNotes;

    @Column(name = "subject_id", nullable = false)
    private long subjectId;

    @Column(name = "note_name", nullable = false)
    private String noteName;

    @Column(name = "note", nullable = false)
    private double noteDouble;
}
