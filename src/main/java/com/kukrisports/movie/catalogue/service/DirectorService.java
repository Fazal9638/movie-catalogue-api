package com.kukrisports.movie.catalogue.service;

import com.kukrisports.movie.catalogue.model.Director;

import java.util.List;
import java.util.UUID;

/**
 * Service interface for managing movie directors.
 * <p>
 * This interface defines operations related to the creation, updating, deletion, and retrieval of movie directors.
 * Directors are represented by the {@link Director} model, which contains information such as name, birthdate, and biography.
 * </p>
 * <p>
 * The service layer interacts with the underlying repository or database to perform these operations.
 * </p>
 *
 * @see Director
 * @author fazal.babaria
 */
public interface DirectorService {

    /**
     * Creates a new director.
     * <p>
     * This method persists a new director in the system with the provided details.
     * </p>
     *
     * @param director the {@link Director} object containing the director's details
     * @return the created {@link Director} object with its generated ID
     */
    Director createDirector(Director director);

    /**
     * Updates an existing director.
     * <p>
     * This method updates the details of an existing director in the system using the provided director ID.
     * </p>
     *
     * @param directorId the ID of the director to update
     * @param director the {@link Director} object containing the updated director details
     * @return the updated {@link Director} object
     */
    Director updateDirector(UUID directorId, Director director);

    /**
     * Deletes a director by their ID.
     * <p>
     * This method removes a director from the system based on the provided director ID.
     * </p>
     *
     * @param id the ID of the director to delete
     */
    void deleteDirector(UUID id);

    /**
     * Retrieves a director by their ID.
     * <p>
     * This method fetches a director from the system using their unique identifier.
     * </p>
     *
     * @param id the ID of the director to retrieve
     * @return the {@link Director} object representing the director, or null if not found
     */
    Director getDirectorById(UUID id);

    /**
     * Retrieves all directors.
     * <p>
     * This method fetches all directors from the system.
     * </p>
     *
     * @return a list of all {@link Director} objects
     */
    List<Director> getAllDirectors();
}
