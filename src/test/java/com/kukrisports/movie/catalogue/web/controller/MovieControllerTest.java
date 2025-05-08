package com.kukrisports.movie.catalogue.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kukrisports.movie.catalogue.model.DTO.DirectorDTO;
import com.kukrisports.movie.catalogue.model.DTO.MovieDTO;
import com.kukrisports.movie.catalogue.service.MovieService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MovieController.class)
public class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private MovieService movieService;

    @Autowired
    private ObjectMapper objectMapper;

    private UUID movieId;
    private UUID directorId;
    private MovieDTO movieDTO;

    @BeforeEach
    void setUp() {
        movieId = UUID.randomUUID();
        directorId = UUID.randomUUID();

        movieDTO = new MovieDTO();
        movieDTO.setId(movieId);
        movieDTO.setTitle("Inception");
        movieDTO.setReleaseDate(new Date());
        movieDTO.setDirectorId(directorId);
    }

    @Test
    void testCreateMovie() throws Exception {
        Mockito.when(movieService.createMovie(any(MovieDTO.class))).thenReturn(movieDTO);

        mockMvc.perform(post("/api/movie-catalog/v1/movies/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(movieDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.id").value(movieId.toString()));
    }

    @Test
    void testUpdateMovie() throws Exception {
        Mockito.when(movieService.updateMovie(eq(movieId), any(MovieDTO.class))).thenReturn(movieDTO);

        mockMvc.perform(put("/api/movie-catalog/v1/movies/" + movieId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(movieDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.title").value("Inception"));
    }

    @Test
    void testGetMovieById() throws Exception {
        Mockito.when(movieService.fetchMovieById(movieId)).thenReturn(movieDTO);

        mockMvc.perform(get("/api/movie-catalog/v1/movies/" + movieId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.id").value(movieId.toString()));
    }

    @Test
    void testDeleteMovie() throws Exception {
        mockMvc.perform(delete("/api/movie-catalog/v1/movies/" + movieId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").value("Movie deleted successfully"));
    }

    @Test
    void testGetMoviesByDirectorId() throws Exception {
        DirectorDTO directorDTO = new DirectorDTO();
        directorDTO.setId(directorId);
        directorDTO.setName("Christopher Nolan");

        Mockito.when(movieService.fetchMoviesByDirectorId(directorId)).thenReturn(directorDTO);

        mockMvc.perform(get("/api/movie-catalog/v1/movies/director/" + directorId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.name").value("Christopher Nolan"));
    }

    @Test
    void testGetMoviesByRatingThreshold() throws Exception {
        List<MovieDTO> movies = Collections.singletonList(movieDTO);
        PageImpl<MovieDTO> page = new PageImpl<>(movies);

        Mockito.when(movieService.fetchMoviesByRatingGreaterThanEqualTo(eq(4.5), any(PageRequest.class)))
                .thenReturn(page);

        mockMvc.perform(get("/api/movie-catalog/v1/movies/movies-by-rating-threshold/4.5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].title").value("Inception"));
    }

    @Test
    void testGetAllMovies() throws Exception {
        List<MovieDTO> movies = Collections.singletonList(movieDTO);
        PageImpl<MovieDTO> page = new PageImpl<>(movies);

        Mockito.when(movieService.fetchAllMovies(any(PageRequest.class))).thenReturn(page);

        mockMvc.perform(get("/api/movie-catalog/v1/movies/")
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].title").value("Inception"));
    }
}
