package com.kodigo.mvc_srn.controllers;

import com.kodigo.mvc_srn.exception.ResourceNotFoundException;
import com.kodigo.mvc_srn.models.*;
import com.kodigo.mvc_srn.models.Subject;
import com.kodigo.mvc_srn.repository.NoteRepository;
import com.kodigo.mvc_srn.repository.StudentRepository;
import com.kodigo.mvc_srn.repository.SubjectRepository;
import com.kodigo.mvc_srn.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/subject")
public class SubjectController {
    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private NoteRepository noteRepository;

    String notFound = "Subject not found on :: ";

    //Get all subjects
    @GetMapping("/all")
    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Subject> getSubjectsById(@PathVariable(value = "id") Long subjectId)
            throws ResourceNotFoundException {
        Subject subject =
                subjectRepository
                        .findById(subjectId)
                        .orElseThrow(() -> new ResourceNotFoundException(notFound + subjectId));
        return ResponseEntity.ok().body(subject);
    }

    //Post new subjects
    @PostMapping("/")
    public Subject createSubject(@RequestBody Subject subject){
        return subjectRepository.save(subject);
    }

    @PutMapping("/{subjectId}/students/{studentId}")
    public ResponseEntity<Subject> addStudentToSubject(
            @PathVariable Long subjectId,
            @PathVariable Long studentId
    ){
        Subject subject = subjectRepository.findById(subjectId).get();
        Student student = studentRepository.findById(studentId).get();
        subject.enrolledStudents.add(student);
        final Subject updateSubject = subjectRepository.save(subject);
        return ResponseEntity.ok(updateSubject);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Subject> updateSubject(
            @PathVariable Long id, @RequestBody Subject subject
    )throws ResourceNotFoundException {
        Subject subject1 = subjectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(notFound + id));
        subject1.setNameSubject(subject.getNameSubject());
        final Subject updateSubject = subjectRepository.save(subject1);
        return ResponseEntity.ok(updateSubject);
    }

    @PutMapping("/{subjectId}/teacher/{teacherId}")
    public ResponseEntity<Subject> assignTeacherToSubject(
            @PathVariable Long subjectId,
            @PathVariable Long teacherId
    ){
        Subject subject = subjectRepository.findById(subjectId).get();
        Teacher teacher = teacherRepository.findById(teacherId).get();
        subject.setTeacher(teacher);
        final Subject updateSubject = subjectRepository.save(subject);
        return ResponseEntity.ok(updateSubject);
    }

    @PutMapping("/{subjectId}/note/{noteId}")
    public ResponseEntity<Subject> assignNoteToSubject(
            @PathVariable Long subjectId,
            @PathVariable Long noteId
    ){
        Subject subject = subjectRepository.findById(subjectId).get();
        Note note = noteRepository.findById(noteId).get();
        subject.setNote(note);
        final Subject updateSubject = subjectRepository.save(subject);
        return ResponseEntity.ok(updateSubject);
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteSubject(@PathVariable(value = "id") Long subjectId) throws Exception {
        Subject subject =
                subjectRepository
                        .findById(subjectId)
                        .orElseThrow(() -> new ResourceNotFoundException(notFound + subjectId));

        subjectRepository.delete(subject);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
