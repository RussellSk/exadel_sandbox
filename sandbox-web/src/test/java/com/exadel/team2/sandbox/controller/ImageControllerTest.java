package com.exadel.team2.sandbox.controller;

import com.exadel.team2.sandbox.service.ImageService;
import com.exadel.team2.sandbox.web.image.ImageResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ImageControllerTest {

    MockMvc mockMvc;
    ObjectMapper objectMapper;
    ImageService imageService;

    @BeforeEach
    void setUp() {
        imageService = mock(ImageService.class);
        mockMvc = MockMvcBuilders.standaloneSetup(new ImageController(imageService)).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void save_imagesInDB_ok() throws Exception {
        when(imageService.save(any())).thenReturn(responseDto());

        mockMvc.perform(post("/image")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(responseDto())))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(responseDto())));
    }


    private ImageResponseDTO responseDto() {
        ImageResponseDTO imageDTO = new ImageResponseDTO();
        imageDTO.setId(5L);
        imageDTO.setImageName("Java cover");
        imageDTO.setAltText("Short description about image");
        imageDTO.setExt("jpg");
        imageDTO.setPath("local path");
        imageDTO.setSize(5);

        return imageDTO;
    }

//    private ImageEntity saveImageEntity() {
//        ImageEntity imageEntity = new ImageEntity();
//        imageEntity.setImageName("Java cover");
//        imageEntity.setAltText("Short description about image");
//        imageEntity.setExt("jpg");
//        imageEntity.setPath("local path");
//        imageEntity.setSize(5);
//
//        return imageEntity;
//    }
}