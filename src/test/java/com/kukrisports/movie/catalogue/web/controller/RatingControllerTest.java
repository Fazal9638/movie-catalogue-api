package com.kukrisports.movie.catalogue.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kukrisports.movie.catalogue.model.DTO.MovieDTO;
import com.kukrisports.movie.catalogue.model.DTO.RatingDTO;
import com.kukrisports.movie.catalogue.service.RatingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RatingController.class)
class RatingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private RatingService ratingService;

    @Autowired
    private ObjectMapper objectMapper;

    private RatingDTO sampleDTO;
    private UUID ratingId;
    private UUID movieId;

    @BeforeEach
    void setUp() {
        ratingId = UUID.randomUUID();
        movieId = UUID.randomUUID();

        sampleDTO = new RatingDTO();
        sampleDTO.setId(ratingId);
        sampleDTO.setRating(8.5);
        sampleDTO.setReview("Excellent movie!");
        sampleDTO.setMovieId(movieId);
        sampleDTO.setMovieDTO(new MovieDTO()); // Assuming a basic constructor or null is fine
    }

    @Test
    void testCreateRating() throws Exception {
        Mockito.when(ratingService.createRating(any(RatingDTO.class)))
               .thenReturn(sampleDTO);

        mockMvc.perform(post("/api/ratings")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(sampleDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.rating").value(8.5));
    }

    @Test
    void testUpdateRating() throws Exception {
        Mockito.when(ratingService.updateRating(eq(ratingId), any(RatingDTO.class)))
               .thenReturn(sampleDTO);

        mockMvc.perform(put("/api/ratings/{id}", ratingId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(sampleDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.id").value(ratingId.toString()));
    }

    @Test
    void testGetRatingById() throws Exception {
        Mockito.when(ratingService.getRatingById(ratingId)).thenReturn(sampleDTO);

        mockMvc.perform(get("/api/ratings/{id}", ratingId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.review").value("Excellent movie!"));
    }

    @Test
    void testGetAllRatings() throws Exception {
        List<RatingDTO> dto = new ArrayList<>();
        dto.add(sampleDTO);
        Mockito.when(ratingService.getAllRatings()).thenReturn(dto);

        mockMvc.perform(get("/api/ratings"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].rating").value(8.5));
    }

    @Test
    void testDeleteRating() throws Exception {
        Mockito.doNothing().when(ratingService).deleteRating(ratingId);

        mockMvc.perform(delete("/api/ratings/{id}", ratingId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").value("Rating deleted successfully"));
    }
}
