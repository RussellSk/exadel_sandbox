package com.exadel.team2.sandbox.controller;

import com.exadel.team2.sandbox.service.ImageService;
import com.exadel.team2.sandbox.web.image.ImageResponseDTO;
import com.exadel.team2.sandbox.web.image.ImageUpdateDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.multipart.MultipartFile;


import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = {ImageController.class})
class ImageControllerTest {

    private static final long IMAGE_ID = 4L;

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
        when(imageService.getById(IMAGE_ID)).thenReturn(responseDto());

        mockMvc.perform(get("/image/{id}", IMAGE_ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(responseDto())))
                .andExpect(status().isOk());
    }

    @Test
    void getAll_imagesInDb_ok() throws Exception {
        List<ImageResponseDTO> imageEntities = createImageDto();
        when(imageService.getAll()).thenReturn(imageEntities);

        mockMvc.perform(get("/image/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(imageEntities)))
                .andExpect(status().isOk());
    }


//    @Test
//    void save_imagesInDB_ok() throws Exception {
//        MultipartFile image = any();
//        when(imageService.save(any(), image) ).thenReturn(responseDto());
//
//        mockMvc.perform(post("/image/upload")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(responseDto())))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(content().json(objectMapper.writeValueAsString(responseDto())));
//    }


    @Test
    void update_imageExists_ok() throws Exception {
        String json = objectMapper.writeValueAsString(updateDto());

        ImageUpdateDTO imageUpdateDTO = updateDto();
        when(imageService.update(IMAGE_ID, imageUpdateDTO)).thenReturn(responseDto());

        mockMvc.perform(put("/image/{id}", IMAGE_ID)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(json))
                .andExpect(status().isOk());

        verify(imageService, times(1)).update(IMAGE_ID, imageUpdateDTO);
    }


    @Test
    void deleteById_imageExists_ok() throws Exception {
        when(imageService.delete(IMAGE_ID)).thenReturn(true);

        mockMvc.perform(delete("/image/{id}", IMAGE_ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());
    }


    private ImageResponseDTO responseDto() {
        ImageResponseDTO imageDTO = new ImageResponseDTO();
        imageDTO.setId(IMAGE_ID);
        imageDTO.setName("Java cover");
        imageDTO.setAltText("Short description about image");
        imageDTO.setExt("jpg");
        imageDTO.setSize(5L);
        return imageDTO;
    }

    private ImageUpdateDTO updateDto() {
        ImageUpdateDTO updateDTO = new ImageUpdateDTO();
        updateDTO.setName("Java cover");
        updateDTO.setAltText("Short description about image");
        updateDTO.setExt("jpg");
        updateDTO.setSize(6L);
        return updateDTO;
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