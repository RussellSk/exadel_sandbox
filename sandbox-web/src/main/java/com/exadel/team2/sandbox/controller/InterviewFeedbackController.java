package com.exadel.team2.sandbox.controller;

import com.exadel.team2.sandbox.entity.InterviewFeedbackEntity;
import com.exadel.team2.sandbox.service.InterviewFeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/interview-feedback")
@RequiredArgsConstructor
public class InterviewFeedbackController {
private final InterviewFeedbackService interviewFeedbackService;

    @GetMapping("/{id}")
    public InterviewFeedbackEntity getInterviewFeedback(@PathVariable Long id) {
        return interviewFeedbackService.getById(id);
    }

    @GetMapping("/all")
    public List<InterviewFeedbackEntity> getAllInterviewFeedback() {
        return interviewFeedbackService.getAll();
    }

    @PostMapping
    public InterviewFeedbackEntity saveInterviewFeedback(@RequestBody InterviewFeedbackEntity entity) {
        return interviewFeedbackService.save(entity);
    }

    @PutMapping("/{id}")
    public InterviewFeedbackEntity updateInterviewFeedback(@PathVariable Long id, @RequestBody InterviewFeedbackEntity entity) {
        entity.setId(id);
        return interviewFeedbackService.update(entity);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        interviewFeedbackService.delete(id);
    }

}
