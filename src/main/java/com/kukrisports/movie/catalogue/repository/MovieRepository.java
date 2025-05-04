package com.kukrisports.movie.catalogue.repository;


import com.kukrisports.movie.catalogue.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MovieRepository extends JpaRepository<Movie, UUID> {
    public List<Movie> findByDirectorIdOrderByReleaseDateDesc(UUID directorId);
}
