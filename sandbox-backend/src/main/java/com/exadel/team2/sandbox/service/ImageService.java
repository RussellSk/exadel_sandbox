package com.exadel.team2.sandbox.service;

import com.exadel.team2.sandbox.entity.ImageEntity;
import java.util.List;

public interface ImageService {

    ImageEntity getById (Long imgId);

    List<ImageEntity> getAll();

    ImageEntity save(ImageEntity imageEntity);

    ImageEntity update(ImageEntity imageEntity);

    void delete(Long imgId);

}
