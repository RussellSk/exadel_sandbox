package com.exadel.team2.sandbox.controller;


import com.exadel.team2.sandbox.entity.InterviewTimeEntity;
import com.exadel.team2.sandbox.service.impl.InterviewTimeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/interviewTime")
@RequiredArgsConstructor
public class ControllerInterviewTime {

    private final InterviewTimeServiceImpl interviewTimeService;

    @GetMapping(value = "get/{ITM_ID}")
    public InterviewTimeEntity setInterviewTime(@PathVariable Long ITM_ID) {
        return interviewTimeService.findById(ITM_ID);
    }

    @GetMapping(value = "getAll")
    public List<InterviewTimeEntity> getAllInterviewTime() {
        return interviewTimeService.getAll();
    }


    @PostMapping(value = "add")
    public InterviewTimeEntity addInterviewTime(
            InterviewTimeEntity interviewTimeEntity) {
        return interviewTimeService.save(interviewTimeEntity);
    }

    @PostMapping(value = "update")
    public InterviewTimeEntity updateInterviewTime(
            InterviewTimeEntity interviewTimeEntity) {
        return interviewTimeService.update(interviewTimeEntity);
    }

    @DeleteMapping(value = "delete/{ITM_ID}")
    public void delete(Long ITM_ID) {
        interviewTimeService.delete(ITM_ID);
    }
}
