package com.kukrisports.movie.catalogue.repository;

import com.kukrisports.movie.catalogue.model.Director;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Repository interface for performing CRUD operations on the {@link Director} entity.
 * Extends {@link JpaRepository} to provide basic database operations such as save, delete, and find for {@link Director} entities.
 * @author fazal.babaria
 */

public interface DirectorRepository extends JpaRepository<Director, UUID> {
}
