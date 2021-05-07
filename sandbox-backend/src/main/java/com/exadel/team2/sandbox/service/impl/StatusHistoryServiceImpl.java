package com.exadel.team2.sandbox.service.impl;

import com.exadel.team2.sandbox.dao.CandidateDAO;
import com.exadel.team2.sandbox.dao.EmployeeDAO;
import com.exadel.team2.sandbox.dao.StatusDAO;
import com.exadel.team2.sandbox.dao.StatusHistoryDAO;
import com.exadel.team2.sandbox.dao.rsql.CustomRsqlVisitor;
import com.exadel.team2.sandbox.entity.CandidateEntity;
import com.exadel.team2.sandbox.entity.EmployeeEntity;
import com.exadel.team2.sandbox.entity.Status;
import com.exadel.team2.sandbox.entity.StatusHistory;
import com.exadel.team2.sandbox.mapper.StatusHistoryMapperDTO;
import com.exadel.team2.sandbox.service.StatusHistoryService;
import com.exadel.team2.sandbox.web.statushistory.CreateStatusHistoryDTO;
import com.exadel.team2.sandbox.web.statushistory.ResponseStatusHistoryDTO;
import com.exadel.team2.sandbox.web.statushistory.UpdateStatusHistoryDTO;
import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class StatusHistoryServiceImpl implements StatusHistoryService {

    private final StatusHistoryDAO historyDAO;
    private final StatusDAO statusDAO;
    private final EmployeeDAO employeeDAO;
    private final CandidateDAO candidateDAO;
    private final StatusHistoryMapperDTO historyMapper;

    @Override
    public ResponseStatusHistoryDTO findById(Long id) {
        StatusHistory statusHistory = historyDAO.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "StatusHistory not found!"));

        return historyMapper.convertEntityToDto(statusHistory);
    }

    @Override
    public Page<ResponseStatusHistoryDTO> findAllPageable(Pageable pageable, String search) {
        if (search.isEmpty()) {
            return historyDAO.findAll(pageable)
                    .map(historyMapper::convertEntityToDto);
        }
        Node rootNode = new RSQLParser().parse(search);
        Specification<StatusHistory> spec = rootNode.accept(new CustomRsqlVisitor<>());
        return historyDAO.findAll(spec, pageable).map(historyMapper::convertEntityToDto);

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

        Status status = statusDAO.findById(createStatusHistoryDTO.getStatusId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Status not found!"));

        statusHistory.setStatus(status);

        CandidateEntity candidate = candidateDAO.findById(createStatusHistoryDTO.getCandidateId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Candidate not found!"));

        statusHistory.setCandidate(candidate);

        EmployeeEntity employee = employeeDAO.findById(createStatusHistoryDTO.getEmployeeId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found!"));

        statusHistory.setEmployee(employee);
        return historyMapper.convertEntityToDto(historyDAO.save(statusHistory));
    }

    @Override
    public ResponseStatusHistoryDTO update(Long id, UpdateStatusHistoryDTO updateStatusHistoryDTO) {
        if (updateStatusHistoryDTO == null) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No content!");
        }
        if (!historyDAO.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id " + id + " not found!");
        }

        StatusHistory statusHistory = historyMapper.convertDtoToEntity(updateStatusHistoryDTO);

        Status status = statusDAO.findById(updateStatusHistoryDTO.getStatusId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Status not found!"));

        statusHistory.setStatus(status);

        CandidateEntity candidate = candidateDAO.findById(updateStatusHistoryDTO.getCandidateId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Candidate not found!"));

        statusHistory.setCandidate(candidate);
        EmployeeEntity employee = employeeDAO.findById(updateStatusHistoryDTO.getEmployeeId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found!"));

        statusHistory.setEmployee(employee);
        statusHistory.setUpdatedAt(LocalDateTime.now());
        statusHistory.setId(id);
        return historyMapper.convertEntityToDto(historyDAO.save(statusHistory));
    }

    @Override
    public void deleteById(Long id) {
        if (!historyDAO.existsById(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "StatusHistory id not found");
        historyDAO.deleteById(id);
    }
}
