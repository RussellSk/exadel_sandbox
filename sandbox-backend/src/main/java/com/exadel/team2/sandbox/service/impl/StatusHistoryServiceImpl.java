package com.exadel.team2.sandbox.service.impl;

import com.exadel.team2.sandbox.dao.EmployeeDAO;
import com.exadel.team2.sandbox.dao.StatusDAO;
import com.exadel.team2.sandbox.dao.StatusHistoryDAO;
import com.exadel.team2.sandbox.entity.EmployeeEntity;
import com.exadel.team2.sandbox.entity.Status;
import com.exadel.team2.sandbox.entity.StatusHistory;
import com.exadel.team2.sandbox.mapper.StatusHistoryMapperDTO;
import com.exadel.team2.sandbox.service.StatusHistoryService;
import com.exadel.team2.sandbox.web.statushistory.CreateStatusHistoryDTO;
import com.exadel.team2.sandbox.web.statushistory.ResponseStatusHistoryDTO;
import com.exadel.team2.sandbox.web.statushistory.UpdateStatusHistoryDTO;
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
public class StatusHistoryServiceImpl implements StatusHistoryService {

    private final StatusHistoryDAO historyDAO;
    private final StatusDAO statusDAO;
    private final EmployeeDAO employeeDAO;
    private final StatusHistoryMapperDTO historyMapper;

    @Override
    public ResponseStatusHistoryDTO findById(Long id) {
        StatusHistory statusHistory = historyDAO.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "StatusHistory not found!"));

        return historyMapper.convertEntityToDto(statusHistory);
    }

    @Override
    public Page<ResponseStatusHistoryDTO> findAllPageable(Pageable pageable) {
        return historyDAO.findAll(pageable).map(historyMapper::convertEntityToDto);

    }

    @Override
    public List<ResponseStatusHistoryDTO> findAll() {
        List<StatusHistory> historyList = historyDAO.findAll();
        if (historyList.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No content!");
        }

        return historyList.stream()
                .map(historyMapper::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ResponseStatusHistoryDTO save(CreateStatusHistoryDTO createStatusHistoryDTO) {
        if (createStatusHistoryDTO == null) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "StatusHistory is null!");
        }
        StatusHistory statusHistory = historyMapper.convertDtoToEntity(createStatusHistoryDTO);

        return historyMapper.convertEntityToDto(historyDAO.save(statusHistory));
    }

    @Override
    public ResponseStatusHistoryDTO update(Long id, UpdateStatusHistoryDTO updateStatusHistoryDTO) {
        if (updateStatusHistoryDTO == null) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No content!");
        }

        StatusHistory statusHistory = historyMapper.convertDtoToEntity(updateStatusHistoryDTO);

        Status status = statusDAO.findById(updateStatusHistoryDTO.getStatusId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Status not found!"));

        statusHistory.setStatus(status);

        EmployeeEntity employee = employeeDAO.findById(statusHistory.getEmployee().getEmpId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found!"));

        statusHistory.setEmployee(employee);
        return historyMapper.convertEntityToDto(statusHistory);
    }

    @Override
    public void deleteById(Long id) {
        historyDAO.deleteById(id);
    }
}
