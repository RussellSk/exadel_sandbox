package com.exadel.team2.sandbox.service.impl;

import com.exadel.team2.sandbox.configuration.constans.EmailConstant;
import com.exadel.team2.sandbox.dao.InterviewTimeDAO;
import com.exadel.team2.sandbox.dao.rsql.CustomRsqlVisitor;
import com.exadel.team2.sandbox.dto.CandidateResponseDTO;
import com.exadel.team2.sandbox.dto.InterviewTimeCreateDTO;
import com.exadel.team2.sandbox.dto.InterviewTimeResponseDTO;
import com.exadel.team2.sandbox.dto.InterviewTimeUpdateDTO;
import com.exadel.team2.sandbox.entity.InterviewTimeEntity;
import com.exadel.team2.sandbox.mapper.ModelMap;
import com.exadel.team2.sandbox.service.CandidateService;
import com.exadel.team2.sandbox.service.InterviewTimeService;
import com.exadel.team2.sandbox.service.SendEmailService;
import com.exadel.team2.sandbox.web.MessageDTO;
import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class InterviewTimeServiceImpl implements InterviewTimeService {

    private final InterviewTimeDAO interviewTimeDAO;
    private final CandidateService candidateService;
    private final ModelMap modelMap;
    private final SendEmailService sendEmailService;

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
        CandidateResponseDTO candidateResponseDTO = candidateService.findById(interviewTimeCreateDTO.getCnId());
        Map<String, Object> prop = new HashMap<>();
        prop.put("name", candidateResponseDTO.getFirstName());
        prop.put("beginDate", interviewTimeCreateDTO.getBeginDate().format(DateTimeFormatter.ofPattern("dd-M-yyyy hh:mm:ss")));

        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setTo(candidateResponseDTO.getEmail());
        messageDTO.setSubject(EmailConstant.SUBJECT_INTERVIEW_TIME);
        messageDTO.setProperties(prop);
        messageDTO.setTemplateName("interviewTimeEmail.ftl");
        try {
            sendEmailService.sendEmail(messageDTO);
        } catch (IOException | TemplateException e) {
            log.error("Error during email sending {}", e.getMessage(), e);
        }
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
