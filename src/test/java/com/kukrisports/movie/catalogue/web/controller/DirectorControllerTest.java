package com.kukrisports.movie.catalogue.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kukrisports.movie.catalogue.model.Director;
import com.kukrisports.movie.catalogue.service.DirectorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DirectorController.class)
public class DirectorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private DirectorService directorService;

    private ObjectMapper objectMapper;
    private UUID directorId;
    private Director sampleDirector;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        directorId = UUID.randomUUID();
        sampleDirector = new Director();
        sampleDirector.setId(directorId);
        sampleDirector.setName("Christopher Nolan");
        sampleDirector.setBiography("Famous for Inception");
        sampleDirector.setBirthdate(new Date());
    }

    @Test
    void testCreateDirector() throws Exception {
        Mockito.when(directorService.createDirector(any(Director.class))).thenReturn(sampleDirector);

        mockMvc.perform(post("/api/directors")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(sampleDirector)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.name").value("Christopher Nolan"));
    }

    @Test
    void testUpdateDirector() throws Exception {
        Mockito.when(directorService.updateDirector(eq(directorId), any(Director.class))).thenReturn(sampleDirector);

        mockMvc.perform(put("/api/directors/" + directorId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(sampleDirector)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.name").value("Christopher Nolan"));
    }

    @Test
    void testGetDirectorById() throws Exception {
        Mockito.when(directorService.getDirectorById(directorId)).thenReturn(sampleDirector);

        mockMvc.perform(get("/api/directors/" + directorId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.name").value("Christopher Nolan"));
    }

    @Test
    void testGetAllDirectors() throws Exception {
        List<Director> directors = Collections.singletonList(sampleDirector);
        Mockito.when(directorService.getAllDirectors()).thenReturn(directors);

        mockMvc.perform(get("/api/directors"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].name").value("Christopher Nolan"));
    }

    @Test
    void testDeleteDirector() throws Exception {
        Mockito.doNothing().when(directorService).deleteDirector(directorId);

        mockMvc.perform(delete("/api/directors/" + directorId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").value("Director deleted successfully"));
    }
}
