package com.kukrisports.movie.catalogue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The main entry point for the Movie Catalogue API application.
 * <p>
 * This Spring Boot application manages a Movie Catalog, allowing the creation, update, and deletion of:
 * <ul>
 *   <li>Movies</li>
 *   <li>Movie Directors</li>
 *   <li>Movie Ratings</li>
 * </ul>
 * The application exposes RESTful APIs for performing CRUD operations on these entities, and provides the ability to search movies based on specific criteria, such as:
 * <ul>
 *   <li>Searching movies by Director</li>
 *   <li>Searching movies where the rating is above a specified value</li>
 * </ul>
 * </p>
 * <p>
 * Key Project Requirements:
 * <ul>
 *   <li>Created using the Spring Framework</li>
 *   <li>Built as a self-contained artifact using Maven</li>
 *   <li>Compiled in Java 8</li>
 *   <li>Persists data across application restarts using an RDBMS with appropriate model relationships</li>
 *   <li>Enables data manipulation via REST APIs</li>
 *   <li>Contains unit test coverage</li>
 *   <li>Documents the functionality using Javadoc</li>
 * </ul>
 * </p>
 * <p>
 * This application is designed to handle CRUD operations for movies, directors, and ratings with the ability to search for movies based on:
 * <ul>
 *   <li>Director</li>
 *   <li>Rating above a specified value</li>
 * </ul>
 * </p>
 *
 * @author fazal.babaria
 */
@SpringBootApplication
public class MovieCatalogueApiApplication {

    /**
     * The main method that serves as the entry point to start the Movie Catalogue API application.
     * <p>
     * It uses {@link SpringApplication#run(Class, String...)} to launch the application in a Spring context.
     * The application exposes REST APIs for CRUD operations on movies, directors, and ratings, and provides search functionality.
     * </p>
     *
     * @param args Command line arguments passed during the application startup.
     *              These arguments can be used to customize the application configuration.
     */
    public static void main(String[] args) {
        SpringApplication.run(MovieCatalogueApiApplication.class, args);
    }
}
