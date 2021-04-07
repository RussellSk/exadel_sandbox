package com.exadel.team2.sandbox.mapper;

import com.exadel.team2.sandbox.entity.CandidateEventEntity;
import com.exadel.team2.sandbox.web.candidate_event.ResponseCandidateEventDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CandidateEventMapper implements Mapper<CandidateEventEntity, ResponseCandidateEventDto> {
    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public ResponseCandidateEventDto convertEntityToDto(CandidateEventEntity entity) {
        return modelMapper.map(entity, ResponseCandidateEventDto.class);
    }

    @Override
    public CandidateEventEntity convertDtoToEntity(Object dto) {
        return modelMapper.map(dto, CandidateEventEntity.class);
    }

}
