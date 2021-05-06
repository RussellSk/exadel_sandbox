package com.exadel.team2.sandbox.controller;

import com.exadel.team2.sandbox.service.ImageService;
import com.exadel.team2.sandbox.web.image.ImageResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ImageController.class)
class ImageControllerTest {

//    @Autowired need?
    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @MockBean
    private ImageService imageService;

    @Autowired
    private ImageController imageController;

    @BeforeEach
    void setUp() {
        imageService = mock(ImageService.class);
        mockMvc = MockMvcBuilders.standaloneSetup(new ImageController(imageService)).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void getById_imageExists_ok() throws Exception {
        when(imageService.getById(5L)).thenReturn(responseDto());

        mockMvc.perform(get("/image/5")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(responseDto())))
                .andExpect(status().isOk());
    }

    @Test
    void gelAll_imagesInDb_ok() throws Exception {
        List<ImageResponseDTO> imageEntities = createImageDto();
        when(imageService.getAll()).thenReturn(imageEntities);

        mockMvc.perform(get("/image/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(imageEntities)))
                .andExpect(status().isOk());
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

    @Test
    void deleteById_imageExists_ok() throws Exception {
        when(imageService.delete(5L)).thenReturn(true);

        mockMvc.perform(delete("/image/5")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());
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

    private List<ImageResponseDTO> createImageDto() {
        ImageResponseDTO imageResponseDTO1 = new ImageResponseDTO();
        imageResponseDTO1.setId(1L);

        ImageResponseDTO imageResponseDTO2 = new ImageResponseDTO();
        imageResponseDTO2.setId(2L);

        ImageResponseDTO imageResponseDTO3 = new ImageResponseDTO();
        imageResponseDTO3.setId(3L);

        List<ImageResponseDTO> imageResponseDTOs = new ArrayList<ImageResponseDTO>();
        imageResponseDTOs.add(imageResponseDTO1);
        imageResponseDTOs.add(imageResponseDTO2);
        imageResponseDTOs.add(imageResponseDTO3);

        return imageResponseDTOs;
    }
}