package com.exadel.team2.sandbox.controller;


import com.exadel.team2.sandbox.dto.InterviewTimeCreateDTO;
import com.exadel.team2.sandbox.dto.InterviewTimeResponseDTO;
import com.exadel.team2.sandbox.dto.InterviewTimeUpdateDTO;
import com.exadel.team2.sandbox.entity.InterviewTimeEntity;
import com.exadel.team2.sandbox.service.impl.InterviewTimeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/interviewtime")
@RequiredArgsConstructor
public class ControllerInterviewTime {

    private final InterviewTimeServiceImpl interviewTimeService;

    @GetMapping(value = "/{id}")
    public InterviewTimeResponseDTO getInterviewTime(@PathVariable Long id) {
        return interviewTimeService.findById(id);
    }

    @GetMapping
    public List<InterviewTimeResponseDTO> getAllInterviewTime() {
        return interviewTimeService.getAll();
    }


    @PostMapping
    public InterviewTimeCreateDTO addInterviewTime(
            @RequestBody InterviewTimeCreateDTO interviewTimeCreateDTO) {
        return interviewTimeService.save(interviewTimeCreateDTO);
    }

    @PutMapping
    public InterviewTimeUpdateDTO updateInterviewTime(
            @RequestBody InterviewTimeUpdateDTO interviewTimeUpdateDTO) {
        return interviewTimeService.update(interviewTimeUpdateDTO);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Long id) {
        interviewTimeService.delete(id);
    }
}
