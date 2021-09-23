package com.kodigo.mvc_srn.controllers;

import com.kodigo.mvc_srn.exception.ResourceNotFoundException;
import com.kodigo.mvc_srn.models.Degree;
import com.kodigo.mvc_srn.models.Teacher;
import com.kodigo.mvc_srn.repository.DegreeRepository;
import com.kodigo.mvc_srn.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/degree")
public class DegreeController extends BaseController<Degree>{
    @Autowired
    private DegreeRepository degreeRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    String notFound = "Degree not found on :: ";

    @PutMapping("/{id}")
    public ResponseEntity<Degree> updateDegree(
            @PathVariable(value = "id") Long degreeId, @Validated @RequestBody Degree degree)
        throws ResourceNotFoundException {
        Degree degree1 = degreeRepository.findById(degreeId)
                .orElseThrow(() -> new ResourceNotFoundException(notFound + degreeId));

        degree1.setNameDegree(degree.getNameDegree());
        final Degree updateDegree = degreeRepository.save(degree1);
        return ResponseEntity.ok(updateDegree);
    }

    @PutMapping("/{degreeId}/teacher/{teacherId}")
    public ResponseEntity<Degree> addDegreeToTeacher(
            @PathVariable Long degreeId,
            @PathVariable Long teacherId
    ){
        Degree degree = degreeRepository.findById(degreeId).get();
        Teacher teacher = teacherRepository.findById(teacherId).get();
        degree.setTeacher(teacher);

        final Degree updateDegree = degreeRepository.save(degree);
        return ResponseEntity.ok(updateDegree);
    }

}
