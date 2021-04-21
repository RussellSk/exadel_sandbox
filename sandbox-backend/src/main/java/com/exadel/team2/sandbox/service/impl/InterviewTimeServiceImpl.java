package com.exadel.team2.sandbox.service.impl;

import com.exadel.team2.sandbox.dao.InterviewTimeDAO;
import com.exadel.team2.sandbox.dao.rsql.CustomRsqlVisitor;
import com.exadel.team2.sandbox.dto.InterviewTimeCreateDTO;
import com.exadel.team2.sandbox.dto.InterviewTimeResponseDTO;
import com.exadel.team2.sandbox.dto.InterviewTimeUpdateDTO;
import com.exadel.team2.sandbox.entity.InterviewTimeEntity;
import com.exadel.team2.sandbox.mapper.ModelMap;
import com.exadel.team2.sandbox.service.InterviewTimeService;
import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.RSQLParserException;
import cz.jirutka.rsql.parser.ast.Node;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class InterviewTimeServiceImpl implements InterviewTimeService {

    private final InterviewTimeDAO interviewTimeDAO;
    private final ModelMap modelMap;

    @Override
    public InterviewTimeResponseDTO findById(Long id) {
        InterviewTimeEntity interviewTimeEntity = interviewTimeDAO.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "The arranged meeting not found"));

        return modelMap.convertTo(interviewTimeEntity, InterviewTimeResponseDTO.class);
    }

    @Override
    public List<InterviewTimeResponseDTO> getAllPageable(Pageable pageable, String search) {

        if (search.isEmpty()) {
            return interviewTimeDAO.findAll(pageable).stream()
                    .map((InterviewTimeEntity interviewTimeEntity) -> (InterviewTimeResponseDTO) modelMap
                            .convertTo(interviewTimeEntity, InterviewTimeResponseDTO.class))
                    .collect(Collectors.toList());
        }

        Node rootNode = new RSQLParser().parse(search);
        Specification<InterviewTimeEntity> specification = rootNode.accept(new CustomRsqlVisitor<>());

        return interviewTimeDAO.findAll(specification, pageable).stream()
                .map((InterviewTimeEntity interviewTimeEntity) -> (InterviewTimeResponseDTO) modelMap
                        .convertTo(interviewTimeEntity, InterviewTimeResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public InterviewTimeCreateDTO save(InterviewTimeCreateDTO interviewTimeCreateDTO) {
        return modelMap.convertTo(interviewTimeDAO
                .save(modelMap.convertTo(interviewTimeCreateDTO, InterviewTimeEntity.class)),
                InterviewTimeCreateDTO.class);
    }

    @Override
    public InterviewTimeUpdateDTO update(Long id, InterviewTimeUpdateDTO interviewTimeUpdateDTO) {
        InterviewTimeEntity interviewTimeEntity = interviewTimeDAO.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "The meeting didn't arrange"));

        if (interviewTimeUpdateDTO.getEvId() != null) {
            interviewTimeEntity.setEvId(interviewTimeUpdateDTO.getEvId());
        }

        if (interviewTimeUpdateDTO.getCnId() != null) {
            interviewTimeEntity.setCnId(interviewTimeUpdateDTO.getCnId());
        }

        if (interviewTimeUpdateDTO.getEmpId() != null) {
            interviewTimeEntity.setEmpId(interviewTimeUpdateDTO.getEmpId());
        }

        if (interviewTimeUpdateDTO.getBeginDate() != null) {
            interviewTimeEntity.setBeginDate(interviewTimeUpdateDTO.getBeginDate());
        }

        interviewTimeEntity.setUpdatedAt(interviewTimeUpdateDTO.getUpdatedAt());

        return modelMap.convertTo(interviewTimeDAO
                        .save(interviewTimeEntity), InterviewTimeUpdateDTO.class);
    }

    @Override
    public void delete(Long id) {
        interviewTimeDAO.deleteById(id);
    }
}
