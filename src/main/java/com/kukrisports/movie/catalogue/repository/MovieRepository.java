package com.kukrisports.movie.catalogue.repository;


import com.kukrisports.movie.catalogue.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * Repository interface for performing CRUD operations on the {@link Movie} entity.
 * Extends {@link JpaRepository} to provide basic database operations such as save, delete, and find for {@link Movie} entities.
 * Additionally, provides custom query methods for retrieving movies based on specific conditions.
 * @author fazal.babaria
 */
@Repository
public interface MovieRepository extends JpaRepository<Movie, UUID> {
    public List<Movie> findByDirectorIdOrderByReleaseDateDesc(UUID directorId);
}
