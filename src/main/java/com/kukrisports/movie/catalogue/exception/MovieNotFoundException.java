package com.kukrisports.movie.catalogue.exception;

/**
 * Exception thrown when a requested movie cannot be found in the catalog.
 * <p>
 * This is a custom unchecked exception typically used in service or controller layers
 * when an operation expects a movie to exist but it is not found in the database or source.
 * </p>
 *
 * <p>Example usage:</p>
 * <pre>{@code
 *     Movie movie = movieRepository.findById(id)
 *         .orElseThrow(() -> new MovieNotFoundException("Movie with ID " + id + " not found"));
 * }</pre>
 *
 * <p>Use this to cleanly separate "not found" scenarios from other types of runtime failures.</p>
 *
 * @author fazal.babaria
 */
public class MovieNotFoundException extends RuntimeException {
    public MovieNotFoundException(String message) {
        super(message);
    }
}
