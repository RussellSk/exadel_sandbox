package com.exadel.team2.sandbox.controller;

import com.exadel.team2.sandbox.entity.Status;
import com.exadel.team2.sandbox.service.StatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/status")
@RequiredArgsConstructor
public class StatusController {
    private final StatusService statusService;

    @GetMapping("/{id}")
    public Status findStatusById(@PathVariable("id") Long id) {
        return statusService.findById(id);
    }

    @GetMapping("/all")
    public List<Status> findAllStatus() {
        return statusService.findAll();
    }

    @PostMapping
    public Status saveStatus(@RequestBody Status status) {
        return statusService.save(status);
    }

    @PutMapping("/{id}")
    public Status updateStatus(@PathVariable("id") Long id,
                               @RequestBody Status status) {
        status.setId(id);
        return statusService.update(status);
    }

    @DeleteMapping("/{id}")
    public void deleteStatusById(@PathVariable("id") Long id) {
        statusService.deleteById(id);
    }
}
