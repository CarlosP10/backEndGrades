package com.kodigo.mvc_srn.boostrap;

import com.kodigo.mvc_srn.models.Note;
import com.kodigo.mvc_srn.repository.NoteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class NotesLoader implements CommandLineRunner {
    public final NoteRepository notesRepository;

    public NotesLoader(NoteRepository notesRepository) {
        this.notesRepository = notesRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadNotes();
    }

    private void loadNotes() {
        if (notesRepository.count() == 0) {
            notesRepository.save(
                    Note.builder()
                            .noteName("Primer examen")
                            .noteDouble(7.6)
                            .build()
            );
            notesRepository.save(
                    Note.builder()
                            .noteName("Segundo examen")
                            .noteDouble(8.6)
                            .build()
            );
            notesRepository.save(
                    Note.builder()
                            .noteName("Tercer examen")
                            .noteDouble(9.6)
                            .build()
            );
            System.out.println("Ejemplos cargados");
        }
    }
}
