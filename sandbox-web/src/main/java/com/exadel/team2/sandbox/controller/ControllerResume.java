package com.exadel.team2.sandbox.controller;

import com.exadel.team2.sandbox.dto.*;
import com.exadel.team2.sandbox.service.impl.CandidateServiceImpl;
import com.exadel.team2.sandbox.service.impl.ResumeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/resume")
@RequiredArgsConstructor
public class ControllerResume {

    private final ResumeServiceImpl resumeService;

    @GetMapping(value = "/{id}")
    public ResumeResponseDTO getResume(@PathVariable Long id) {
        return resumeService.getById(id);
    }

    @GetMapping
    public List<ResumeResponseDTO> getAll(
            @PageableDefault(sort = "id", size = 15, direction = Sort.Direction.ASC) Pageable pageable,
            @RequestParam(value = "search") String search) {

        return resumeService.getAllPageable(pageable, search);
    }

    @PostMapping
    public ResumeResponseDTO addResume(
            @RequestParam("id") Long candidateId,
            @RequestParam(value = "file", required = false) MultipartFile file,
            @RequestParam(value = "link", required = false) String link) {
        return resumeService.save(candidateId, file, link);
    }

    @PutMapping(value = "/{id}")
    public ResumeUpdateDTO updateResume(
            @PathVariable Long id,
            @RequestBody ResumeUpdateDTO resumeUpdateDTO) {
        return resumeService.update(id, resumeUpdateDTO);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteResume(@PathVariable Long id) {
        resumeService.delete(id);
    }
}
