package com.kukrisports.movie.catalogue.web.controller;


import com.kukrisports.movie.catalogue.model.Director;
import com.kukrisports.movie.catalogue.model.common.ResponseModel;
import com.kukrisports.movie.catalogue.service.DirectorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * REST controller for managing movie directors.
 * <p>
 * Provides endpoints to create, update, retrieve, list, and delete directors in the catalog.
 * </p>
 * @author fazal.babaria
 */

@RestController
@Validated
@RequestMapping("/api/directors")
public class DirectorController {
    @Autowired
    private DirectorService directorService;

    /**
     * Creates a new director.
     *
     * @param director the director details
     * @return the created director wrapped in a response
     */
    @PostMapping
    public ResponseModel<Director> createDirector(@RequestBody Director director) {
        return new ResponseModel<>(directorService.createDirector(director), HttpStatus.OK.value());
    }

    /**
     * Updates an existing director by ID.
     *
     * @param id the UUID of the director to update
     * @param director the updated director details
     * @return the updated director wrapped in a response
     */
    @PutMapping("/{id}")
    public ResponseModel<Director> updateDirector(@Valid @PathVariable UUID id, @Valid @RequestBody Director director) {
        return new ResponseModel<>(directorService.updateDirector(id, director), HttpStatus.OK.value());
    }

    /**
     * Retrieves a director by ID.
     *
     * @param id the UUID of the director
     * @return the director wrapped in a response
     */
    @GetMapping("/{id}")
    public ResponseModel<Director> getDirectorById(@Valid @PathVariable UUID id) {
        return new ResponseModel<>(directorService.getDirectorById(id), HttpStatus.OK.value());
    }

    /**
     * Retrieves all directors.
     *
     * @return list of all directors wrapped in a response
     */
    @GetMapping
    public ResponseModel<List<Director>> getAllDirectors() {
        return new ResponseModel<>(directorService.getAllDirectors(), HttpStatus.OK.value());
    }

    /**
     * Deletes a director by ID.
     *
     * @param id the UUID of the director
     * @return success message wrapped in a response
     */
    @DeleteMapping("/{id}")
    public ResponseModel<String> deleteDirector(@Valid @PathVariable UUID id) {
        directorService.deleteDirector(id);
        return new ResponseModel<>("Director deleted successfully", HttpStatus.OK.value());
    }
}
