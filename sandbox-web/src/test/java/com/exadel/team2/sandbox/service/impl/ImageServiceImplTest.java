//package com.exadel.team2.sandbox.service.impl;
//
//import com.exadel.team2.sandbox.dao.ImageDAO;
//import com.exadel.team2.sandbox.entity.ImageEntity;
//import com.exadel.team2.sandbox.exceptions.NoSuchException;
//import com.exadel.team2.sandbox.mapper.ImageMapper;
//import com.exadel.team2.sandbox.service.ImageService;
//import com.exadel.team2.sandbox.web.image.ImageCreateDTO;
//import com.exadel.team2.sandbox.web.image.ImageResponseDTO;
//import org.junit.Assert;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.junit.runner.RunWith;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.context.junit4.SpringRunner;
////import static org.hamcrest.MatcherAssert.assertThat;
//
//import java.time.LocalDateTime;
//import java.util.Optional;
//
//import static org.junit.Assert.assertThat;
//import static org.junit.Assert.assertTrue;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//
////@RunWith(SpringRunner.class) //4 junit
////@ExtendWith(SpringExtension.class)
//@ExtendWith(MockitoExtension.class)
//@SpringBootTest
//public class ImageServiceImplTest {
//
//    @MockBean
//    private ImageDAO imageDAO;
//
//    @Autowired
//    private ImageService imageService;
//
//    private Optional<ImageEntity> getImageEntity() {
//        ImageEntity imageEntity = new ImageEntity();
//        imageEntity.setImageName("sf");
//        return Optional.of(imageEntity);
//    }
//
//
//
//    @Test
//    @DisplayName("Should get image to id")
//    void shouldGetImageToId() {
//
////        ImageServiceImpl imageServiceImpl = new ImageServiceImpl(imageDAO, imageMapper);
//
//        when(imageDAO.findById(123L)).thenReturn(getImageEntity());
//
//        ImageResponseDTO actualImageResponse = imageService.getById(123L);
//
//        Assertions.assertEquals(actualImageResponse.getId(), imageResponseDTO.getId());
//        Assertions.assertEquals(actualImageResponse.getImageName(), imageResponseDTO.getImageName());
//
//    }
//
//    @Test
//    void getAll() {
//    }
//
//    @Test
//    @DisplayName("Method should save image")
//    void shouldSaveImage() {
//
//    }
//
//    @Test
//    void update() {
//    }
//
//    @Test
//    void delete() {
//        ImageResponseDTO mockImage = new ImageResponseDTO(1L, "ownf some path", "java cover",
//                "jpg", 5, "short description", LocalDateTime.now());
//
//        when(imageDAO.findById(mockImage.getId())).thenReturn(Optional.of(imageEntity));
//
//        imageDAO.deleteById(mockImage.getId());
//    }
//
//    @Test
//    @DisplayName("Should thrown exception")
//    public void shouldThrownException() throws Exception {
//        assertThrows(NoSuchException.class, () -> {
//            Integer.parseInt("One");
//        });
//
////        assertTrue();
//    }
//}