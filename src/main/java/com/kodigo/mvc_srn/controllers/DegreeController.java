package com.kodigo.mvc_srn.controllers;

import com.kodigo.mvc_srn.exception.ResourceNotFoundException;
import com.kodigo.mvc_srn.models.Degree;
import com.kodigo.mvc_srn.repository.DegreeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class DegreeController {
    @Autowired
    private DegreeRepository degreeRepository;

    String notFound = "Degree not found on :: ";

    //Get all degrees
    @GetMapping("/degrees")
    public List<Degree> getAllDegrees() {
        return degreeRepository.findAll();
    }

    /**
     * Gets degrees by id.
     *
     * @param degreeId the degree id
     * @return the degree by id
     * @throws ResourceNotFoundException the resource not found exception
     */
    @GetMapping("/degree/{id}")
    public ResponseEntity<Degree> getDegreesById(@PathVariable(value = "id") Long degreeId)
            throws ResourceNotFoundException {
        Degree degree =
                degreeRepository
                        .findById(degreeId)
                        .orElseThrow(() -> new ResourceNotFoundException(notFound + degreeId));
        return ResponseEntity.ok().body(degree);
    }

    //Post new degrees
    @PostMapping("/degree")
    public Degree createDegree(@Validated @RequestBody Degree degree){
        return degreeRepository.save(degree);
    }

    /**
     * Update degree entity
     * @param degreeId the degree id
     * @return the response entity
     * @throws ResourceNotFoundException the resource not found exception
     */
    @PutMapping("/degree/{id}")
    public ResponseEntity<Degree> updateDegree(
            @PathVariable(value = "id") Long degreeId, @Validated @RequestBody Degree degree)
        throws ResourceNotFoundException {
        Degree degree1 = degreeRepository.findById(degreeId)
                .orElseThrow(() -> new ResourceNotFoundException(notFound + degreeId));

        degree1.setTeacherId(degree.getTeacherId());
        degree1.setStudentId(degree.getStudentId());
        degree1.setSubjectId(degree.getSubjectId());
        degree1.setNameDegree(degree.getNameDegree());
        final Degree updateDegree = degreeRepository.save(degree1);
        return ResponseEntity.ok(updateDegree);
    }

    /**
     * Delete degree map.
     *
     * @param degreeId the degree id
     * @return the map
     */
    @DeleteMapping("/degree/{id}")
    public Map<String, Boolean> deleteDegree(@PathVariable(value = "id") Long degreeId) throws Exception {
        Degree degree =
                degreeRepository
                        .findById(degreeId)
                        .orElseThrow(() -> new ResourceNotFoundException(notFound + degreeId));

        degreeRepository.delete(degree);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
