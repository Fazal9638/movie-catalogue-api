package com.kukrisports.movie.catalogue.model.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kukrisports.movie.catalogue.model.Director;
import com.kukrisports.movie.catalogue.model.Movie;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Data Transfer Object (DTO) representing a Director, typically used for API responses.
 * <p>
 * This class includes basic director attributes such as ID, name, birthdate, and biography,
 * along with an optional list of associated movies (in {@link MovieDTO} format).
 * It helps decouple domain models from API contracts and avoids circular references in serialization.
 * </p>
 *
 * <p>
 * Example usage in a service or controller:
 * <pre>{@code
 * List<Movie> movies = movieRepository.findByDirectorId(director.getId());
 * DirectorDTO dto = DirectorDTO.convertToDirectorDTOWithMovies(director, movies);
 * }</pre>
 * </p>
 *
 * @see MovieDTO
 * @see Director
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DirectorDTO {
    private UUID id;
    private String name;
    private Date birthdate;
    private String biography;
    private List<MovieDTO> movies;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public List<MovieDTO> getMovies() {
        return movies;
    }

    public void setMovies(List<MovieDTO> movies) {
        this.movies = movies;
    }

    public static DirectorDTO convertToDirectorDTOWithMovies(Director director, List<Movie> movies) {
        if (director == null) return null;

        DirectorDTO dto = new DirectorDTO();
        dto.setId(director.getId());
        dto.setName(director.getName());
        dto.setBirthdate(director.getBirthdate());
        dto.setBiography(director.getBiography());

        List<MovieDTO> movieDTOs = movies.stream()
                .map(MovieDTO::convertToMovieDTOWithoutDirector)
                .collect(Collectors.toList());

        dto.setMovies(movieDTOs);

        return dto;
    }
}
