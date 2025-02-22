package com.exadel.team2.sandbox.controller;

import com.exadel.team2.sandbox.service.StatusService;
import com.exadel.team2.sandbox.web.status.CreateStatusDTO;
import com.exadel.team2.sandbox.web.status.ResponseStatusDTO;
import com.exadel.team2.sandbox.web.status.UpdateStatusDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
    public Page<ResponseStatusDTO> findAllPageable(
            @RequestParam(defaultValue = "", value = "search") String query,
            @RequestParam(defaultValue = "0", name = "page") Integer page,
            @RequestParam(defaultValue = "9", name = "itemsPerPage") Integer size) {
        return statusService.findAllPageable(PageRequest.of(page, size), query);
    }


    @PostMapping
    public ResponseStatusDTO saveStatus(@Validated @RequestBody CreateStatusDTO createStatusDTO) {
        return statusService.save(createStatusDTO);
    }

    @PutMapping("/{id}")
    public ResponseStatusDTO updateStatus(@PathVariable("id") Long id,
                                          @Validated @RequestBody UpdateStatusDTO updateStatusDTO) {

        return statusService.update(id, updateStatusDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStatusById(@PathVariable("id") Long id) {
        if (statusService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        statusService.deleteById(id);

        return ResponseEntity.ok().build();
    }
}
