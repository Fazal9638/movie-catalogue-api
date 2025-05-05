package com.kukrisports.movie.catalogue.repository;

import com.kukrisports.movie.catalogue.model.Movie;
import com.kukrisports.movie.catalogue.model.Rating;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

/**
 * Repository interface for performing CRUD operations on the {@link Rating} entity.
 * Extends {@link JpaRepository} to provide basic database operations such as save, delete, and find for {@link Rating} entities.
 * Additionally, provides custom query methods for retrieving movies based on rating conditions.
 * @author fazal.babaria
 */
public interface RatingRepository extends JpaRepository<Rating, UUID> {
    @Query("SELECT DISTINCT r.movie FROM ratings r WHERE r.rating > :rating")
    Page<Movie> findMoviesWithAnyRatingAbove(@Param("rating") double rating, Pageable pageable);
}
