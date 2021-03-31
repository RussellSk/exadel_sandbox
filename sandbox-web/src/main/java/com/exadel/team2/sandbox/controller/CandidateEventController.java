package com.exadel.team2.sandbox.controller;

import com.exadel.team2.sandbox.entity.CandidateEventEntity;
import com.exadel.team2.sandbox.service.CandidateEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/candidate-event")
@RequiredArgsConstructor
public class CandidateEventController {
    private final CandidateEventService candidateEventService;

    @GetMapping("/{id}")
    public CandidateEventEntity getCandidateEvent(@PathVariable Long id) {
        return candidateEventService.getById(id);
    }

    @GetMapping("/all")
    public List<CandidateEventEntity> getAllCandidateEvent() {
        return candidateEventService.getAll();
    }

    @PostMapping
    public CandidateEventEntity saveCandidateEvent(@RequestBody CandidateEventEntity entity) {
        return candidateEventService.save(entity);
    }

    @PutMapping("/{id}")
    public CandidateEventEntity updateCandidateEvent(@PathVariable Long id, @RequestBody CandidateEventEntity entity) {
        entity.setId(id);
        return candidateEventService.update(entity);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        candidateEventService.delete(id);
    }
}
