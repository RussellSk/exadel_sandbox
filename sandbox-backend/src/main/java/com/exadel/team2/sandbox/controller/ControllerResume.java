package com.exadel.team2.sandbox.controller;

import com.exadel.team2.sandbox.entity.ResumeEntity;
import com.exadel.team2.sandbox.service.impl.ResumeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/resume")
@RequiredArgsConstructor
public class ControllerResume {

    private final ResumeServiceImpl resumeService;

    @GetMapping(value = "get/{RSM_ID}")
    public ResumeEntity getResume(@PathVariable Long RSM_ID) {
        ResumeEntity resumeEntity = resumeService.getById(RSM_ID);
        System.out.println(resumeEntity);
        return resumeEntity;
    }

    @GetMapping(value = "getAll")
    public List<ResumeEntity> getAll() {
        return resumeService.getAll();
    }

    @PostMapping(value = "add")
    public ResumeEntity addResume(ResumeEntity resumeEntity) {
        return resumeService.save(resumeEntity);
    }

    @PutMapping(value = "update")
    public ResumeEntity updateResume(ResumeEntity resumeEntity) {
        return resumeService.update(resumeEntity);
    }

    @DeleteMapping(value = "delete/{RSM_ID}")
    public void deleteResume(@PathVariable Long RSM_ID) {
        resumeService.delete(RSM_ID);
    }
}
