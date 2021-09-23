package com.kodigo.mvc_srn.controllers;

import com.kodigo.mvc_srn.exception.ResourceNotFoundException;
import com.kodigo.mvc_srn.models.Degree;
import com.kodigo.mvc_srn.models.Student;
import com.kodigo.mvc_srn.models.Institute;
import com.kodigo.mvc_srn.repository.DegreeRepository;
import com.kodigo.mvc_srn.repository.InstituteRepository;
import com.kodigo.mvc_srn.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController extends BaseController<Student>{
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private InstituteRepository instituteRepository;

    @Autowired
    private DegreeRepository degreeRepository;

    String notFound = "Student not found on :: ";

    /**
     * Update student entity
     * @param studentId the student id
     * @return the response entity
     * @throws ResourceNotFoundException the resource not found exception
     */
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(
            @PathVariable(value = "id") Long studentId, @Validated @RequestBody Student student)
        throws ResourceNotFoundException {
        Student student1 = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException(notFound + studentId));

        student1.setNameStudent(student.getNameStudent());
        student1.setLastnameStudent(student.getLastnameStudent());
        student1.setBirthdate(student.getBirthdate());
        final Student updateStudent = studentRepository.save(student1);
        return ResponseEntity.ok(updateStudent);
    }

    @PutMapping("/{studentId}/institute/{instituteId}")
    public ResponseEntity<Student> addStudentToInstitute(
            @PathVariable Long studentId,
            @PathVariable Long instituteId
    ){
        Student student = studentRepository.findById(studentId).get();
        Institute institute = instituteRepository.findById(instituteId).get();
        student.setInstitute(institute);
        final Student updateStudent = studentRepository.save(student);
        return ResponseEntity.ok(updateStudent);
    }

    @PutMapping("/{studentId}/degree/{degreeId}")
    public ResponseEntity<Student> addStudentToDegree(
            @PathVariable Long studentId,
            @PathVariable Long degreeId
    ){
        Student student = studentRepository.findById(studentId).get();
        Degree degree = degreeRepository.findById(degreeId).get();
        student.setDegree(degree);
        final Student updateStudent = studentRepository.save(student);
        return ResponseEntity.ok(updateStudent);
    }

}
