package com.exadel.team2.sandbox.controller;


import com.exadel.team2.sandbox.service.StatusHistoryService;
import com.exadel.team2.sandbox.web.CreateStatusHistoryDTO;
import com.exadel.team2.sandbox.web.ResponseStatusHistoryDTO;
import com.exadel.team2.sandbox.web.UpdateStatusHistoryDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    public Page<ResponseStatusHistoryDTO> findAllStatusHistory(@RequestParam(defaultValue = "0", name = "page") Integer page,
                                                               @RequestParam(defaultValue = "15", name = "size") Integer size) {
        return historyService.findAll(PageRequest.of(page, size));
    }

    @PostMapping
    public ResponseStatusHistoryDTO saveStatusHistory(@RequestBody CreateStatusHistoryDTO statusHistoryDTO) {
        return historyService.save(statusHistoryDTO);
    }

    @PutMapping("/{id}")
    public ResponseStatusHistoryDTO updateStatusHistory(@PathVariable("id") Long id,
                                                        @RequestBody UpdateStatusHistoryDTO updateStatusHistoryDTO) {
        return historyService.update(id, updateStatusHistoryDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteStatusHistoryById(@PathVariable("id") Long id) {
        historyService.deleteById(id);
    }


}
