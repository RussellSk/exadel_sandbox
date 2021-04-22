package com.exadel.team2.sandbox.service.impl;

import com.exadel.team2.sandbox.dao.ResumeDAO;
import com.exadel.team2.sandbox.dao.rsql.CustomRsqlVisitor;
import com.exadel.team2.sandbox.dto.ResumeCreateDTO;
import com.exadel.team2.sandbox.dto.ResumeResponseDTO;
import com.exadel.team2.sandbox.dto.ResumeUpdateDTO;
import com.exadel.team2.sandbox.entity.ResumeEntity;
import com.exadel.team2.sandbox.mapper.ModelMap;
import com.exadel.team2.sandbox.service.ResumeService;
import cz.jirutka.rsql.parser.RSQLParser;
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
public class ResumeServiceImpl implements ResumeService {

    private final ResumeDAO resumeDAO;
    private final ModelMap modelMap;

    @Override
    public ResumeResponseDTO getById(Long id) {
        ResumeEntity resumeEntity = resumeDAO.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "The resume not found"));

        return modelMap.convertTo(resumeEntity, ResumeResponseDTO.class);
    }

    @Override
    public List<ResumeResponseDTO> getAllPageable(Pageable pageable, String search) {

        if (search.isEmpty()) {
            return resumeDAO.findAll(pageable).stream().map((ResumeEntity entity) ->
                    (ResumeResponseDTO) modelMap.convertTo(entity, ResumeResponseDTO.class))
                    .collect(Collectors.toList());
        }

        Node rootNode = new RSQLParser().parse(search);
        Specification<ResumeEntity> specification = rootNode.accept(new CustomRsqlVisitor<>());

        return resumeDAO.findAll(specification, pageable).stream().map((ResumeEntity entity) ->
                (ResumeResponseDTO) modelMap.convertTo(entity, ResumeResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ResumeCreateDTO save(ResumeCreateDTO resumeCreateDTO) {
        return modelMap.convertTo(
                resumeDAO.save(modelMap.convertTo(resumeCreateDTO, ResumeEntity.class)),
                        ResumeCreateDTO.class);
    }

    @Override
    public ResumeUpdateDTO update(Long id, ResumeUpdateDTO resumeUpdateDTO) {
        ResumeEntity resumeEntity = resumeDAO.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Resume not found"));

        if (resumeUpdateDTO.getPath() != null) {
            resumeEntity.setPath(resumeUpdateDTO.getPath());
        }

        if (resumeUpdateDTO.getLink() != null) {
            resumeEntity.setPath(resumeUpdateDTO.getLink());
        }

        if (resumeUpdateDTO.getName() != null) {
            resumeEntity.setName(resumeUpdateDTO.getName());
        }

        if (resumeUpdateDTO.getExt() != null) {
            resumeEntity.setExt(resumeUpdateDTO.getExt());
        }

        if (resumeUpdateDTO.getSize() != null) {
            resumeEntity.setSize(resumeUpdateDTO.getSize());
        }

        return modelMap.convertTo(
                resumeDAO.save(modelMap.convertTo(resumeUpdateDTO, ResumeEntity.class)),
                ResumeUpdateDTO.class);
    }

    @Override
    public void delete(Long id) {
        resumeDAO.deleteById(id);
    }
}
