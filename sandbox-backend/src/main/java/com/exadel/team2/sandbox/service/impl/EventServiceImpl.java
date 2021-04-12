package com.exadel.team2.sandbox.service.impl;

import com.exadel.team2.sandbox.dao.EmployeeDAO;
import com.exadel.team2.sandbox.dao.EventDAO;
import com.exadel.team2.sandbox.dao.EventTypeDAO;
import com.exadel.team2.sandbox.dao.ImageDAO;
import com.exadel.team2.sandbox.dao.rsql.CustomRsqlVisitor;
import com.exadel.team2.sandbox.entity.EmployeeEntity;
import com.exadel.team2.sandbox.entity.EventEntity;
import com.exadel.team2.sandbox.entity.EventTypeEntity;
import com.exadel.team2.sandbox.entity.ImageEntity;
import com.exadel.team2.sandbox.exceptions.NoSuchException;
import com.exadel.team2.sandbox.mapper.EventMapper;
import com.exadel.team2.sandbox.service.EventService;
import com.exadel.team2.sandbox.web.*;
import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventDAO eventDAO;
    private final EmployeeDAO employeeDAO;
    private final ImageDAO imageDAO;
    private final EventTypeDAO eventTypeDAO;
    private final EventMapper eventMapper;

        @Override
    public EventResponseDTO getById(Long id) {
        EventEntity eventEntity = eventDAO.findById(id)
                .orElseThrow(() -> new NoSuchException("Event with ID = " + id + " not found in Database" ));
        return eventMapper.convertEntityToDto(eventEntity);
    }

    @Override
    public List<EventResponseDTO> getAll() {
        List<EventEntity> eventEntities = eventDAO.findAll();
        if (eventEntities.isEmpty()) {
            throw new NoSuchException("Not found events in Database");
        }
        return eventEntities.stream()
                .map(eventMapper::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<EventResponseDTO> getAllPageable(Pageable pageable, String search) {
        Node rootNode = new RSQLParser().parse(search);
        Specification<EventEntity> specification = rootNode.accept(new CustomRsqlVisitor<>());
        return eventDAO.findAll(specification, pageable)
                .map(eventMapper::convertEntityToDto);
    }


    @Override
    public EventResponseDTO save(EventCreateDTO eventCreateDTO) {
        EventEntity eventEntity = eventMapper.convertDtoToEntity(eventCreateDTO);
        EmployeeEntity employeeEntity = employeeDAO.findById(eventCreateDTO.getEmployeeId())

                .orElseThrow(() -> new NoSuchException ("Employee not found"));

        eventEntity.setEmployee(employeeEntity);

        ImageEntity imageEntity = imageDAO.findById(eventCreateDTO.getImageId())
                .orElseThrow(() -> new NoSuchException ("Image not found"));

        eventEntity.setImage(imageEntity);

        EventTypeEntity eventTypeEntity = eventTypeDAO.findById(eventCreateDTO.getEventTypeId())
                .orElseThrow(() -> new NoSuchException ("Event Type not found"));

        eventEntity.setEventType(eventTypeEntity);

        eventEntity.setEvCreatedAt(LocalDateTime.now());
        eventDAO.save(eventEntity);

        return eventMapper.convertEntityToDto(eventEntity);
    }

    @Override
    public EventResponseDTO update(Long id, EventUpdateDTO eventUpdateDTO) {
        EventEntity eventEntity = eventMapper.convertDtoToEntity(eventUpdateDTO);
        eventEntity.setEvId(id);
        EventEntity ifEventNotFound = eventDAO.findById(id)
        .orElseThrow(() -> new NoSuchException("Event with ID = " + id + " not found in Database" ));


        EmployeeEntity employeeEntity = employeeDAO.findById(eventUpdateDTO.getEmployeeId())
                .orElseThrow(() -> new NoSuchException ("Employee not found"));

        eventEntity.setEmployee(employeeEntity);

        ImageEntity imageEntity = imageDAO.findById(eventUpdateDTO.getImageId())
                .orElseThrow(() -> new NoSuchException ("Image not found"));

        eventEntity.setImage(imageEntity);

        EventTypeEntity eventTypeEntity = eventTypeDAO.findById(eventUpdateDTO.getEventTypeId())
                .orElseThrow(() -> new NoSuchException ("Event Type not found"));

        eventEntity.setEventType(eventTypeEntity);

        eventEntity.setEvUpdatedAt(LocalDateTime.now());
        return eventMapper.convertEntityToDto(eventDAO.save(eventEntity));
    }


    @Override
    public String delete(Long id) {
        EventEntity eventRemove = eventDAO.findById(id)
                .orElseThrow(() -> new NoSuchException
                        ("Event with ID = " + id + " not found in Database." +
                          " Unable to delete an event that does not exist."));
        eventDAO.deleteById(id);
        return "Event with ID = " + id + " was successful removed";
    }
}