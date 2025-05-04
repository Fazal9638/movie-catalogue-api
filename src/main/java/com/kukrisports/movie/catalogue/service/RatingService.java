package com.kukrisports.movie.catalogue.service;


import com.kukrisports.movie.catalogue.model.DTO.RatingDTO;

import java.util.List;
import java.util.UUID;

/**
 * Service interface for managing movie ratings.
 * <p>
 * This interface defines the operations related to movie ratings, such as creating, updating,
 * deleting, and retrieving ratings. The ratings are represented as {@link RatingDTO} objects.
 * </p>
 *
 * <p>
 * The service interacts with the underlying repository or database to perform the necessary operations.
 * The {@link RatingDTO} contains the data for ratings, including the rating score, review, and associated movie.
 * </p>
 *
 * <p>
 * The operations provided by this service allow for the management of movie ratings in the application.
 * </p>
 * @author fazal.babaria
 * @see RatingDTO
 */
public interface RatingService {
    /**
     * Creates a new rating.
     * <p>
     * This method persists a new rating in the system.
     * </p>
     *
     * @param ratingDTO the {@link RatingDTO} object containing the details of the rating to create
     * @return the created {@link RatingDTO} with its generated ID
     */
    RatingDTO createRating(RatingDTO ratingDTO);
    /**
     * Updates an existing rating.
     * <p>
     * This method updates an existing rating in the system based on the provided rating ID and new details.
     * </p>
     *
     * @param ratingId the ID of the rating to update
     * @param ratingDTO the {@link RatingDTO} object containing the updated rating details
     * @return the updated {@link RatingDTO}
     */
    RatingDTO updateRating(UUID ratingId, RatingDTO ratingDTO);
    /**
     * Deletes a rating by its ID.
     * <p>
     * This method removes a rating from the system based on the provided rating ID.
     * </p>
     *
     * @param ratingId the ID of the rating to delete
     */
    void deleteRating(UUID ratingId);

    /**
     * Retrieves a rating by its ID.
     * <p>
     * This method fetches a rating from the system using its unique identifier.
     * </p>
     *
     * @param ratingId the ID of the rating to retrieve
     * @return the {@link RatingDTO} representing the rating, or null if not found
     */
    RatingDTO getRatingById(UUID ratingId);

    /**
     * Retrieves all ratings.
     * <p>
     * This method fetches all ratings from the system.
     * </p>
     *
     * @return a list of all {@link RatingDTO} objects
     */
    List<RatingDTO> getAllRatings();
}
