package com.kodigo.mvc_srn.controllers;

import com.kodigo.mvc_srn.exception.ResourceNotFoundException;
import com.kodigo.mvc_srn.models.Institute;
import com.kodigo.mvc_srn.repository.InstituteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.POST, RequestMethod.PUT})
@RequestMapping("/api/v1/institute")
public class InstituteController extends BaseController<Institute>{
    @Autowired
    private InstituteRepository instituteRepository;

    String notFound = "Institute not found on :: ";

    /**
     * Update institute entity
     * @param instituteId the institute id
     * @return the response entity
     * @throws ResourceNotFoundException the resource not found exception
     */
    @PutMapping("/{id}")
    public ResponseEntity<Institute> updateInstitute(
            @PathVariable(value = "id") Long instituteId, @Validated @RequestBody Institute institute)
        throws ResourceNotFoundException {
        Institute institute1 = instituteRepository.findById(instituteId)
                .orElseThrow(() -> new ResourceNotFoundException(notFound + instituteId));

        institute1.setInstitutionName(institute.getInstitutionName());
        final Institute updateInstitute = instituteRepository.save(institute1);
        return ResponseEntity.ok(updateInstitute);
    }

}
