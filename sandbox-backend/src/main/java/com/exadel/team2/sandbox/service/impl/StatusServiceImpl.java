package com.exadel.team2.sandbox.service.impl;

import com.exadel.team2.sandbox.dao.StatusDAO;
import com.exadel.team2.sandbox.dao.rsql.CustomRsqlVisitor;
import com.exadel.team2.sandbox.entity.Status;
import com.exadel.team2.sandbox.mapper.StatusMapperDTO;
import com.exadel.team2.sandbox.service.StatusService;
import com.exadel.team2.sandbox.web.status.CreateStatusDTO;
import com.exadel.team2.sandbox.web.status.ResponseStatusDTO;
import com.exadel.team2.sandbox.web.status.UpdateStatusDTO;
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
    public Page<ResponseStatusDTO> findAllPageable(Pageable pageable, String query) {
        if (query.isEmpty()) {
            return dao.findAll(pageable)
                    .map(statusMapper::convertEntityToDto);
        }
        Node rootNode = new RSQLParser().parse(query);
        Specification<Status> spec = rootNode.accept(new CustomRsqlVisitor<>());
        return dao.findAll(spec, pageable).map(statusMapper::convertEntityToDto);
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
        if (!dao.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id " + id + " not found!");
        }
        Status status = statusMapper.convertDtoToEntity(updateStatusDTO);
        status.setId(id);
        status.setUpdatedAt(LocalDateTime.now());
        return statusMapper.convertEntityToDto(dao.save(status));
    }

    @Override
    public void deleteById(Long id) {
        if (!dao.existsById(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Status id not found");
        dao.deleteById(id);
    }
}
