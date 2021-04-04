package com.exadel.team2.sandbox.mappers;

import com.exadel.team2.sandbox.entity.StatusHistory;
import com.exadel.team2.sandbox.web.ResponseStatusHistoryDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class StatusHistoryMapperDTO implements Mapper<StatusHistory, ResponseStatusHistoryDTO> {
    private final ModelMapper mapper = new ModelMapper();


    @Override
    public StatusHistory convertToEntity(Object dto) {
        return mapper.map(dto, StatusHistory.class);
    }

    @Override
    public ResponseStatusHistoryDTO convertToDto(StatusHistory entity) {
        return mapper.map(entity,ResponseStatusHistoryDTO.class);
    }
}
