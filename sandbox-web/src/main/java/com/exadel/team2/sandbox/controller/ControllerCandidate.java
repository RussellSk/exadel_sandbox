package com.exadel.team2.sandbox.controller;

import com.exadel.team2.sandbox.dto.CandidateCreateDTO;
import com.exadel.team2.sandbox.dto.CandidateResponseDTO;
import com.exadel.team2.sandbox.dto.CandidateUpdateDTO;
import com.exadel.team2.sandbox.service.CandidateAvailabilityTimeService;
import com.exadel.team2.sandbox.service.impl.CandidateServiceImpl;
import com.exadel.team2.sandbox.web.canidate_availability_time.CreateCandidateAvailabilityTimeDto;
import com.exadel.team2.sandbox.web.canidate_availability_time.ResponseCandidateAvailabilityTimeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/candidate")
@RequiredArgsConstructor
public class ControllerCandidate {

    private final CandidateServiceImpl candidateService;
    private final CandidateAvailabilityTimeService candidateAvailabilityTimeService;


    @GetMapping(value = "/{id}")
    public CandidateResponseDTO getCandidate(@PathVariable Long id) {
        return candidateService.findById(id);
    }

    @GetMapping
    public Page<CandidateResponseDTO> getAllCandidates(
            @RequestParam(defaultValue = "", name = "search") String search,
            @RequestParam(defaultValue = "0", name = "page") Integer page,
            @RequestParam(defaultValue = "15", name = "itemsPerPage") Integer itemsPerPage) {

        return candidateService.getAllPageable(PageRequest.of(page, itemsPerPage), search);
    }


    @PostMapping
    public CandidateResponseDTO addCandidate(
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

    @GetMapping("/{candidateId}/availability")
    public ResponseCandidateAvailabilityTimeDto getCandidateAvailabilityTime(@PathVariable Long candidateId) {
        return candidateAvailabilityTimeService.getByCandidateId(candidateId);
    }

    @PostMapping("/availability")
    public ResponseCandidateAvailabilityTimeDto createCandidateAvailabilityTime(
            @RequestBody CreateCandidateAvailabilityTimeDto candidateAvailabilityTimeDto) {
        return candidateAvailabilityTimeService.save(candidateAvailabilityTimeDto);
    }

    @DeleteMapping("/{candidateId}/availability")
    public ResponseEntity<?> deleteCandidateSlots(@PathVariable Long candidateId) {
        candidateAvailabilityTimeService.deleteAllByCandidateId(candidateId);
        return ResponseEntity.ok().build();
    }

}
