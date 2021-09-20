package com.kodigo.mvc_srn.controllers;

import com.kodigo.mvc_srn.exception.ResourceNotFoundException;
import com.kodigo.mvc_srn.models.Subject;
import com.kodigo.mvc_srn.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class SubjectController {
    @Autowired
    private SubjectRepository subjectRepository;

    String notFound = "Subject not found on :: ";

    //Get all subjects
    @GetMapping("/subjects")
    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    /**
     * Gets subjects by id.
     *
     * @param subjectId the subject id
     * @return the subject by id
     * @throws ResourceNotFoundException the resource not found exception
     */
    @GetMapping("/subject/{id}")
    public ResponseEntity<Subject> getSubjectsById(@PathVariable(value = "id") Long subjectId)
            throws ResourceNotFoundException {
        Subject subject =
                subjectRepository
                        .findById(subjectId)
                        .orElseThrow(() -> new ResourceNotFoundException(notFound + subjectId));
        return ResponseEntity.ok().body(subject);
    }

    //Post new subjects
    @PostMapping("/subject")
    public Subject createSubject(@Validated @RequestBody Subject subject){
        return subjectRepository.save(subject);
    }

    /**
     * Update subject entity
     * @param subjectId the subject id
     * @return the response entity
     * @throws ResourceNotFoundException the resource not found exception
     */
    @PutMapping("/subject/{id}")
    public ResponseEntity<Subject> updateSubject(
            @PathVariable(value = "id") Long subjectId, @Validated @RequestBody Subject subject)
        throws ResourceNotFoundException {
        Subject subject1 = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new ResourceNotFoundException(notFound + subjectId));

        subject1.setTeacherId(subject.getTeacherId());
        subject1.setStudentId(subject.getStudentId());
        subject1.setNameSubject(subject.getNameSubject());
        subject1.setNameDate(subject.getNameDate());
        final Subject updateSubject = subjectRepository.save(subject1);
        return ResponseEntity.ok(updateSubject);
    }

    /**
     * Delete subject map.
     *
     * @param subjectId the subject id
     * @return the map
     */
    @DeleteMapping("/subject/{id}")
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
