package com.exadel.team2.sandbox.service.impl;

import com.exadel.team2.sandbox.dao.EmployeeDAO;
import com.exadel.team2.sandbox.dao.StatusDAO;
import com.exadel.team2.sandbox.dao.StatusHistoryDAO;
import com.exadel.team2.sandbox.entity.EmployeeEntity;
import com.exadel.team2.sandbox.entity.Status;
import com.exadel.team2.sandbox.entity.StatusHistory;
import com.exadel.team2.sandbox.mappers.StatusHistoryMapperDTO;
import com.exadel.team2.sandbox.service.StatusHistoryService;
import com.exadel.team2.sandbox.web.CreateStatusHistoryDTO;
import com.exadel.team2.sandbox.web.ResponseStatusHistoryDTO;
import com.exadel.team2.sandbox.web.UpdateStatusHistoryDTO;
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

        return historyMapper.convertToDto(statusHistory);
    }

    @Override
    public Page<ResponseStatusHistoryDTO> findAll(Pageable pageable) {
        return historyDAO.findAll(pageable).map(historyMapper::convertToDto);

    }

    @Override
    public List<ResponseStatusHistoryDTO> findAll() {
        List<StatusHistory> historyList = historyDAO.findAll();
        if (historyList.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No content!");
        }

        return historyList.stream()
                .map(historyMapper::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ResponseStatusHistoryDTO save(CreateStatusHistoryDTO createStatusHistoryDTO) {
        if (createStatusHistoryDTO == null) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "StatusHistory is null!");
        }
        StatusHistory statusHistory = historyMapper.convertToEntity(createStatusHistoryDTO);

        return historyMapper.convertToDto(historyDAO.save(statusHistory));
    }

    @Override
    public ResponseStatusHistoryDTO update(Long id, UpdateStatusHistoryDTO updateStatusHistoryDTO) {
        if (updateStatusHistoryDTO == null) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No content!");
        }
        StatusHistory statusHistory = historyDAO.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "StatusHistory is nul!"));

        Status status = statusDAO.findById(updateStatusHistoryDTO.getStatusId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Status not found!"));
        EmployeeEntity employee = employeeDAO.findById(statusHistory.getEmployee().getEmpId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found!"));

        statusHistory.setStatus(status);
        statusHistory.setEmployee(employee);
        statusHistory.setUpdatedAt(updateStatusHistoryDTO.getUpdatedAt());
        statusHistory.setChangeNote(updateStatusHistoryDTO.getChangeNote());
        return historyMapper.convertToDto(statusHistory);
    }

    @Override
    public void deleteById(Long id) {
        historyDAO.deleteById(id);
    }
}
