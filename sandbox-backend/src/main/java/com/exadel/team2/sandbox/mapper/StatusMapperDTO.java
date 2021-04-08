package com.exadel.team2.sandbox.mapper;

import com.exadel.team2.sandbox.entity.Status;
import com.exadel.team2.sandbox.web.status.ResponseStatusDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class StatusMapperDTO implements Mapper<Status, ResponseStatusDTO> {

    private final ModelMapper mapper = new ModelMapper();

    @Override
    public Status convertDtoToEntity(Object dto) {
        return mapper.map(dto, Status.class);
    }

    @Override
    public ResponseStatusDTO convertEntityToDto(Status entity) {
        return mapper.map(entity, ResponseStatusDTO.class);
    }
}
