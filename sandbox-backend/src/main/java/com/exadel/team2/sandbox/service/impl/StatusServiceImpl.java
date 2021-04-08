package com.exadel.team2.sandbox.service.impl;

import com.exadel.team2.sandbox.dao.StatusDAO;
import com.exadel.team2.sandbox.entity.Status;
import com.exadel.team2.sandbox.mapper.StatusMapperDTO;
import com.exadel.team2.sandbox.service.StatusService;
import com.exadel.team2.sandbox.web.status.CreateStatusDTO;
import com.exadel.team2.sandbox.web.status.ResponseStatusDTO;
import com.exadel.team2.sandbox.web.status.UpdateStatusDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class StatusServiceImpl implements StatusService {
    private final StatusDAO dao;
    private final StatusMapperDTO statusMapper;

    @Override
    public ResponseStatusDTO findById(Long id) {
        Status status = dao.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Status Not Found!"));

        return statusMapper.convertEntityToDto(status);
    }

    @Override
    public Page<ResponseStatusDTO> findAllPageable(Pageable pageable) {
        return dao.findAll(pageable).map(statusMapper::convertEntityToDto);
    }

    @Override
    public List<ResponseStatusDTO> findAll() {

        List<Status> statusList = dao.findAll();
        if (statusList.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No Content!");
        }
        return statusList.stream()
                .map(statusMapper::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ResponseStatusDTO save(CreateStatusDTO createStatusDTO) {
        if (createStatusDTO == null) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Status is null!");
        }
        Status status = statusMapper.convertDtoToEntity(createStatusDTO);

        return statusMapper.convertEntityToDto(dao.save(status));
    }

    @Override
    public ResponseStatusDTO update(Long id, UpdateStatusDTO updateStatusDTO) {
        if (updateStatusDTO == null) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No content!");
        }
        // Status status = dao.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Status not found to update!"));

//        status.setName(updateStatusDTO.getName());
//        status.setDescription(updateStatusDTO.getDescription());
//        status.setUpdatedAt(LocalDateTime.now());
        Status status = statusMapper.convertDtoToEntity(updateStatusDTO);
        status.setId(id);
        return statusMapper.convertEntityToDto(dao.save(status));
    }

    @Override
    public void deleteById(Long id) {
        dao.deleteById(id);
    }
}
