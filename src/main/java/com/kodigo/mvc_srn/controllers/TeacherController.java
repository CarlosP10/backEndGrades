package com.kodigo.mvc_srn.controllers;

import com.kodigo.mvc_srn.exception.ResourceNotFoundException;
import com.kodigo.mvc_srn.models.Institute;
import com.kodigo.mvc_srn.models.Teacher;
import com.kodigo.mvc_srn.models.Teacher;
import com.kodigo.mvc_srn.repository.InstituteRepository;
import com.kodigo.mvc_srn.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/teacher")
public class TeacherController {
    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private InstituteRepository instituteRepository;

    String notFound = "Teacher not found on :: ";

    //Get all teachers
    @GetMapping("/all")
    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    /**
     * Gets teachers by id.
     *
     * @param teacherId the teacher id
     * @return the teacher by id
     * @throws ResourceNotFoundException the resource not found exception
     */
    @GetMapping("/{id}")
    public ResponseEntity<Teacher> getTeachersById(@PathVariable(value = "id") Long teacherId)
            throws ResourceNotFoundException {
        Teacher teacher =
                teacherRepository
                        .findById(teacherId)
                        .orElseThrow(() -> new ResourceNotFoundException(notFound + teacherId));
        return ResponseEntity.ok().body(teacher);
    }

    //Post new teachers
    @PostMapping("/")
    public Teacher createTeacher(@Validated @RequestBody Teacher teacher){
        return teacherRepository.save(teacher);
    }

    /**
     * Update teacher entity
     * @param teacherId the teacher id
     * @return the response entity
     * @throws ResourceNotFoundException the resource not found exception
     */
    @PutMapping("/{id}")
    public ResponseEntity<Teacher> updateTeacher(
            @PathVariable(value = "id") Long teacherId, @Validated @RequestBody Teacher teacher)
        throws ResourceNotFoundException {
        Teacher teacher1 = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new ResourceNotFoundException(notFound + teacherId));

        teacher1.setNameTeacher(teacher.getNameTeacher());
        final Teacher updateTeacher = teacherRepository.save(teacher1);
        return ResponseEntity.ok(updateTeacher);
    }

    @PutMapping("/{teacherId}/institute/{instituteId}")
    public ResponseEntity<Teacher> addStudentToInstitute(
            @PathVariable Long teacherId,
            @PathVariable Long instituteId
    ){
        Teacher teacher = teacherRepository.findById(teacherId).get();
        Institute institute = instituteRepository.findById(instituteId).get();
        teacher.setInstitute(institute);
        final Teacher updateStudent = teacherRepository.save(teacher);
        return ResponseEntity.ok(updateStudent);
    }

    /**
     * Delete teacher map.
     *
     * @param teacherId the teacher id
     * @return the map
     */
    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteTeacher(@PathVariable(value = "id") Long teacherId) throws Exception {
        Teacher teacher =
                teacherRepository
                        .findById(teacherId)
                        .orElseThrow(() -> new ResourceNotFoundException(notFound + teacherId));

        teacherRepository.delete(teacher);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
