package com.kodigo.mvc_srn.models;

import lombok.*;

import javax.persistence.*;

@Entity(name = "Note")
@Table
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Note {
    @Id
    @SequenceGenerator(
            name = "note_sequence",
            sequenceName = "note_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "note_sequence")
    @Column(updatable = false, nullable = false)
    private long idNotes;

    @Column(name = "subject_id", nullable = false)
    private long subjectId;

    @Column(name = "note_name", nullable = false)
    private String noteName;

    @Column(name = "note", nullable = false)
    private double noteDouble;
}
