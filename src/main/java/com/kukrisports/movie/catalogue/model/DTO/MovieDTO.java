package com.kukrisports.movie.catalogue.model.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kukrisports.movie.catalogue.model.Director;
import com.kukrisports.movie.catalogue.model.Movie;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.Date;
import java.util.UUID;

/**
 * Data Transfer Object (DTO) for the {@link Movie} entity.
 * <p>
 * This class is used to transfer movie data between different application layers, particularly
 * for API requests and responses. It includes fields for movie metadata along with optional
 * director information.
 * </p>
 *
 * <p>
 * Validation annotations are used to ensure data integrity for input fields, making this
 * suitable for request body binding in Spring REST controllers.
 * </p>
 *
 * @see Movie
 * @see DirectorDTO
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MovieDTO {

    private UUID id;

    @NotBlank(message = "Title is mandatory")
    @Size(min = 1, max = 255, message = "Title must be between 1 and 255 characters")
    private String title;

    @NotNull(message = "Release date is mandatory")
    private Date releaseDate;

    @NotNull(message = "Director ID is mandatory")
    private UUID directorId;

    private DirectorDTO directorDTO;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public DirectorDTO getDirectorDTO() {
        return directorDTO;
    }

    public void setDirectorDTO(DirectorDTO directorDTO) {
        this.directorDTO = directorDTO;
    }

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

    public @NotNull(message = "Director ID is mandatory") UUID getDirectorId() {
        return directorId;
    }

    public void setDirectorId(@NotNull(message = "Director ID is mandatory") UUID directorId) {
        this.directorId = directorId;
    }

    public static MovieDTO convertToMovieDTO(Movie movie) {
        if (movie == null) return null;

        MovieDTO dto = new MovieDTO();
        dto.setId(movie.getId());
        dto.setTitle(movie.getTitle());
        dto.setReleaseDate(movie.getReleaseDate());

        Director director = movie.getDirector();
        if (director != null) {
            DirectorDTO directorDTO = new DirectorDTO();
            dto.setDirectorId(director.getId());
            directorDTO.setId(director.getId());
            directorDTO.setName(director.getName());
            directorDTO.setBirthdate(director.getBirthdate());
            directorDTO.setBiography(director.getBiography());
            dto.setDirectorDTO(directorDTO);
        }
        return dto;
    }

    public static Movie convertToMovie(MovieDTO dto, Director director) {
        if (dto == null) return null;
        Movie movie = new Movie();
        movie.setId(dto.getId());
        movie.setTitle(dto.getTitle());
        movie.setReleaseDate(dto.getReleaseDate());
        movie.setDirector(director);
        return movie;
    }

    public static MovieDTO convertToMovieDTOWithoutDirector(Movie movie) {
        if (movie == null) return null;

        MovieDTO dto = new MovieDTO();
        dto.setId(movie.getId());
        dto.setTitle(movie.getTitle());
        dto.setReleaseDate(movie.getReleaseDate());
        return dto;
    }
}
