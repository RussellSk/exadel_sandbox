package com.exadel.team2.sandbox.controller;

import com.exadel.team2.sandbox.entity.ImageEntity;
import com.exadel.team2.sandbox.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/image")
public class ImageController {

    private final ImageService imageService;

    @GetMapping("/{id}")
    public ImageEntity getImage(@PathVariable Long id) {
        return imageService.getById(id);
    }

    @GetMapping("/all")
    public List<ImageEntity> gelAllImage() {
        return imageService.getAll();
    }

    @PostMapping
    public ImageEntity saveImage(@PathVariable Long id, ImageEntity imageEntity) {
        return imageService.save(imageEntity);
    }

    @PutMapping("/{id}")
    public ImageEntity updateImage(@PathVariable Long id, @RequestBody ImageEntity imageEntity) {
        imageEntity.setImgId(id);
        return imageService.update(imageEntity);
    }

    @DeleteMapping("/{id}")
    public void deleteImage(@PathVariable Long id) {
        imageService.delete(id);
    }

}
