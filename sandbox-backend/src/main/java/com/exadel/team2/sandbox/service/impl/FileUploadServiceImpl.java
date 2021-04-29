package com.exadel.team2.sandbox.service.impl;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.exadel.team2.sandbox.service.FileUploadService;

@Service
public class FileUploadServiceImpl implements FileUploadService {

    @Value("${app.upload.dir}")
    public String uploadDir;

    @Override
    public void uploadFile(MultipartFile file) {

        try {
            Path location = Paths.get(uploadDir + File.separator + StringUtils.cleanPath(file.getOriginalFilename()));
            Files.copy(file.getInputStream(), location, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Could not store file " + file.getOriginalFilename());
        }
    }

    @Override
    public List<String> uploadedList() {
        return Arrays.asList(new File(uploadDir).list());
    }
}
