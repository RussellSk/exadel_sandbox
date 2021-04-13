package com.exadel.team2.sandbox.service.impl;


import com.exadel.team2.sandbox.dao.CandidateDAO;
import com.exadel.team2.sandbox.dao.CandidateEventDAO;
import com.exadel.team2.sandbox.dao.EventDAO;
import com.exadel.team2.sandbox.entity.CandidateEntity;
import com.exadel.team2.sandbox.entity.CandidateEventEntity;
import com.exadel.team2.sandbox.entity.EventEntity;
import com.exadel.team2.sandbox.mapper.CandidateEventMapper;
import com.exadel.team2.sandbox.service.CandidateEventService;
import com.exadel.team2.sandbox.web.candidate_event.CreateCandidateEventDto;
import com.exadel.team2.sandbox.web.candidate_event.ResponseCandidateEventDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
@RequiredArgsConstructor
public class CandidateEventServiceImpl implements CandidateEventService {
    private final CandidateEventDAO candidateEventDAO;
    private final CandidateEventMapper candidateEventMapper;
    private final EventDAO eventDAO;
    private final CandidateDAO candidateDAO;

    @Override
    public ResponseCandidateEventDto getById(Long id) {
        CandidateEventEntity candidateEventEntity = candidateEventDAO.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CandidateEvent Not Found"));
        return candidateEventMapper.convertEntityToDto(candidateEventEntity);
    }

    @Override
    public List<ResponseCandidateEventDto> getAll() {
        List<CandidateEventEntity> candidateEventEntities = candidateEventDAO.findAll();
        if (candidateEventEntities.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No Content");
        }
        return candidateEventEntities.stream()
                .map(candidateEventMapper::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ResponseCandidateEventDto save(CreateCandidateEventDto createCandidateEventDto) {
        CandidateEventEntity candidateEventEntity = candidateEventMapper.convertDtoToEntity(createCandidateEventDto);

        EventEntity eventEntity = eventDAO.findById(createCandidateEventDto.getIdEvent())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found"));
        candidateEventEntity.setEvent(eventEntity);

         CandidateEntity candidateEntity =   candidateDAO.findById(createCandidateEventDto.getIdCandidate())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Candidate not found"));
        candidateEventEntity.setCandidates((List<CandidateEntity>) candidateEntity);

        candidateEventEntity.setCreatedAt(LocalDateTime.now());
        candidateEventDAO.save(candidateEventEntity);
        return candidateEventMapper.convertEntityToDto(candidateEventEntity);
    }

    @Override
    public void delete(Long id) {
        candidateEventDAO.deleteById(id);
    }

    @Override
    public Page<ResponseCandidateEventDto> getAllPageable(Pageable pageable) {
        return candidateEventDAO.findAll(pageable)
                .map(candidateEventMapper::convertEntityToDto);
    }
}
