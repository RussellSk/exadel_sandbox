package com.exadel.team2.sandbox.controller;


import com.exadel.team2.sandbox.dto.InterviewTimeCreateDTO;
import com.exadel.team2.sandbox.dto.InterviewTimeResponseDTO;
import com.exadel.team2.sandbox.dto.InterviewTimeUpdateDTO;
import com.exadel.team2.sandbox.entity.InterviewTimeEntity;
import com.exadel.team2.sandbox.service.impl.InterviewTimeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
    public List<InterviewTimeResponseDTO> getAllInterviewTime(
            @RequestParam(defaultValue = "", name = "search") String search,
            @RequestParam(defaultValue = "0", name = "page") Integer page,
            @RequestParam(defaultValue = "15", name = "itemsPerPage") Integer itemsPerPage) {
        return interviewTimeService.getAllPageable(PageRequest.of(page, itemsPerPage), search);
    }


    @PostMapping
    public InterviewTimeCreateDTO addInterviewTime(
            @RequestBody InterviewTimeCreateDTO interviewTimeCreateDTO) {
        return interviewTimeService.save(interviewTimeCreateDTO);
    }

    @PutMapping(value = "/{id}")
    public InterviewTimeUpdateDTO updateInterviewTime(
            @PathVariable Long id,
            @RequestBody InterviewTimeUpdateDTO interviewTimeUpdateDTO) {
        return interviewTimeService.update(id, interviewTimeUpdateDTO);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Long id) {
        interviewTimeService.delete(id);
    }
}
