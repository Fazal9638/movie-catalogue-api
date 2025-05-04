package com.kukrisports.movie.catalogue.model.common;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Generic wrapper class used to standardize API responses across the application.
 * <p>
 * This class supports a flexible structure that allows returning data, messages,
 * status codes, and error information in both list and map formats. By using
 * {@code @JsonInclude(JsonInclude.Include.NON_NULL)}, any null fields are automatically
 * excluded from the serialized JSON, keeping the response clean and concise.
 * </p>
 *
 * <p>Typical usage includes successful responses with data and status, or error
 * responses with error messages or field-specific error details.</p>
 *
 * <pre>
 * Example (success):
 * new ResponseModel<>(movieDto, 200);
 *
 * Example (error with list):
 * new ResponseModel<>(null, List.of("Title is required"), 400);
 *
 * Example (error with field map):
 * new ResponseModel<>(null, Map.of("title", "Title is required"), 400);
 * </pre>
 *
 * @param <T> the type of data being returned (can be a DTO, List, etc.)
 * @author fazal.babaria
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseModel<T> implements Serializable {
    private static final long serialVersionUID = -4022678560985243055L;

    private T data;
    private String message;
    private int status;
    private List<String> errors;
    private Map<String, String> mapErrors;

    // Constructor for data and status
    public ResponseModel(final T data, final int status) {
        this.data = data;
        this.status = status;
        this.message = "";
    }

    // Constructor for data, status, and message
    public ResponseModel(final T data, final int status, final String message) {
        this.data = data;
        this.status = status;
        this.message = message;
    }

    // Constructor for data, errors (List), and status
    public ResponseModel(final T data, final List<String> errors, final int status) {
        this.data = data;
        this.errors = errors;
        this.status = status;
    }

    // Constructor for data, mapErrors (Map), and status
    public ResponseModel(final T data, final Map<String, String> mapErrors, final int status) {
        this.data = data;
        this.mapErrors = mapErrors;
        this.status = status;
    }

    // Constructor for data, errors (List), message, and status
    public ResponseModel(final T data, final List<String> errors, final String message, final int status) {
        this.data = data;
        this.errors = errors;
        this.message = message;
        this.status = status;
    }

    // Constructor for data, mapErrors (Map), message, and status
    public ResponseModel(final T data, final Map<String, String> mapErrors, final String message, final int status) {
        this.data = data;
        this.mapErrors = mapErrors;
        this.message = message;
        this.status = status;
    }

    // Getters and Setters
    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public Map<String, String> getMapErrors() {
        return mapErrors;
    }

    public void setMapErrors(Map<String, String> mapErrors) {
        this.mapErrors = mapErrors;
    }
}
