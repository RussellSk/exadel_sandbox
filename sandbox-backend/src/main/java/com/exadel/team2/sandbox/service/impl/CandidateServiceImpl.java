package com.exadel.team2.sandbox.service.impl;

import com.exadel.team2.sandbox.configuration.constans.EmailConstant;
import com.exadel.team2.sandbox.dao.CandidateDAO;
import com.exadel.team2.sandbox.dao.rsql.CustomRsqlVisitor;
import com.exadel.team2.sandbox.dto.CandidateCreateDTO;
import com.exadel.team2.sandbox.dto.CandidateResponseDTO;
import com.exadel.team2.sandbox.dto.CandidateUpdateDTO;
import com.exadel.team2.sandbox.entity.CandidateEntity;
import com.exadel.team2.sandbox.entity.enums.CandidateStatus;
import com.exadel.team2.sandbox.exceptions.FlagDisabledException;
import com.exadel.team2.sandbox.mapper.ModelMap;
import com.exadel.team2.sandbox.service.CandidateService;
import com.exadel.team2.sandbox.service.SendEmailService;
import com.exadel.team2.sandbox.web.MessageDTO;
import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class CandidateServiceImpl implements CandidateService {

    private final SendEmailService sendEmailService;
    private final CandidateDAO candidateDAO;
    private final ModelMap modelMap;

    @Override
    public CandidateResponseDTO findById(Long id) {
        CandidateEntity candidateEntity = candidateDAO.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Candidate not found"));

        return modelMap.convertTo(candidateEntity, CandidateResponseDTO.class);
    }

    @Override
    public Page<CandidateResponseDTO> getAllPageable(Pageable pageable, String search) {

        if (search.isEmpty()) {
            return new PageImpl<>(candidateDAO.findAll(pageable).stream()
                    .map((CandidateEntity candidateEntity) ->
                            (CandidateResponseDTO) modelMap.convertTo(
                                    candidateEntity, CandidateResponseDTO.class))
                    .collect(Collectors.toList()));
        }


        Node rootNode = new RSQLParser().parse(search);
        Specification<CandidateEntity> specification = rootNode.accept(new CustomRsqlVisitor<>());

        return new PageImpl<>(candidateDAO.findAll(specification, pageable).stream()
                .map((CandidateEntity candidateEntity) ->
                        (CandidateResponseDTO) modelMap.convertTo(
                                candidateEntity, CandidateResponseDTO.class))
                .collect(Collectors.toList()));
    }

    @Override
    public CandidateCreateDTO save(CandidateCreateDTO candidateCreateDTO) {

        Map<String, Object> prop = new HashMap<>();
        prop.put("name", candidateCreateDTO.getFirstName());

        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setTo(candidateCreateDTO.getEmail());
        messageDTO.setSubject(EmailConstant.SUBJECT_RECEIVED_RESUME);
        messageDTO.setProperties(prop);
        messageDTO.setTemplateName("receivingCandidateResume.ftl");
        try {
            sendEmailService.sendEmail(messageDTO);
        } catch (FlagDisabledException q) {
            log.warn(q.getMessage(), q);
        } catch (TemplateException | IOException e) {
            log.error("Error during email sending {}", e.getMessage(), e);
        }
        return modelMap.convertTo(candidateDAO.save(
                modelMap.convertTo(candidateCreateDTO, CandidateEntity.class)),
                CandidateCreateDTO.class);
    }

    @Override
    public CandidateUpdateDTO update(Long id, CandidateUpdateDTO candidateUpdateDTO) {
        CandidateEntity candidateEntity = candidateDAO.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Candidate not found"));

        if (candidateUpdateDTO.getRsmId() != null) {
            candidateEntity.setRsmId(candidateUpdateDTO.getRsmId());
        }

        if (candidateUpdateDTO.getFirstName() != null) {
            candidateEntity.setFirstName(candidateUpdateDTO.getFirstName());
        }

        if (candidateUpdateDTO.getLastName() != null) {
            candidateEntity.setLastName(candidateUpdateDTO.getLastName());
        }

        if (candidateUpdateDTO.getPhone() != null) {
            candidateEntity.setPhone(candidateUpdateDTO.getPhone());
        }

        if (candidateUpdateDTO.getSkype() != null) {
            candidateEntity.setSkype(candidateUpdateDTO.getSkype());
        }

        if (candidateUpdateDTO.getEnglishLevel() != null) {
            candidateEntity.setEnglishLevel(candidateUpdateDTO.getEnglishLevel());
        }

        if (candidateUpdateDTO.getMainSkill() != null) {
            candidateEntity.setMainSkill(candidateUpdateDTO.getMainSkill());
        }

        if (candidateUpdateDTO.getOtherSkills() != null) {
            candidateEntity.setOtherSkills(candidateUpdateDTO.getOtherSkills());
        }

        if (candidateUpdateDTO.getEmail() != null) {
            candidateEntity.setEmail(candidateUpdateDTO.getEmail());
        }

        if (candidateUpdateDTO.getInstitution() != null) {
            candidateEntity.setInstitution(candidateUpdateDTO.getInstitution());
        }

        if (candidateUpdateDTO.getFaculty() != null) {
            candidateEntity.setFaculty(candidateUpdateDTO.getFaculty());
        }

        if (candidateUpdateDTO.getSpeciality() != null) {
            candidateEntity.setSpeciality(candidateUpdateDTO.getSpeciality());
        }

        if (candidateUpdateDTO.getGraduationDate() != null) {
            candidateEntity.setGraduationDate(candidateUpdateDTO.getGraduationDate());
        }

        if (candidateUpdateDTO.getCountry() != null) {
            candidateEntity.setCountry(candidateUpdateDTO.getCountry());
        }

        if (candidateUpdateDTO.getCity() != null) {
            candidateEntity.setCity(candidateUpdateDTO.getCity());
        }
        if (candidateUpdateDTO.getStatus() != null) {
            candidateEntity.setStatus(candidateUpdateDTO.getStatus());
            MessageDTO messageDTO = getMessageDTO(candidateUpdateDTO);
            try {
                sendEmailService.sendEmail(messageDTO);
            } catch (FlagDisabledException q) {
                log.warn(q.getMessage(), q);
            } catch (IOException | TemplateException e) {
                log.error("Error during email sending {}", e.getMessage(), e);
            }
        }

        candidateEntity.setUpdatedAt(candidateUpdateDTO.getUpdatedAt());

        return modelMap.convertTo(candidateDAO.save(candidateEntity), CandidateUpdateDTO.class);
    }

    @Override
    public void delete(Long id) {
        candidateDAO.deleteById(id);
    }

    private MessageDTO getMessageDTO(CandidateUpdateDTO candidateUpdateDTO) {
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setTo(candidateUpdateDTO.getEmail());
        messageDTO.setSubject(EmailConstant.SUBJECT_STATUS_CANDIDATE);
        if (candidateUpdateDTO.getStatus() == CandidateStatus.SUITABLE) {
            messageDTO.setProperties(new HashMap<>());
            messageDTO.setTemplateName("candidateHiring.ftl");
        }
        if (candidateUpdateDTO.getStatus() == CandidateStatus.NOT_SUITABLE) {
            Map<String, Object> prop = new HashMap<>();
            prop.put("name", candidateUpdateDTO.getFirstName());
            messageDTO.setProperties(prop);
            messageDTO.setTemplateName("candidateRejection.ftl");
        }
        return messageDTO;
    }
}
