package com.exadel.team2.sandbox.controller;

import com.exadel.team2.sandbox.dto.ResumeCreateDTO;
import com.exadel.team2.sandbox.dto.ResumeResponseDTO;
import com.exadel.team2.sandbox.dto.ResumeUpdateDTO;
import com.exadel.team2.sandbox.dto.UploadFileResponse;
import com.exadel.team2.sandbox.service.impl.ResumeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

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
    public ResumeCreateDTO addResume(
            @RequestBody ResumeCreateDTO resumeCreateDTO) {
        UploadFileResponse response = null;

        if (resumeCreateDTO.getFile() != null) {
            response = new FileController().uploadFile(resumeCreateDTO.getFile());

            return resumeService.save(ResumeCreateDTO.builder()
                    .ext(response.getFileType())
                    .name(response.getFileName())
                    .size(response.getSize())
                    .build());
        }

        return resumeService.save(resumeCreateDTO);
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
