package com.exadel.team2.sandbox.service.impl;

import com.exadel.team2.sandbox.dao.ImageDAO;
import com.exadel.team2.sandbox.entity.ImageEntity;
import com.exadel.team2.sandbox.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageDAO imageDAO;

    @Override
    public ImageEntity getById(Long imgId) {
        return imageDAO.findById(imgId).orElse(null);
    }

    @Override
    public List<ImageEntity> getAll() {
        return imageDAO.findAll();
    }

    @Override
    public ImageEntity save(ImageEntity imageEntity) {
        return imageDAO.save(imageEntity);
    }

    @Override
    public ImageEntity update(ImageEntity imageEntity) {
        return imageDAO.save(imageEntity);
    }

    @Override
    public void delete(Long imgId) {
        imageDAO.deleteById(imgId);
    }
}
