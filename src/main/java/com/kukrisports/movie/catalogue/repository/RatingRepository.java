package com.kukrisports.movie.catalogue.repository;

import com.kukrisports.movie.catalogue.model.Movie;
import com.kukrisports.movie.catalogue.model.Rating;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface RatingRepository extends JpaRepository<Rating, UUID> {
    @Query("SELECT DISTINCT r.movie FROM ratings r WHERE r.rating > :rating")
    Page<Movie> findMoviesWithAnyRatingAbove(@Param("rating") double rating, Pageable pageable);

}
