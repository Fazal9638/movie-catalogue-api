package com.kukrisports.movie.catalogue.model.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kukrisports.movie.catalogue.model.Movie;
import com.kukrisports.movie.catalogue.model.Rating;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class RatingDTO {

    private UUID id;

    @NotNull(message = "Rating is mandatory")
    @Min(value = 0, message = "Rating must be at least 0")
    @Max(value = 10, message = "Rating must be at most 10")
    private Double rating;

    private String review;

    @NotNull(message = "Movie ID is mandatory")
    private UUID movieId;

    @JsonProperty("movie")
    private MovieDTO movieDTO;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public UUID getMovieId() {
        return movieId;
    }

    public void setMovieId(UUID movieId) {
        this.movieId = movieId;
    }

    public MovieDTO getMovieDTO() {
        return movieDTO;
    }

    public void setMovieDTO(MovieDTO movieDTO) {
        this.movieDTO = movieDTO;
    }

    public static RatingDTO convertToRatingDTO(Rating rating) {
        if (rating == null) return null;

        RatingDTO dto = new RatingDTO();
        dto.setId(rating.getId());
        dto.setRating(rating.getRating());
        dto.setReview(rating.getReview());

        if (rating.getMovie() != null) {
            dto.setMovieId(rating.getMovie().getId());
            dto.setMovieDTO(MovieDTO.convertToMovieDTOWithoutDirector(rating.getMovie()));
        }

        return dto;
    }

    public static Rating convertToRating(RatingDTO dto, Movie movie) {
        if (dto == null) return null;

        Rating rating = new Rating();
        rating.setId(dto.getId());
        rating.setRating(dto.getRating());
        rating.setReview(dto.getReview());
        rating.setMovie(movie);

        return rating;
    }
}
