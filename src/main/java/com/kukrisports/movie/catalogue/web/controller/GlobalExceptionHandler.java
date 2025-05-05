package com.kukrisports.movie.catalogue.web.controller;

import com.kukrisports.movie.catalogue.exception.MovieCatalogRunTimeException;
import com.kukrisports.movie.catalogue.exception.MovieNotFoundException;
import com.kukrisports.movie.catalogue.model.common.ResponseModel;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Global exception handler for the Movie Catalog application.
 * <p>
 * This class intercepts exceptions thrown across the entire application and
 * converts them into consistent, user-friendly HTTP responses. It ensures
 * clients receive meaningful error messages while avoiding exposure of stack traces or internal logic.
 * </p>
 *
 * <p>
 * All methods in this class return a {@link ResponseModel} containing a standardized failure message and appropriate HTTP status code.
 * </p>
 *
 * <p>
 * Exceptions handled:
 * <ul>
 *     <li>{@link MovieCatalogRunTimeException} - for custom business logic errors</li>
 *     <li>{@link MethodArgumentNotValidException} - for validation failures on incoming requests</li>
 *     <li>{@link AccessDeniedException} - for unauthorized access attempts</li>
 *     <li>{@link Exception} - a generic fallback for unexpected server errors</li>
 * </ul>
 * </p>
 *
 * This class is annotated with {@code @ControllerAdvice}, allowing it to globally manage exceptions across all controllers.
 *
 * @author fazal.babaria
 */

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final String FAILURE = "failure";
    private static final String GENERIC_ERROR = "Error! An error occurred, Please try again.";

    /**
     * Hotline Business Exception
     *
     * @param ex Exception
     * @param req Request
     * @return Failure Response
     */
    @ExceptionHandler(MovieCatalogRunTimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseModel<String> movieCatalogBusinessFailure(final HttpServletRequest req, final MovieCatalogRunTimeException ex) {
        List<String> errors = new ArrayList<>();
        errors.add(ex.getMessage());
        return new ResponseModel<>(FAILURE, HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    /**
     * Invalid Request
     *
     * @param ex Exception
     * @param req Request
     * @return Failure Response
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseModel<String> invalidArgument(final HttpServletRequest req, final MethodArgumentNotValidException ex) {
        Map<String, String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
        return new ResponseModel<>(FAILURE, errors, HttpStatus.BAD_REQUEST.value());
    }

    /**
     * Access Denied exception
     *
     * @param ex Exception
     * @param req Request
     * @return Failure Response
     */
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ResponseModel<String> accessDenied(final HttpServletRequest req, final AccessDeniedException ex) {
        final long now = System.currentTimeMillis();
        List<String> errors = new ArrayList<>();
        errors.add("GENERIC_ERROR" + " - " + System.currentTimeMillis() / 1000);
        return new ResponseModel<>(FAILURE, errors, HttpStatus.UNAUTHORIZED.value());
    }


    @ExceptionHandler(MovieNotFoundException.class)
    public ResponseEntity<Object> handleCustomException(MovieNotFoundException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    /**
     * All other failure handler here
     *
     * @param ex
     * @return Failure Response
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResponseModel<String> internalError(final HttpServletRequest req, final Exception ex) {
        List<String> errors = new ArrayList<>();
        errors.add(GENERIC_ERROR + " - " + LocalDateTime.now());
        return new ResponseModel<>(FAILURE, errors, HttpStatus.INTERNAL_SERVER_ERROR.value());
    }
}
