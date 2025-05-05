package com.kukrisports.movie.catalogue.web.controller;

import com.kukrisports.movie.catalogue.model.DTO.RatingDTO;
import com.kukrisports.movie.catalogue.model.common.ResponseModel;
import com.kukrisports.movie.catalogue.service.RatingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * REST controller for handling movie ratings.
 * <p>
 * Provides endpoints to create, update, retrieve, list, and delete ratings.
 * @author fazal.babaria
 */

@RestController
@Validated
@RequestMapping("/api/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    /**
     * Creates a new rating.
     *
     * @param ratingDTO the rating details
     * @return the created rating wrapped in a response
     */
    @PostMapping
    public ResponseModel<RatingDTO> createRating(@Valid @RequestBody RatingDTO ratingDTO) {
        return new ResponseModel<>(ratingService.createRating(ratingDTO), HttpStatus.CREATED.value());
    }

    /**
     * Updates an existing rating by ID.
     *
     * @param id the UUID of the rating to update
     * @param ratingDTO the updated rating details
     * @return the updated rating wrapped in a response
     */
    @PutMapping("/{id}")
    public ResponseModel<RatingDTO> updateRating(@PathVariable UUID id, @Valid @RequestBody RatingDTO ratingDTO) {
        return new ResponseModel<>(ratingService.updateRating(id, ratingDTO), HttpStatus.OK.value());
    }

    /**
     * Retrieves a rating by ID.
     *
     * @param id the UUID of the rating
     * @return the rating wrapped in a response
     */
    @GetMapping("/{id}")
    public ResponseModel<RatingDTO> getRatingById(@PathVariable UUID id) {
        return new ResponseModel<>(ratingService.getRatingById(id), HttpStatus.OK.value());
    }

    /**
     * Retrieves all ratings.
     *
     * @return list of all ratings wrapped in a response
     */
    @GetMapping("/")
    public ResponseModel<List<RatingDTO>> getAllRatings() {
        return new ResponseModel<>(ratingService.getAllRatings(), HttpStatus.OK.value());
    }

    /**
     * Deletes a rating by ID.
     *
     * @param id the UUID of the rating
     * @return success message wrapped in a response
     */
    @DeleteMapping("/{id}")
    public ResponseModel<String> deleteRating(@PathVariable UUID id) {
        ratingService.deleteRating(id);
        return new ResponseModel<>("Rating deleted successfully", HttpStatus.OK.value());
    }
}
