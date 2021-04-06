package com.exadel.team2.sandbox.controller;

import com.exadel.team2.sandbox.service.InterviewFeedbackService;
import com.exadel.team2.sandbox.web.interview_feedback.CreateInterviewFeedbackDTO;
import com.exadel.team2.sandbox.web.interview_feedback.ResponseInterviewFeedbackDto;
import com.exadel.team2.sandbox.web.interview_feedback.UpdateInterviewFeedbackDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/interview-feedback")
@RequiredArgsConstructor
public class InterviewFeedbackController {
    private final InterviewFeedbackService interviewFeedbackService;

    @GetMapping("/{id}")
    public ResponseInterviewFeedbackDto getInterviewFeedback(@PathVariable Long id) {
        return interviewFeedbackService.getById(id);
    }

    @GetMapping
    public Page<ResponseInterviewFeedbackDto> getAllInterviewFeedback(
            @RequestParam(defaultValue = "0", name = "page") Integer page,
            @RequestParam(defaultValue = "15", name = "itemsPerPage") Integer itemsPerPage) {
        return interviewFeedbackService.getAllPageable(PageRequest.of(page, itemsPerPage));
    }

    @PostMapping
    public ResponseInterviewFeedbackDto createInterviewFeedback(@Validated @RequestBody CreateInterviewFeedbackDTO createInterviewFeedbackDTO) {
        return interviewFeedbackService.save(createInterviewFeedbackDTO);
    }

    @PutMapping("/{id}")
    public ResponseInterviewFeedbackDto updateInterviewFeedback(@PathVariable Long id, @Validated @RequestBody UpdateInterviewFeedbackDto updateInterviewFeedbackDto) {
        return interviewFeedbackService.update(id, updateInterviewFeedbackDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteInterviewFeedback(@PathVariable Long id) {
        if (interviewFeedbackService.getById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        interviewFeedbackService.delete(id);
        return ResponseEntity.ok().build();
    }

}
