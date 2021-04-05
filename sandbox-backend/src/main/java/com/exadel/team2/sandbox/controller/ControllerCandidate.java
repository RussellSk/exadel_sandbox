package com.exadel.team2.sandbox.controller;

import com.exadel.team2.sandbox.entity.CandidateEntity;
import com.exadel.team2.sandbox.service.impl.CandidateServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/candidate")
@RequiredArgsConstructor
public class ControllerCandidate {

    private final CandidateServiceImpl candidateService;

    @GetMapping(value = "get/{CN_ID}")
    public CandidateEntity setInterviewTime(@PathVariable Long CN_ID) {
        return candidateService.findById(CN_ID);
    }

    @GetMapping(value = "getAll")
    public List<CandidateEntity> getAllInterviewTime() {
        return candidateService.getAll();
    }


    @PostMapping(value = "add")
    public CandidateEntity addInterviewTime(
            CandidateEntity candidateEntity) {
        return candidateService.save(candidateEntity);
    }

    @PostMapping(value = "update")
    public CandidateEntity updateInterviewTime(
            CandidateEntity candidateEntity) {
        return candidateService.update(candidateEntity);
    }

    @DeleteMapping(value = "delete/{CN_ID}")
    public void delete(Long CN_ID) {
        candidateService.delete(CN_ID);
    }

    public void testBuilder() {
        CandidateEntity candidateEntity = CandidateEntity.builder()
                .cn_first_name("///")
                .cn_last_name("///")
                .build();
    }

}
