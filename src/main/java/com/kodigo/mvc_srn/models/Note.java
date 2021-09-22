package com.kodigo.mvc_srn.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

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
    private long id;

    @JsonIgnore
    @OneToMany(mappedBy = "note")
    private Set<Subject> subjects;

    @Column(name = "note_name", nullable = false)
    private String noteName;

    @JsonIgnore
    @OneToMany(mappedBy = "note")
    private Set<Quiz> quizzes;

    @Column(name = "note", nullable = false)
    private double noteDouble;
}
