package com.exadel.team2.sandbox.service;

import com.exadel.team2.sandbox.web.statushistory.CreateStatusHistoryDTO;
import com.exadel.team2.sandbox.web.statushistory.ResponseStatusHistoryDTO;
import com.exadel.team2.sandbox.web.statushistory.UpdateStatusHistoryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StatusHistoryService {
    ResponseStatusHistoryDTO findById(Long id);

    Page<ResponseStatusHistoryDTO> findAllPageable(Pageable pageable);

    List<ResponseStatusHistoryDTO> findAll();

    ResponseStatusHistoryDTO save(CreateStatusHistoryDTO createStatusHistoryDTO);

    ResponseStatusHistoryDTO update(Long id, UpdateStatusHistoryDTO updateStatusHistoryDTO);

    void deleteById(Long id);

}
