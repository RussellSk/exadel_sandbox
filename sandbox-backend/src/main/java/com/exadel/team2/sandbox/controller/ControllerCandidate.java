package com.exadel.team2.sandbox.controller;

import com.exadel.team2.sandbox.dto.CandidateResponseDTO;
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

//    @GetMapping(value = "get/{CN_ID}")
//    public CandidateEntity getCandidate1(@PathVariable Long CN_ID) {
//        return candidateService.findById(CN_ID);
//    }

    @GetMapping(value = "get/{CN_ID}")
    public CandidateResponseDTO getCandidate(@PathVariable Long CN_ID) {
        return candidateService.findById(CN_ID);
    }

    @GetMapping(value = "getAll")
    public List<CandidateEntity> getAllCandidates() {
        return candidateService.getAll();
    }


    @PostMapping(value = "add")
    public CandidateEntity addCandidate(
            CandidateEntity candidateEntity) {
        return candidateService.save(candidateEntity);
    }

    @PutMapping(value = "update")
    public CandidateEntity updateUpdateCandidate(
            CandidateEntity candidateEntity) {
        return candidateService.update(candidateEntity);
    }

    @DeleteMapping(value = "delete/{CN_ID}")
    public void delete(Long CN_ID) {
        candidateService.delete(CN_ID);
    }


}
