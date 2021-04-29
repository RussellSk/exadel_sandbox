package com.exadel.team2.sandbox.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.exadel.team2.sandbox.service.FileUploadService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/file/upload")
@RequiredArgsConstructor
public class TestUploadController {

    private final FileUploadService fileUploadService;

    @PostMapping
    public void uploadFile(@RequestParam("file") MultipartFile file) {
        fileUploadService.uploadFile(file);
    }

    @GetMapping("/list")
    public List<String> uploaded() {
        return fileUploadService.uploadedList();
    }
}
