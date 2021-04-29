package com.exadel.team2.sandbox.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {

    void uploadFile(MultipartFile file);

    List<String> uploadedList();

}
