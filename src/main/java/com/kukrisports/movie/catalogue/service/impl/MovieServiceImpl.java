package com.kukrisports.movie.catalogue.service.impl;


import com.kukrisports.movie.catalogue.exception.MovieCatalogRunTimeException;
import com.kukrisports.movie.catalogue.exception.MovieNotFoundException;
import com.kukrisports.movie.catalogue.model.DTO.DirectorDTO;
import com.kukrisports.movie.catalogue.model.DTO.MovieDTO;
import com.kukrisports.movie.catalogue.model.Director;
import com.kukrisports.movie.catalogue.model.Movie;
import com.kukrisports.movie.catalogue.repository.DirectorRepository;
import com.kukrisports.movie.catalogue.repository.MovieRepository;
import com.kukrisports.movie.catalogue.repository.RatingRepository;
import com.kukrisports.movie.catalogue.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final DirectorRepository directorRepository;
    private final RatingRepository ratingRepository;

    /**
     * @param movieDetails movie related information
     * @return Movie
     */
    @Override
    public MovieDTO createMovie(MovieDTO movieDetails) {

        if(movieDetails.getDirectorId() == null)
            throw new MovieCatalogRunTimeException("Please select valid director.");

        Optional<Director> directorOptional = directorRepository.findById(movieDetails.getDirectorId());
        if (!directorOptional.isPresent())
            throw new MovieCatalogRunTimeException("Please select valid director.");

        Movie movie = movieRepository.save(MovieDTO.convertToMovie(movieDetails, directorOptional.get()));
        return MovieDTO.convertToMovieDTO(movie);
    }

    /**
     * @param movieId
     * @param movieDetails
     * @return
     */
    @Override
    public MovieDTO updateMovie(UUID movieId, MovieDTO movieDetails) {
        if (movieId == null || movieDetails.getDirectorId() == null) {
            throw new MovieCatalogRunTimeException("Please enter valid movie id/director id.");
        }
        Optional<Movie> optionalMovie = movieRepository.findById(movieId);
        if (!optionalMovie.isPresent()) {
            throw new MovieNotFoundException("Cannot update: No movie exists with ID " + movieDetails.getId());
        }

        Optional<Director> directorOptional = directorRepository.findById(movieDetails.getDirectorId());
        if (!directorOptional.isPresent())
            throw new MovieCatalogRunTimeException("Please select valid director.");

        Movie movie = optionalMovie.get();
        movie.setTitle(movieDetails.getTitle() != null ? movieDetails.getTitle() : movie.getTitle());
        movie.setReleaseDate(movieDetails.getReleaseDate() != null ? movieDetails.getReleaseDate() : movie.getReleaseDate());
        movie.setDirector(directorOptional.get());
        movie.setUpdatedAt(new Date());
        return MovieDTO.convertToMovieDTO(movieRepository.save(movie));

    }

    /**
     * @param movieId
     */
    @Override
    public void deleteMovie(UUID movieId) {
        Optional<Movie> movie = movieRepository.findById(movieId);
        if (!movie.isPresent()) {
            throw new MovieNotFoundException("Movie not found with id: " + movieId);
        }
        movieRepository.delete(movie.get());
    }

    /**
     * @param id
     * @return
     */
    @Override
    public MovieDTO fetchMovieById(UUID id) {
        Optional<Movie> movie = movieRepository.findById(id);
        if (!movie.isPresent())
            throw new MovieNotFoundException("Movie not found with id: " + id);
        else
           return MovieDTO.convertToMovieDTO(movie.get());
    }

    /**
     * @return
     */
    @Override
    public Page<MovieDTO> fetchAllMovies(Pageable pageable) {
        return movieRepository.findAll(pageable).map(MovieDTO::convertToMovieDTO);
    }

    /**
     * @param directorId
     * @return
     */
    @Override
    public DirectorDTO fetchMoviesByDirectorId(UUID directorId) {
        Optional<Director> director = directorRepository.findById(directorId);
        if (!director.isPresent()) {
            throw new MovieCatalogRunTimeException("Director not found with id: " + directorId);
        }
        return DirectorDTO.convertToDirectorDTOWithMovies(director.get(), movieRepository.findByDirectorIdOrderByReleaseDateDesc(directorId));
    }

    /**
     * @param ratingThreshold
     * @return
     */
    @Override
    public Page<MovieDTO> fetchMoviesByRatingGreaterThanEqualTo(Double ratingThreshold, Pageable pageable) {
        return ratingRepository.findMoviesWithAnyRatingAbove(ratingThreshold, pageable).map(MovieDTO::convertToMovieDTOWithoutDirector);
    }

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository, DirectorRepository directorRepository, RatingRepository ratingRepository) {
        this.movieRepository = movieRepository;
        this.directorRepository = directorRepository;
        this.ratingRepository = ratingRepository;
    }
}
