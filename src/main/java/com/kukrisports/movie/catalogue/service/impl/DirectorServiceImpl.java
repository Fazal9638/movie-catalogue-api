package com.kukrisports.movie.catalogue.service.impl;

import com.kukrisports.movie.catalogue.exception.MovieCatalogRunTimeException;
import com.kukrisports.movie.catalogue.model.Director;
import com.kukrisports.movie.catalogue.repository.DirectorRepository;
import com.kukrisports.movie.catalogue.service.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Implementation of the {@link DirectorService} interface, providing CRUD operations for managing directors in the movie catalog.
 */
@Service
public class DirectorServiceImpl implements DirectorService {
    /**
     * @param directorData
     * @return
     */
    @Override
    public Director createDirector(Director directorData) {
        if (directorData == null || directorData.getName() == null || directorData.getName().trim().isEmpty() || directorData.getBirthdate() == null)
            throw new MovieCatalogRunTimeException("Director Name and Birthdate are mandatory fields. Please ensure the values are not blank.");

        return directorRepository.save(directorData);
    }

    /**
     * @param directorData
     * @return
     */
    @Override
    public Director updateDirector(UUID directorId, Director directorData) {
        if (directorId == null || directorData == null || directorData.getName() == null || directorData.getName().trim().isEmpty() || directorData.getBirthdate() == null)
            throw new MovieCatalogRunTimeException("Director Name and Birthdate are mandatory fields. Please ensure the values are not blank.");

        Optional<Director> optionalDirector = directorRepository.findById(directorData.getId());
        if(!optionalDirector.isPresent())
            throw new MovieCatalogRunTimeException("Director not found with id: " + directorData.getId());
        Director director = optionalDirector.get();
        director.setBiography(directorData.getBiography());
        director.setName(directorData.getName());
        director.setUpdatedAt(new Date());
        director.setBirthdate(directorData.getBirthdate());
        return directorRepository.save(director);
    }

    /**
     * @param id
     */
    @Override
    public void deleteDirector(UUID id) {
        if (!directorRepository.existsById(id)) {
            throw new MovieCatalogRunTimeException("Director with ID " + id + " not found.");
        }
        directorRepository.deleteById(id);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Director getDirectorById(UUID id) {
        Optional<Director> director = directorRepository.findById(id);
        return director.orElseThrow(() -> new MovieCatalogRunTimeException("Director not found with id: " + id));
    }

    /**
     * @return
     */
    @Override
    public List<Director> getAllDirectors() {
        return directorRepository.findAll();
    }

    @Autowired
    public DirectorServiceImpl(DirectorRepository directorRepository) {
        this.directorRepository = directorRepository;
    }

    private final DirectorRepository directorRepository;
}
