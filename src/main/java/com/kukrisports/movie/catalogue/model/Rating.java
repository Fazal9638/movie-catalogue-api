package com.kukrisports.movie.catalogue.model;

import com.kukrisports.movie.catalogue.model.common.CommonFields;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.antlr.v4.runtime.misc.NotNull;

/**
 * Represents a movie rating in the movie catalog system.
 * A rating is typically user-generated or system-generated feedback on a movie.
 * It includes a numerical rating (between 0 and 10) and an optional textual review.
 * This entity is stored in the "ratings" table in the database.
 * The class extends CommonFields, which adds standard fields like createdAt and updatedAt.
 * @author fazal.babaria
 */

@Entity(name = "ratings")
@Table
public class Rating extends CommonFields {
    /**
     * Numerical score given to a movie.
     * Must be between 0 and 10, inclusive.
     * This field is required.
     */
    @NotNull
    @Min(0)
    @Max(10)
    @Column(name = "rating", nullable = false)
    private Double rating;

    /**
     * Optional text review for the movie.
     * This can be used to add comments or feedback about the movie.
     */
    @Column(name = "review")
    private String review;

    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;

    public @Min(0) @Max(10) Double getRating() {
        return rating;
    }

    public void setRating(@Min(0) @Max(10) Double rating) {
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
