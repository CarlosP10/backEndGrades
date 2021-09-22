package com.kodigo.mvc_srn.controllers;

import com.kodigo.mvc_srn.exception.ResourceNotFoundException;
import com.kodigo.mvc_srn.models.Note;
import com.kodigo.mvc_srn.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/note")
public class NoteController {
    @Autowired
    private NoteRepository noteRepository;

    String notFound = "Note not found on :: ";

    //Get all notes
    @GetMapping("/all")
    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    /**
     * Gets notes by id.
     *
     * @param noteId the note id
     * @return the note by id
     * @throws ResourceNotFoundException the resource not found exception
     */
    @GetMapping("/{id}")
    public ResponseEntity<Note> getNotesById(@PathVariable(value = "id") Long noteId)
            throws ResourceNotFoundException {
        Note note =
                noteRepository
                        .findById(noteId)
                        .orElseThrow(() -> new ResourceNotFoundException(notFound + noteId));
        return ResponseEntity.ok().body(note);
    }

    //Post new notes
    @PostMapping("/")
    public Note createNote(@Validated @RequestBody Note note){
        return noteRepository.save(note);
    }

    /**
     * Update note entity
     * @param noteId the note id
     * @return the response entity
     * @throws ResourceNotFoundException the resource not found exception
     */
    @PutMapping("/{id}")
    public ResponseEntity<Note> updateNote(
            @PathVariable(value = "id") Long noteId, @Validated @RequestBody Note note)
        throws ResourceNotFoundException {
        Note note1 = noteRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException(notFound + noteId));

        note1.setNoteName(note.getNoteName());
        note1.setNoteDouble(note.getNoteDouble());
        final Note updateNote = noteRepository.save(note1);
        return ResponseEntity.ok(updateNote);
    }

    /**
     * Delete note map.
     *
     * @param noteId the note id
     * @return the map
     */
    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteNote(@PathVariable(value = "id") Long noteId) throws Exception {
        Note note =
                noteRepository
                        .findById(noteId)
                        .orElseThrow(() -> new ResourceNotFoundException(notFound + noteId));

        noteRepository.delete(note);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
