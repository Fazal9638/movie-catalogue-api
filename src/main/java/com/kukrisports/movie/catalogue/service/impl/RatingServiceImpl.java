package com.kukrisports.movie.catalogue.service.impl;

import com.kukrisports.movie.catalogue.exception.MovieCatalogRunTimeException;
import com.kukrisports.movie.catalogue.model.DTO.RatingDTO;
import com.kukrisports.movie.catalogue.model.Movie;
import com.kukrisports.movie.catalogue.model.Rating;
import com.kukrisports.movie.catalogue.repository.MovieRepository;
import com.kukrisports.movie.catalogue.repository.RatingRepository;
import com.kukrisports.movie.catalogue.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Implementation of the {@link RatingService} interface for managing movie ratings.
 * <p>
 * This service handles the business logic for creating, updating, deleting, and retrieving ratings for movies.
 * It interacts with the {@link RatingRepository} and {@link MovieRepository} to persist data and perform necessary validations.
 * </p>
 * <p>
 * The service throws {@link MovieCatalogRunTimeException} for invalid inputs, such as invalid movie ID, invalid rating value, etc.
 * </p>
 *
 * @see RatingService
 * @see Rating
 * @see Movie
 * @see RatingDTO
 * @see MovieCatalogRunTimeException
 * @author fazal.babaria
 */

@Service
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;
    private final MovieRepository movieRepository;

    @Autowired
    public RatingServiceImpl(RatingRepository ratingRepository, MovieRepository movieRepository) {
        this.ratingRepository = ratingRepository;
        this.movieRepository = movieRepository;
    }

    @Override
    public RatingDTO createRating(RatingDTO ratingDTO) {
        if (ratingDTO.getMovieId() == null)
            throw new MovieCatalogRunTimeException("Please select a valid movie.");

        Optional<Movie> movieOptional = movieRepository.findById(ratingDTO.getMovieId());
        if (!movieOptional.isPresent())
            throw new MovieCatalogRunTimeException("Movie not found with ID: " + ratingDTO.getMovieId());

        if(ratingDTO.getRating() == null || ratingDTO.getRating() < 0 || ratingDTO.getRating() > 10) {
            throw new MovieCatalogRunTimeException("Rating must be between 0 and 10. Please enter a valid value.");
        }

        Rating rating = RatingDTO.convertToRating(ratingDTO, movieOptional.get());
        return RatingDTO.convertToRatingDTO(ratingRepository.save(rating));
    }

    @Override
    public RatingDTO updateRating(UUID ratingId, RatingDTO ratingDTO) {
        Optional<Rating> optionalRating = ratingRepository.findById(ratingId);
        if (!optionalRating.isPresent())
            throw new MovieCatalogRunTimeException("Rating not found with ID: " + ratingId);

        Rating rating = optionalRating.get();

        if( ratingDTO.getRating() != null && (ratingDTO.getRating() < 0 || ratingDTO.getRating() > 10)) {
            throw new MovieCatalogRunTimeException("Rating must be between 0 and 10. Please enter a valid value.");
        }

        if (ratingDTO.getRating() != null) rating.setRating(ratingDTO.getRating());
        if (ratingDTO.getReview() != null) rating.setReview(ratingDTO.getReview());
        rating.setUpdatedAt(new Date());

        return RatingDTO.convertToRatingDTO(ratingRepository.save(rating));
    }

    @Override
    public void deleteRating(UUID ratingId) {
        Optional<Rating> rating = ratingRepository.findById(ratingId);
        if (!rating.isPresent())
            throw new MovieCatalogRunTimeException("Rating not found with ID: " + ratingId);
        ratingRepository.delete(rating.get());
    }

    @Override
    public RatingDTO getRatingById(UUID ratingId) {
        Optional<Rating> rating = ratingRepository.findById(ratingId);
        if (!rating.isPresent())
            throw new MovieCatalogRunTimeException("Rating not found with ID: " + ratingId);
        return RatingDTO.convertToRatingDTO(rating.get());
    }

    @Override
    public List<RatingDTO> getAllRatings() {
        return ratingRepository.findAll().stream()
                .map(RatingDTO::convertToRatingDTO)
                .collect(Collectors.toList());
    }
}
