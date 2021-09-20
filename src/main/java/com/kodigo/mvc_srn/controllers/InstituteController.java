package com.kodigo.mvc_srn.controllers;

import com.kodigo.mvc_srn.models.Institute;
import com.kodigo.mvc_srn.repository.InstituteRepository;
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
public class InstituteController {
    @Autowired
    private InstituteRepository instituteRepository;

    String notFound = "Institute not found on :: ";

    //Get all institutes
    @GetMapping("/institutes")
    public List<Institute> getAllInstitutes() {
        return instituteRepository.findAll();
    }

    /**
     * Gets institutes by id.
     *
     * @param instituteId the institute id
     * @return the institute by id
     * @throws ResourceAccessException the resource not found exception
     */
    @GetMapping("/institute/{id}")
    public ResponseEntity<Institute> getInstitutesById(@PathVariable(value = "id") Long instituteId)
            throws ResourceAccessException {
        Institute institute =
                instituteRepository
                        .findById(instituteId)
                        .orElseThrow(() -> new ResourceAccessException(notFound + instituteId));
        return ResponseEntity.ok().body(institute);
    }

    //Post new institutes
    @PostMapping("/institute")
    public Institute createInstitute(@Validated @RequestBody Institute institute){
        return instituteRepository.save(institute);
    }

    /**
     * Update institute entity
     * @param instituteId the institute id
     * @return the response entity
     * @throws ResourceAccessException the resource not found exception
     */
    @PutMapping("/institute/{id}")
    public ResponseEntity<Institute> updateInstitute(
            @PathVariable(value = "id") Long instituteId, @Validated @RequestBody Institute institute)
        throws ResourceAccessException {
        Institute institute1 = instituteRepository.findById(instituteId)
                .orElseThrow(() -> new ResourceAccessException(notFound + instituteId));

        institute1.setInstitutionName(institute.getInstitutionName());
        final Institute updateInstitute = instituteRepository.save(institute1);
        return ResponseEntity.ok(updateInstitute);
    }

    /**
     * Delete institute map.
     *
     * @param instituteId the institute id
     * @return the map
     */
    @DeleteMapping("/institute/{id}")
    public Map<String, Boolean> deleteInstitute(@PathVariable(value = "id") Long instituteId) {
        Institute institute =
                instituteRepository
                        .findById(instituteId)
                        .orElseThrow(() -> new ResourceAccessException(notFound + instituteId));

        instituteRepository.delete(institute);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
