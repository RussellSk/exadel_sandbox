package com.exadel.team2.sandbox.service.impl;

import com.exadel.team2.sandbox.dao.GeneralDAO;
import com.exadel.team2.sandbox.dao.rsql.CustomRsqlVisitor;
import com.exadel.team2.sandbox.entity.BaseEntity;
import com.exadel.team2.sandbox.mapper.Mapper;
import com.exadel.team2.sandbox.service.GeneralService;
import com.exadel.team2.sandbox.web.GeneralDto;
import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

public abstract class GeneralServiceImpl <E extends BaseEntity, R extends GeneralDto, C extends GeneralDto, U extends GeneralDto>
        implements GeneralService<R, C, U> {

    Mapper<E, R> generalMapper;
    GeneralDAO<E, Long> generalDAO;

    public R getById(Long id) {
        E generalEntity = generalDAO.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Item Not Found"));
        return generalMapper.convertEntityToDto(generalEntity);
    }

    public List<R> getAll() {
        List<E> generalEntities = generalDAO.findAll();
        if (generalEntities.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No Content Added Yet");
        }

        return generalEntities.stream()
                .map(generalMapper::convertEntityToDto)
                .collect(Collectors.toList());
    }

    public Page<R> getAllPageable(Pageable pageable, String search) {
        if (search.isEmpty()) {
            return generalDAO.findAll(pageable)
                    .map(generalMapper::convertEntityToDto);
        }

        Node rootNode = new RSQLParser().parse(search);
        Specification<E> specification = rootNode.accept(new CustomRsqlVisitor<>());
        return generalDAO.findAll(specification, pageable)
                .map(generalMapper::convertEntityToDto);
    }

    public R save(C createDTO) {
        return generalMapper.convertEntityToDto(generalDAO.save(generalMapper.convertDtoToEntity(createDTO)));
    }

    public R update(Long id, U updateDto) {
        generalDAO.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Item Not Found"));
        E updatedEntity = generalMapper.convertDtoToEntity(updateDto);
        updatedEntity.setId(id);
        return generalMapper.convertEntityToDto(generalDAO.save(updatedEntity));
    }

    public void delete(Long id) {
        generalDAO.deleteById(id);
    }
}
