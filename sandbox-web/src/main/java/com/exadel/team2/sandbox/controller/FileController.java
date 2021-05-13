package com.exadel.team2.sandbox.controller;

import com.exadel.team2.sandbox.service.FileUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/file", produces = "text/html;charset=UTF-8")
@RequiredArgsConstructor
public class FileController {

    private final FileUploadService fileUploadService;

    @GetMapping(value = "{filename:.+}", produces = "text/html;charset=UTF-8")
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = fileUploadService.loadAsResource(filename);

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + filename + "\"").body(file);
    }
}
