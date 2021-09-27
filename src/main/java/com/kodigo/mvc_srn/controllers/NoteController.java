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
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.POST, RequestMethod.PUT})
@RequestMapping("/api/v1/note")
public class NoteController extends BaseController<Note>{
    @Autowired
    private NoteRepository noteRepository;

    String notFound = "Note not found on :: ";

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

}
