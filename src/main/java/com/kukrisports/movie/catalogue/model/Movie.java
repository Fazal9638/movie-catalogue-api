package com.kukrisports.movie.catalogue.model;

import com.kukrisports.movie.catalogue.model.common.CommonFields;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Date;
import java.util.List;


/**
 * This class represents a Movie in the system.
 * <ul><li>A movie has a title, a release date, a director, and a list of ratings given by users.</li>
 * <li>It's stored in the "movies" table in the database.</li>
 * <li>Each movie must be linked to a director (many movies can have the same director),
 * and can have multiple ratings associated with it.</li>
 * <li>The title must be between 1 and 255 characters and cannot be blank.</li>
 * <li>The release date is required and must be a valid date.</li>
 * <li>This class extends CommonFields, so it also includes createdAt and updatedAt fields
 * to track when the movie was created or last updated.</li></ul>
 * @author fazal.babaria
 */

@Entity(name = "movies")
@Table
public class Movie extends CommonFields {

    /**
     * Title of the movie.
     * Must be between 1 and 255 characters.
     */
    @NotBlank(message = "Title is mandatory")
    @Size(min = 1, max = 255, message = "Title must be between 1 and 255 characters")
    private String title;

    /**
     * Release date of the movie.
     * Cannot be null.
     */
    @Temporal(TemporalType.DATE)
    @NotNull(message = "Release date is mandatory")
    private Date releaseDate;

    /**
     * Director of the movie.
     * Cannot be null.
     * Represents a many-to-one relationship.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "director_id", nullable = false)
    private Director director;

    /**
     * List of ratings received by the movie.
     * Represents a one-to-many relationship.
     */
    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Rating> ratings;

    public @NotBlank(message = "Title is mandatory") @Size(min = 1, max = 255, message = "Title must be between 1 and 255 characters") String getTitle() {
        return title;
    }

    public void setTitle(@NotBlank(message = "Title is mandatory") @Size(min = 1, max = 255, message = "Title must be between 1 and 255 characters") String title) {
        this.title = title;
    }

    public @NotNull(message = "Release date is mandatory") Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(@NotNull(message = "Release date is mandatory") Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }
}
