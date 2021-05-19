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
import com.exadel.team2.sandbox.web.event.EventCreateDTO;
import com.exadel.team2.sandbox.web.event.EventResponseDTO;
import com.exadel.team2.sandbox.web.event.EventUpdateDTO;
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
                .orElseThrow(() -> new NoSuchException("Event with ID = " + id + " not found in Database"));
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
        if (search.isEmpty()) {
            return eventDAO.findAll(pageable).map(eventMapper::convertEntityToDto);
        } else {
            Node rootNode = new RSQLParser().parse(search);
            Specification<EventEntity> specification = rootNode.accept(new CustomRsqlVisitor<>());
            return eventDAO.findAll(specification, pageable)
                    .map(eventMapper::convertEntityToDto);
        }
    }


    @Override
    public EventResponseDTO save(EventCreateDTO eventCreateDTO) {
        EventEntity eventEntity = eventMapper.convertDtoToEntity(eventCreateDTO);
        EmployeeEntity employeeEntity = employeeDAO.findById(eventCreateDTO.getEmployee())
                .orElseThrow(() -> new NoSuchException("Employee not found"));

        eventEntity.setEmployee(employeeEntity);

        EmployeeEntity creatorEvent = employeeDAO.findById(eventCreateDTO.getCreatorEvent())
                .orElseThrow(() -> new NoSuchException("Creator not found"));

        eventEntity.setCreatorEvent(creatorEvent);


        ImageEntity imageEntity = imageDAO.findById(eventCreateDTO.getImageId())
                .orElseThrow(() -> new NoSuchException("Image not found"));

        eventEntity.setImageEntity(imageEntity);

        EventTypeEntity eventTypeEntity = eventTypeDAO.findById(eventCreateDTO.getEventType())
                .orElseThrow(() -> new NoSuchException("Event Type not found"));

        eventEntity.setEventType(eventTypeEntity);
        eventEntity.setId(null);
        eventEntity.setCreatedAt(LocalDateTime.now());
        eventDAO.save(eventEntity);

        return eventMapper.convertEntityToDto(eventEntity);
    }

    @Override
    public EventResponseDTO update(Long id, EventUpdateDTO eventUpdateDTO) {
        EventEntity eventEntity = eventDAO.findById(id)
                .orElseThrow(() -> new NoSuchException("Event with ID = " + id + " not found in Database"));

        if (eventUpdateDTO.getImageId() != null) {
            eventEntity.setImageId(eventUpdateDTO.getImageId());
        }

        if (eventUpdateDTO.getEmployee() != null) {
            EmployeeEntity employeeEntity = employeeDAO.findById(eventUpdateDTO.getEmployee())
                    .orElseThrow(() -> new NoSuchException("Employee with ID = " + id + " not found in Database"));
            eventEntity.setEmployee(employeeEntity);
        }

        if (eventUpdateDTO.getEventType() != null) {
            EventTypeEntity eventTypeEntity = eventTypeDAO.findById(eventUpdateDTO.getEventType())
                    .orElseThrow(() -> new NoSuchException("Event type with ID = " + id + " not found in Database"));
            eventEntity.setEventType(eventTypeEntity);
        }

        if(eventUpdateDTO.getCreatorEvent() != null) {
            EmployeeEntity creatorEvent = employeeDAO.findById(eventUpdateDTO.getCreatorEvent())
                    .orElseThrow(() -> new NoSuchException("Creator event with ID = " + id + " not found in Database"));
            eventEntity.setCreatorEvent(creatorEvent);
        }

        if (eventUpdateDTO.getStartDate() != null) {
            eventEntity.setStartDate(eventUpdateDTO.getStartDate());
        }

        if (eventUpdateDTO.getDuration() != null) {
            eventUpdateDTO.setDuration(eventUpdateDTO.getDuration());
        }

        if (eventUpdateDTO.getDeadline() != null) {
            eventEntity.setDeadline(eventUpdateDTO.getDeadline());
        }

        if (eventUpdateDTO.getDateOfEndAccept() != null) {
            eventEntity.setDateOfEndAccept(eventUpdateDTO.getDateOfEndAccept());
        }

        if (eventUpdateDTO.getFormat() != null) {
            eventEntity.setFormat(eventUpdateDTO.getFormat());
        }

        if (eventUpdateDTO.getCountry() != null) {
            eventEntity.setCountry(eventUpdateDTO.getCountry());
        }

        if (eventUpdateDTO.getCity() != null) {
            eventEntity.setCity(eventUpdateDTO.getCity());
        }

        if (eventUpdateDTO.getTechnologies() != null) {
            eventEntity.setTechnologies(eventUpdateDTO.getTechnologies());
        }

        if (eventUpdateDTO.getTitle() != null) {
            eventEntity.setTitle(eventUpdateDTO.getTitle());
        }

        if (eventUpdateDTO.getDescription() != null) {
            eventEntity.setDescription(eventUpdateDTO.getDescription());
        }

        if (eventUpdateDTO.getEventTab() != null) {
            eventEntity.setEventTab(eventUpdateDTO.getEventTab());
        }

        if (eventUpdateDTO.getEnglishLevel() != null) {
            eventEntity.setEnglishLevel(eventUpdateDTO.getEnglishLevel());
        }

        eventEntity.setUpdatedAt(LocalDateTime.now());
        return eventMapper.convertEntityToDto(eventDAO.save(eventEntity));
    }


    @Override
    public Boolean delete(Long id) {
        if (!eventDAO.existsById(id)) {
            throw new NoSuchException("Event with ID = " + id + " not found in Database. " +
                    "Unable to delete an event that does not exist.");
        }
        eventDAO.deleteById(id);
        return true;
    }
}