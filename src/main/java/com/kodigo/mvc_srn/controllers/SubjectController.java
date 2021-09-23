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
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/subject")
public class SubjectController extends BaseController<Subject>{
    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private NoteRepository noteRepository;

    String notFound = "Subject not found on :: ";

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

}
