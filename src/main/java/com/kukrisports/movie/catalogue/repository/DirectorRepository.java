package com.kukrisports.movie.catalogue.repository;

import com.kukrisports.movie.catalogue.model.Director;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DirectorRepository extends JpaRepository<Director, UUID> {
}
