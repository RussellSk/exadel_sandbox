package com.exadel.team2.sandbox.service;

import com.exadel.team2.sandbox.web.status.CreateStatusDTO;
import com.exadel.team2.sandbox.web.status.ResponseStatusDTO;
import com.exadel.team2.sandbox.web.status.UpdateStatusDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StatusService {

    ResponseStatusDTO findById(Long id);

    Page<ResponseStatusDTO> findAllPageable(Pageable pageable,String query);

    List<ResponseStatusDTO> findAll();

    ResponseStatusDTO save(CreateStatusDTO createStatusDTO);

    ResponseStatusDTO update(Long id, UpdateStatusDTO updateStatusDTO);

    void deleteById(Long id);
}
