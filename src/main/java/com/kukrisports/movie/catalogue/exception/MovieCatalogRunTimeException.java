package com.kukrisports.movie.catalogue.exception;

/**
 * A custom unchecked exception used across the Movie Catalog service.
 * <p>
 * This is typically thrown when the application encounters an unexpected condition
 * at runtime — for example, data inconsistency, missing configuration, or an external service failure.
 * </p>
 *
 * <p>Use this to signal non-recoverable issues that don't need to be declared or caught explicitly.</p>
 *
 * <p>Example:</p>
 * <pre>{@code
 *     if (movie == null) {
 *         throw new MovieCatalogRunTimeException("Movie not found in the catalog");
 *     }
 * }</pre>
 *
 * @author fazal.babaria
 */
public class MovieCatalogRunTimeException extends RuntimeException {
    private static final long serialVersionUID = -2224392711316978188L;

    public MovieCatalogRunTimeException(final String message) {
        super(message);
    }

    public MovieCatalogRunTimeException(final String message, final Throwable t) {
        super(message, t);
    }
}
