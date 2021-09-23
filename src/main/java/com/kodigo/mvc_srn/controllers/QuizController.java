package com.kodigo.mvc_srn.controllers;

import com.kodigo.mvc_srn.exception.ResourceNotFoundException;
import com.kodigo.mvc_srn.models.Note;
import com.kodigo.mvc_srn.models.Quiz;
import com.kodigo.mvc_srn.models.Student;
import com.kodigo.mvc_srn.repository.NoteRepository;
import com.kodigo.mvc_srn.repository.QuizRepository;
import com.kodigo.mvc_srn.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/quiz")
public class QuizController extends BaseController<Quiz>{
    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private NoteRepository noteRepository;
    String notFound = "Quiz not found on :: ";

    @PutMapping("/{quizId}/students/{studentId}")
    public ResponseEntity<Quiz> addStudentToQuiz(
            @PathVariable Long quizId,
            @PathVariable Long studentId
    ){
        Quiz quiz = quizRepository.findById(quizId).get();
        Student student = studentRepository.findById(studentId).get();
        quiz.enrolledStudentsQuiz.add(student);

        final Quiz updateQuiz = quizRepository.save(quiz);
        return ResponseEntity.ok(updateQuiz);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Quiz> updateQuiz(
            @PathVariable Long id, @RequestBody Quiz quiz
    ){
        Quiz quiz1 = quizRepository.findById(id).get();
        quiz1.setQuizName(quiz.getQuizName());
        quiz1.setQuizNote(quiz.getQuizNote());
        final Quiz updateQuiz = quizRepository.save(quiz1);
        return ResponseEntity.ok(updateQuiz);
    }

    @PutMapping("/{quizId}/note/{noteId}")
    public ResponseEntity<Quiz> addQuizToNote(
            @PathVariable Long quizId,
            @PathVariable Long noteId
    ){
        Quiz quiz = quizRepository.findById(quizId).get();
        Note note = noteRepository.findById(noteId).get();
        quiz.setNote(note);

        final Quiz updateQuiz = quizRepository.save(quiz);
        return ResponseEntity.ok(updateQuiz);
    }

}
