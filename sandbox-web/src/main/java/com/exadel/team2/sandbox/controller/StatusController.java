package com.exadel.team2.sandbox.controller;

import com.exadel.team2.sandbox.service.StatusService;
import com.exadel.team2.sandbox.web.CreateStatusDTO;
import com.exadel.team2.sandbox.web.ResponseStatusDTO;
import com.exadel.team2.sandbox.web.UpdateStatusDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/status")
@RequiredArgsConstructor
public class StatusController {
    private final StatusService statusService;

    @GetMapping("/{id}")
    public ResponseStatusDTO findStatusById(@PathVariable("id") Long id) {
        return statusService.findById(id);
    }

    @GetMapping("/all")
    public Page<ResponseStatusDTO> findAll(@RequestParam(defaultValue = "0", name = "page") Integer page,
                                           @RequestParam(defaultValue = "15", name = "size") Integer size) {
        return statusService.findAll(PageRequest.of(page, size));
    }


    @PostMapping
    public ResponseStatusDTO saveStatus(@RequestBody CreateStatusDTO createStatusDTO) {
        return statusService.save(createStatusDTO);
    }

    @PutMapping("/{id}")
    public ResponseStatusDTO updateStatus(@PathVariable("id") Long id,
                                          @RequestBody UpdateStatusDTO updateStatusDTO) {

        return statusService.update(id, updateStatusDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteStatusById(@PathVariable("id") Long id) {
        statusService.deleteById(id);
    }
}
