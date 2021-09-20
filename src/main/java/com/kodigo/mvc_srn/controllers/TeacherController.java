package com.kodigo.mvc_srn.controllers;

import com.kodigo.mvc_srn.exception.ResourceNotFoundException;
import com.kodigo.mvc_srn.models.Teacher;
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

    String notFound = "Teacher not found on :: ";

    //Get all teachers
    @GetMapping("/teachers")
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
    @GetMapping("/teacher/{id}")
    public ResponseEntity<Teacher> getTeachersById(@PathVariable(value = "id") Long teacherId)
            throws ResourceNotFoundException {
        Teacher teacher =
                teacherRepository
                        .findById(teacherId)
                        .orElseThrow(() -> new ResourceNotFoundException(notFound + teacherId));
        return ResponseEntity.ok().body(teacher);
    }

    //Post new teachers
    @PostMapping("/teacher")
    public Teacher createTeacher(@Validated @RequestBody Teacher teacher){
        return teacherRepository.save(teacher);
    }

    /**
     * Update teacher entity
     * @param teacherId the teacher id
     * @return the response entity
     * @throws ResourceNotFoundException the resource not found exception
     */
    @PutMapping("/teacher/{id}")
    public ResponseEntity<Teacher> updateTeacher(
            @PathVariable(value = "id") Long teacherId, @Validated @RequestBody Teacher teacher)
        throws ResourceNotFoundException {
        Teacher teacher1 = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new ResourceNotFoundException(notFound + teacherId));

        teacher1.setInstitutionId(teacher.getInstitutionId());
        teacher1.setNameTeacher(teacher.getNameTeacher());
        final Teacher updateTeacher = teacherRepository.save(teacher1);
        return ResponseEntity.ok(updateTeacher);
    }

    /**
     * Delete teacher map.
     *
     * @param teacherId the teacher id
     * @return the map
     */
    @DeleteMapping("/teacher/{id}")
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
