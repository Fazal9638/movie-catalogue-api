package com.kukrisports.movie.catalogue.web.controller;

import com.kukrisports.movie.catalogue.model.DTO.DirectorDTO;
import com.kukrisports.movie.catalogue.model.DTO.MovieDTO;
import com.kukrisports.movie.catalogue.model.common.PaginatedResponse;
import com.kukrisports.movie.catalogue.model.common.ResponseModel;
import com.kukrisports.movie.catalogue.service.MovieService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;


/**
 * REST controller for managing movies in the catalog.
 * <p>
 * Provides endpoints to create, update, retrieve, list, and delete movies.
 * Also supports filtering by director and rating threshold.
 */
@RestController
@Validated
@RequestMapping("/api/movie-catalog/v1/movies")
public class MovieController {
    @Autowired
    private MovieService movieService;

    /**
     * Creates a new movie.
     *
     * @param movie the movie details
     * @return the created movie wrapped in a response
     */
    @PostMapping("/create-movie")
    public ResponseModel<MovieDTO> createMovie(@RequestBody MovieDTO movie) {
        return new ResponseModel<>(movieService.createMovie(movie), HttpStatus.OK.value());
    }

    /**
     * Updates an existing movie by ID.
     *
     * @param id the UUID of the movie to update
     * @param movieDTO the updated movie data
     * @return the updated movie wrapped in a response
     */
    @PutMapping("update-movie/{id}")
    public ResponseModel<MovieDTO> updateMovie(@Valid @PathVariable UUID id, @Valid @RequestBody MovieDTO movieDTO) {
        return new ResponseModel<>(movieService.updateMovie(id, movieDTO), HttpStatus.OK.value());
    }

    /**
     * Retrieves a movie by its ID.
     *
     * @param movieId the UUID of the movie
     * @return the movie wrapped in a response
     */
    @GetMapping("/{movieId}")
    public ResponseModel<MovieDTO> getMovieById(@Valid @PathVariable UUID movieId) {
        return new ResponseModel<>(movieService.fetchMovieById(movieId), HttpStatus.OK.value());
    }

    /**
     * Deletes a movie by its ID.
     *
     * @param id the UUID of the movie
     * @return success message wrapped in a response
     */
    @DeleteMapping("/{id}")
    public ResponseModel<String> deleteMovie(@Valid @PathVariable UUID id) {
        movieService.deleteMovie(id);
        return new ResponseModel<>("Movie deleted successfully", HttpStatus.OK.value());
    }

    /**
     * Retrieves all movies by a specific director ID.
     *
     * @param directorId the UUID of the director
     * @return the director along with their movies wrapped in a response
     */
    @GetMapping("director/{directorId}")
    public ResponseModel<DirectorDTO> getMoviesByDirectorId(@PathVariable UUID directorId) {
        DirectorDTO directorDTO = movieService.fetchMoviesByDirectorId(directorId);

        if (directorDTO == null) {
            return new ResponseModel<>(null, HttpStatus.NOT_FOUND.value());
        }

        return new ResponseModel<>(directorDTO, HttpStatus.OK.value());
    }

    /**
     * Retrieves all movies with a rating equal to or above the given threshold.
     *
     * @param ratingThreshold the minimum rating to filter movies
     * @return list of movies matching the rating criteria wrapped in a response
     */
    @GetMapping("movies-by-rating-threshold/{ratingThreshold}")
    public ResponseEntity<PaginatedResponse<MovieDTO>> getMoviesByDirectorId(@PathVariable double ratingThreshold,
                                                                             @RequestParam(defaultValue = "0") int page,
                                                                             @RequestParam(defaultValue = "10") int size) {
        Page<MovieDTO> moviePage = movieService.fetchMoviesByRatingGreaterThanEqualTo(ratingThreshold, PageRequest.of(page, size));
        PaginatedResponse<MovieDTO> response = new PaginatedResponse<>(
                moviePage.getContent(), moviePage.getNumber(), moviePage.getSize(),
                moviePage.getTotalElements(), moviePage.getTotalPages(), moviePage.isLast());
        return ResponseEntity.ok(response);
    }

    /**
     * Retrieves all movies.
     *
     * @return list of all movies wrapped in a response
     */
    @GetMapping("/")
    public ResponseEntity<PaginatedResponse<MovieDTO>> getAllMovies(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<MovieDTO> moviePage = movieService.fetchAllMovies(PageRequest.of(page, size));
        PaginatedResponse<MovieDTO> response = new PaginatedResponse<>(
                moviePage.getContent(), moviePage.getNumber(), moviePage.getSize(),
                moviePage.getTotalElements(), moviePage.getTotalPages(), moviePage.isLast());
        return ResponseEntity.ok(response);
    }
}
