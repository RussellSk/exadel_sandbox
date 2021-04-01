package com.exadel.team2.sandbox.controller;


import com.exadel.team2.sandbox.entity.StatusHistory;
import com.exadel.team2.sandbox.service.StatusHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/status/history")
@RequiredArgsConstructor
public class StatusHistoryController {

    private final StatusHistoryService historyService;

    @GetMapping("/{id}")
    public StatusHistory findStatusHistoryById(@PathVariable("id") Long id) {
        return historyService.findById(id);
    }

    @GetMapping("/all")
    public List<StatusHistory> findAllStatusHistory() {
        return historyService.findAll();
    }

    @PostMapping
    public StatusHistory saveStatusHistory(@RequestBody StatusHistory statusHistory) {
        return historyService.save(statusHistory);
    }

    @PutMapping("/{id}")
    public StatusHistory updateStatusHistory(@PathVariable("id") Long id,
                                             @RequestBody StatusHistory statusHistory) {
        statusHistory.setId(id);
        return historyService.update(statusHistory);
    }

    @DeleteMapping("/{id}")
    public void deleteStatusHistoryById(@PathVariable("id") Long id) {
        historyService.deleteById(id);
    }


}
