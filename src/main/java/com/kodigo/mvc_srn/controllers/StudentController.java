package com.kodigo.mvc_srn.controllers;

import com.kodigo.mvc_srn.models.Student;
import com.kodigo.mvc_srn.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;

    String notFound = "Student not found on :: ";

    //Get all students
    @GetMapping("/students")
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    /**
     * Gets students by id.
     *
     * @param studentId the student id
     * @return the student by id
     * @throws ResourceAccessException the resource not found exception
     */
    @GetMapping("/student/{id}")
    public ResponseEntity<Student> getStudentsById(@PathVariable(value = "id") Long studentId)
            throws ResourceAccessException {
        Student student =
                studentRepository
                        .findById(studentId)
                        .orElseThrow(() -> new ResourceAccessException(notFound + studentId));
        return ResponseEntity.ok().body(student);
    }

    //Post new students
    @PostMapping("/student")
    public Student createStudent(@Validated @RequestBody Student student){
        return studentRepository.save(student);
    }

    /**
     * Update student entity
     * @param studentId the student id
     * @return the response entity
     * @throws ResourceAccessException the resource not found exception
     */
    @PutMapping("/student/{id}")
    public ResponseEntity<Student> updateStudent(
            @PathVariable(value = "id") Long studentId, @Validated @RequestBody Student student)
        throws ResourceAccessException {
        Student student1 = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceAccessException(notFound + studentId));

        student1.setInstitutionId(student.getInstitutionId());
        student1.setNameStudent(student.getNameStudent());
        student1.setLastnameStudent(student.getLastnameStudent());
        student1.setBirthdate(student.getBirthdate());
        final Student updateStudent = studentRepository.save(student1);
        return ResponseEntity.ok(updateStudent);
    }

    /**
     * Delete student map.
     *
     * @param studentId the student id
     * @return the map
     */
    @DeleteMapping("/student/{id}")
    public Map<String, Boolean> deleteStudent(@PathVariable(value = "id") Long studentId) {
        Student student =
                studentRepository
                        .findById(studentId)
                        .orElseThrow(() -> new ResourceAccessException(notFound + studentId));

        studentRepository.delete(student);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
