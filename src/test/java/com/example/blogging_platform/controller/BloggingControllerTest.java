package com.example.blogging_platform.controller;

import com.example.blogging_platform.model.Blogging;
import com.example.blogging_platform.repository.BloggingRepository;
import com.example.blogging_platform.repository.BloggingRequestPayload;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = BloggingController.class)
public class BloggingControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BloggingRepository bloggingRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private Blogging blogging;

    @BeforeEach
    void setup() {
        blogging = new Blogging();
        blogging.setId(1);
        blogging.setTitle("Title test");
        blogging.setContent("Content test");
        blogging.setCategory("Category test");
    }

    @Test
    @DisplayName("Criação do post do BLogging")
    void createPostBlogging() throws Exception {
        List<String> tags = new ArrayList<>();
        tags.add("[tech, tech2, tech 3]");
        BloggingRequestPayload requestPayload = new BloggingRequestPayload("Title test", "Content test", "Category", tags, "2024-10-25T10:15:30", "2024-10-25T10:15:30");
        when(bloggingRepository.save(any(Blogging.class))).thenReturn(blogging);
        mockMvc.perform(post("/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestPayload)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value(blogging.getTitle()))
                .andExpect(jsonPath("$.content").value(blogging.getContent()));
    }

    @Test
    @DisplayName("Fazer update no blogging")
    void updateBlogging() throws Exception {
        List<String> tags = List.of("tech", "tech2", "tech3");

        BloggingRequestPayload requestPayload = new BloggingRequestPayload("Updated Title", "Updated Content", "Updated Category", tags, "2024-10-25T10:15:30", "2024-10-25T10:15:30");

        when(bloggingRepository.findById(1)).thenReturn(Optional.of(blogging));
        when(bloggingRepository.save(any(Blogging.class))).thenReturn(blogging);

        mockMvc.perform(put("/posts/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestPayload)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Updated Title"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("Busca todos o post do blogging")
    void getAllBlogging() throws Exception {
        when(this.bloggingRepository.findAll()).thenReturn(List.of(blogging));
        mockMvc.perform(get("/posts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value(blogging.getTitle()));
    }

    @Test
    @DisplayName("Deletando post")
    void deleteBlogging() throws Exception {
        when(this.bloggingRepository.findById(1)).thenReturn(Optional.of(blogging));
        mockMvc.perform(delete("/posts/{id}", blogging.getId()))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    @DisplayName("Busca de post por um termo")
    void searchPost() throws Exception {
        when(this.bloggingRepository.searchByTerm("test")).thenReturn(List.of(blogging));
        mockMvc.perform(get("/posts")
                        .param("term", "test")
                ).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value(blogging.getTitle()));
    }
}