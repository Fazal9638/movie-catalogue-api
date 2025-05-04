package com.kukrisports.movie.catalogue.service;

import com.kukrisports.movie.catalogue.model.DTO.DirectorDTO;
import com.kukrisports.movie.catalogue.model.DTO.MovieDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

/**
 * Service interface for managing movies in the catalog.
 * <p>
 * This interface defines operations related to the creation, updating, deletion, and retrieval of movies.
 * Movies are represented by the {@link MovieDTO} data transfer object, which includes details such as title, release date, director, and rating.
 * </p>
 * <p>
 * The service layer interacts with the underlying repository or database to perform these operations.
 * </p>
 *
 * @see MovieDTO
 * @see DirectorDTO
 */
public interface MovieService {

    /**
     * Creates a new movie in the catalog.
     * <p>
     * This method persists a new movie using the provided {@link MovieDTO} object.
     * </p>
     *
     * @param movie the {@link MovieDTO} object containing movie details
     * @return the created {@link MovieDTO} object with its generated ID
     */
    MovieDTO createMovie(MovieDTO movie);

    /**
     * Updates an existing movie's details.
     * <p>
     * This method updates the details of an existing movie using the provided movie ID.
     * </p>
     *
     * @param movieId the ID of the movie to update
     * @param movie the {@link MovieDTO} object containing the updated movie details
     * @return the updated {@link MovieDTO} object
     */
    MovieDTO updateMovie(UUID movieId, MovieDTO movie);

    /**
     * Deletes a movie by its ID.
     * <p>
     * This method removes a movie from the catalog based on the provided movie ID.
     * </p>
     *
     * @param movieId the ID of the movie to delete
     */
    void deleteMovie(UUID movieId);

    /**
     * Retrieves a movie by its ID.
     * <p>
     * This method fetches a movie from the catalog using its unique identifier.
     * </p>
     *
     * @param movieId the ID of the movie to retrieve
     * @return the {@link MovieDTO} object representing the movie, or null if not found
     */
    MovieDTO fetchMovieById(UUID movieId);

    /**
     * Retrieves all movies with pagination.
     * <p>
     * This method fetches all movies from the catalog and paginates the result using the provided {@link Pageable} object.
     * </p>
     *
     * @param pageable the pagination details (e.g., page size and page number)
     * @return a {@link Page} of {@link MovieDTO} objects representing the movies
     */
    Page<MovieDTO> fetchAllMovies(Pageable pageable);

    /**
     * Retrieves all movies by a specific director's ID.
     * <p>
     * This method fetches all movies from the catalog that are directed by the specified director.
     * </p>
     *
     * @param directorId the ID of the director whose movies are to be fetched
     * @return a {@link DirectorDTO} object containing the director's details and a list of movies
     */
    DirectorDTO fetchMoviesByDirectorId(UUID directorId);

    /**
     * Retrieves all movies with a rating greater than or equal to the specified value.
     * <p>
     * This method fetches all movies from the catalog that have a rating greater than or equal to the specified rating.
     * The results are paginated based on the provided {@link Pageable} object.
     * </p>
     *
     * @param rating the minimum rating value
     * @param pageable the pagination details (e.g., page size and page number)
     * @return a {@link Page} of {@link MovieDTO} objects representing the movies with the specified rating
     */
    Page<MovieDTO> fetchMoviesByRatingGreaterThanEqualTo(Double rating, Pageable pageable);
}
