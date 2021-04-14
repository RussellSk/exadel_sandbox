package com.exadel.team2.sandbox.controller;

import com.exadel.team2.sandbox.service.CandidateEventService;
import com.exadel.team2.sandbox.web.candidate_event.CreateCandidateEventDto;
import com.exadel.team2.sandbox.web.candidate_event.ResponseCandidateEventDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/candidate-event")
@RequiredArgsConstructor
public class CandidateEventController {
    private final CandidateEventService candidateEventService;

    @GetMapping("/{id}")
    public ResponseCandidateEventDto getCandidateEvent(@PathVariable Long id) {
        return candidateEventService.getById(id);
    }

    @GetMapping("/all")
    public Page<ResponseCandidateEventDto> getAllCandidateEvent(
            @RequestParam(value = "search") String search,
            @RequestParam(defaultValue = "0", name = "page") Integer page,
            @RequestParam(defaultValue = "15", name = "itemsPerPage") Integer itemsPerPage) {
        return candidateEventService.getAllPageable(PageRequest.of(page, itemsPerPage),search);
    }

    @PostMapping
    public ResponseCandidateEventDto createCandidateEvent(@Validated @RequestBody CreateCandidateEventDto createCandidateEventDto) {
        return candidateEventService.save(createCandidateEventDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCandidateEvent(@PathVariable Long id) {
        if (candidateEventService.getById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        candidateEventService.delete(id);
        return ResponseEntity.ok().build();
    }
}
