package com.kodigo.mvc_srn.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Note")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idNotes;

    @Column(name = "subject_id", nullable = false)
    private long subjectId;

    @Column(name = "note_name", nullable = false)
    private String noteName;

    @Column(name = "note", nullable = false)
    private double noteDouble;
}
