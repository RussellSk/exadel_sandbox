package com.exadel.team2.sandbox.controller;

import com.exadel.team2.sandbox.service.ImageService;
import com.exadel.team2.sandbox.web.ImageCreateDTO;
import com.exadel.team2.sandbox.web.ImageResponseDTO;
import com.exadel.team2.sandbox.web.ImageUpdateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ImageResponseDTO saveImage(@Validated @RequestBody ImageCreateDTO imageCreateDTO) {
        return imageService.save(imageCreateDTO);
    }

    @PutMapping("/{id}")
    public ImageResponseDTO updateImage(@Validated @PathVariable Long id, @RequestBody ImageUpdateDTO imageUpdateDTO) {
        return imageService.update(id, imageUpdateDTO);
    }

    @DeleteMapping("/{id}")
    public String deleteImage(@PathVariable Long id) {
        return imageService.delete(id);
    }

}
