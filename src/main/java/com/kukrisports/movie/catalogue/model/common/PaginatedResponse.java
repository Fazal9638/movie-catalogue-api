package com.kukrisports.movie.catalogue.model.common;

import java.io.Serializable;
import java.util.List;
/**
 * Generic wrapper class for paginated API responses.
 * <p>
 * This class encapsulates a list of items and related pagination metadata such as
 * current page, page size, total number of elements, total pages, and whether it's the last page.
 * It's commonly used when querying pageable resources via REST endpoints.
 * </p>
 *
 * <p>Typical usage:</p>
 * <pre>
 * Page&lt;MovieDto&gt; moviePage = movieService.getMovies(page, size);
 * return new PaginatedResponse&lt;&gt;(
 *     moviePage.getContent(),
 *     moviePage.getNumber(),
 *     moviePage.getSize(),
 *     moviePage.getTotalElements(),
 *     moviePage.getTotalPages(),
 *     moviePage.isLast()
 * );
 * </pre>
 *
 * @param <T> The type of content in the paginated response (e.g., DTO or entity).
 */
public class PaginatedResponse<T> implements Serializable {
    private List<T> content;
    private int pageNumber;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;

    public PaginatedResponse(List<T> content, int pageNumber, int pageSize, long totalElements, int totalPages, boolean last) {
        this.content = content;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.last = last;
    }

    // Getters and Setters

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }
}
