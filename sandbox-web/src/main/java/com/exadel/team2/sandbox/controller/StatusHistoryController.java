package com.exadel.team2.sandbox.controller;


import com.exadel.team2.sandbox.service.StatusHistoryService;
import com.exadel.team2.sandbox.web.statushistory.CreateStatusHistoryDTO;
import com.exadel.team2.sandbox.web.statushistory.ResponseStatusHistoryDTO;
import com.exadel.team2.sandbox.web.statushistory.UpdateStatusHistoryDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/status/history")
@RequiredArgsConstructor
public class StatusHistoryController {

    private final StatusHistoryService historyService;

    @GetMapping("/{id}")
    public ResponseStatusHistoryDTO findStatusHistoryById(@PathVariable("id") Long id) {
        return historyService.findById(id);
    }

    @GetMapping("/all")
    public Page<ResponseStatusHistoryDTO> findAllStatusHistoryPageable(@RequestParam(defaultValue = "0", name = "page") Integer page,
                                                                       @RequestParam(defaultValue = "9", name = "size") Integer size,
                                                                       @RequestParam(value = "q", required = false) String query) {
        return historyService.findAllPageable(PageRequest.of(page, size),query);
    }

    @PostMapping
    public ResponseStatusHistoryDTO saveStatusHistory(@Validated @RequestBody CreateStatusHistoryDTO statusHistoryDTO) {
        return historyService.save(statusHistoryDTO);
    }

    @PutMapping("/{id}")
    public ResponseStatusHistoryDTO updateStatusHistory(@PathVariable("id") Long id,
                                                        @Validated @RequestBody UpdateStatusHistoryDTO updateStatusHistoryDTO) {
        return historyService.update(id, updateStatusHistoryDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteStatusHistoryById(@PathVariable("id") Long id) {
        historyService.deleteById(id);
    }


}
