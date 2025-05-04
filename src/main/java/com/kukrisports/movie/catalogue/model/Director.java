package com.kukrisports.movie.catalogue.model;

import com.kukrisports.movie.catalogue.model.common.CommonFields;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Date;

/**
 * This class represents a Director in the movie catalog system.
 * A director is responsible for creating movies and can be linked to multiple movies.
 * This entity is stored in the "directors" table in the database.
 * Each director has a name, a birthdate, an optional biography, and a list of movies they’ve directed.
 * The name must be between 1 and 255 characters and cannot be blank.
 * The birthdate is required and must be a valid date.
 * The biography is optional but must not exceed 1000 characters if provided.
 * This class also inherits createdAt and updatedAt timestamps from CommonFields.
 * @author fazal.babaria
 */

@Entity(name = "directors")
@Table
public class Director extends CommonFields {
    /**
     * The full name of the director.
     * This field is required and must be between 1 and 255 characters.
     */
    @NotBlank(message = "Director name is mandatory")
    @Size(min = 1, max = 255, message = "Director name must be between 1 and 255 characters")
    private String name;

    /**
     * The director’s date of birth.
     * This field is required and must be a valid date.
     */
    @NotNull(message = "Birthdate is mandatory")
    @Temporal(TemporalType.DATE)
    private Date birthdate;

    /**
     * A short biography of the director.
     * This field is optional and must not exceed 1000 characters.
     */
    @Size(max = 1000, message = "Biography must be less than 1000 characters")
    private String biography;

    /**
     * A list of movies directed by this director.
     * One director can be associated with many movies.
     */

    public @NotBlank(message = "Director name is mandatory") @Size(min = 1, max = 255, message = "Director name must be between 1 and 255 characters") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Director name is mandatory") @Size(min = 1, max = 255, message = "Director name must be between 1 and 255 characters") String name) {
        this.name = name;
    }

    public @NotNull(message = "Birthdate is mandatory") Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(@NotNull(message = "Birthdate is mandatory") Date birthdate) {
        this.birthdate = birthdate;
    }

    public @Size(max = 1000, message = "Biography must be less than 1000 characters") String getBiography() {
        return biography;
    }

    public void setBiography(@Size(max = 1000, message = "Biography must be less than 1000 characters") String biography) {
        this.biography = biography;
    }
}
