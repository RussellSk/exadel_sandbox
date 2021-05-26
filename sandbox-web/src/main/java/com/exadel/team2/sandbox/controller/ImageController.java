package com.exadel.team2.sandbox.controller;

import com.exadel.team2.sandbox.service.ImageService;
import com.exadel.team2.sandbox.web.image.ImageCreateDTO;
import com.exadel.team2.sandbox.web.image.ImageResponseDTO;
import com.exadel.team2.sandbox.web.image.ImageUpdateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/image")
public class ImageController {

    private final ImageService imageService;

    @GetMapping("/{id}")
    public ImageResponseDTO getImageById(@PathVariable Long id) {
        return imageService.getById(id);
    }

    @GetMapping("/all")
    public List<ImageResponseDTO> gelAllImages() {
        return imageService.getAll();
    }

    @PostMapping("/upload")
    public ImageResponseDTO saveImage(
            @RequestParam("id") Long eventId,
            @RequestParam(value = "altText", required = false) ImageCreateDTO imageCreateDTO,
            @RequestParam(value = "image", required = false) MultipartFile image) {
        return imageService.save(eventId, imageCreateDTO, image);
    }

    @PutMapping("/{id}")
    public ImageResponseDTO updateImage(@Validated @PathVariable Long id, @RequestBody ImageUpdateDTO
            imageUpdateDTO) {
        return imageService.update(id, imageUpdateDTO);
    }

    @DeleteMapping("/{id}")
    public Boolean deleteImage(@PathVariable Long id) {
        return imageService.delete(id);
    }

}
