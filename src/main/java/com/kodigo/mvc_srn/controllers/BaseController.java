package com.kodigo.mvc_srn.controllers;

import com.kodigo.mvc_srn.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class BaseController<T> {
    @Autowired
    private JpaRepository<T, Long> repository;

    String notFound = "T not found on :: ";

    //Get all ts
    @GetMapping("/all")
    public List<T> getAllTs() {
        return repository.findAll();
    }

    /**
     * Gets ts by id.
     *
     * @param tId the t id
     * @return the t by id
     * @throws ResourceNotFoundException the resource not found exception
     */
    @GetMapping("/{id}")
    public ResponseEntity<T> getTsById(@PathVariable(value = "id") Long tId)
            throws ResourceNotFoundException {
        T t =
                repository
                        .findById(tId)
                        .orElseThrow(() -> new ResourceNotFoundException(notFound + tId));
        return ResponseEntity.ok().body(t);
    }

    //Post new ts
    @PostMapping("/")
    public T createT(@Validated @RequestBody T t){
        return repository.save(t);
    }

    /**
     * Delete t map.
     *
     * @param tId the t id
     * @return the map
     */
    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteT(@PathVariable(value = "id") Long tId) throws Exception {
        T t =
                repository
                        .findById(tId)
                        .orElseThrow(() -> new ResourceNotFoundException(notFound + tId));

        repository.delete(t);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
