package com.exadel.team2.sandbox.controller;

import com.exadel.team2.sandbox.mapper.Mapper;
import com.exadel.team2.sandbox.service.ImageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ImageControllerTest {

    MockMvc mockMvc;
    Mapper mapper;
    ImageService imageService;

    @BeforeEach
    void setUp() {
        imageService = mock(ImageService.class);
//        mapper = new Mapper();
    }

    @Test
    void getImageById() {
    }
}