package com.exadel.team2.sandbox.controller;

import com.exadel.team2.sandbox.dto.CandidateCreateDTO;
import com.exadel.team2.sandbox.dto.CandidateResponseDTO;
import com.exadel.team2.sandbox.dto.CandidateUpdateDTO;
import com.exadel.team2.sandbox.service.impl.CandidateServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/candidate")
@RequiredArgsConstructor
public class ControllerCandidate {

    private final CandidateServiceImpl candidateService;

    @GetMapping(value = "/{id}")
    public CandidateResponseDTO getCandidate(@PathVariable Long id) {
        return candidateService.findById(id);
    }

    @GetMapping
    public List<CandidateResponseDTO> getAllCandidates(
            @PageableDefault(sort = {"lastName"}, size = 2, direction = Sort.Direction.ASC) Pageable pageable,
            @RequestParam(value = "search", defaultValue = "", required = false) String search) {

        if (search.isEmpty() || search == null) {
            return candidateService.getAll(pageable);
        }

        return candidateService.getAllPageable(pageable, search);
    }


    @PostMapping
    public CandidateCreateDTO addCandidate(
            @RequestBody CandidateCreateDTO candidateCreateDTO) {
        return candidateService.save(candidateCreateDTO);
    }

    @PutMapping(value = "/{id}")
    public CandidateUpdateDTO updateUpdateCandidate(
            @PathVariable Long id,
            @RequestBody CandidateUpdateDTO candidateUpdateDTO) {
        return candidateService.update(id, candidateUpdateDTO);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Long id) {
        candidateService.delete(id);
    }


}
