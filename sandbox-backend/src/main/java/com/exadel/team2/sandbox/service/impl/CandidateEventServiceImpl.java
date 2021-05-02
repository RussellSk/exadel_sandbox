package com.exadel.team2.sandbox.service.impl;


import com.exadel.team2.sandbox.dao.CandidateEventDAO;
import com.exadel.team2.sandbox.dao.rsql.CustomRsqlVisitor;
import com.exadel.team2.sandbox.entity.CandidateEventEntity;
import com.exadel.team2.sandbox.mapper.CandidateEventMapper;
import com.exadel.team2.sandbox.service.CandidateEventService;
import com.exadel.team2.sandbox.web.candidate_event.ResponseCandidateEventDto;
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

@Transactional
@Service
@RequiredArgsConstructor
public class CandidateEventServiceImpl implements CandidateEventService {
    private final CandidateEventDAO candidateEventDAO;
    private final CandidateEventMapper candidateEventMapper;

    @Override
    public ResponseCandidateEventDto getById(Long id) {
        CandidateEventEntity candidateEventEntity = candidateEventDAO.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CandidateEvent Not Found"));
        return candidateEventMapper.convertEntityToDto(candidateEventEntity);
    }

    @Override
    public void delete(Long id) {
        candidateEventDAO.deleteById(id);
    }

    @Override
    public Page<ResponseCandidateEventDto> getAllPageable(Pageable pageable, String search) {
        if (search.isEmpty()) {
            return candidateEventDAO.findAll(pageable).map(candidateEventMapper::convertEntityToDto);
        } else {
            Node rootNode = new RSQLParser().parse(search);
            Specification<CandidateEventEntity> spec = rootNode.accept(new CustomRsqlVisitor<>());
            return candidateEventDAO.findAll(spec, pageable)
                    .map(candidateEventMapper::convertEntityToDto);
        }
    }
}
