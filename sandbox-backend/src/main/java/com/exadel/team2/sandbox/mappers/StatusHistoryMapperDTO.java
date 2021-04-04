package com.exadel.team2.sandbox.mappers;

import com.exadel.team2.sandbox.entity.StatusHistory;
import com.exadel.team2.sandbox.web.ResponseStatusHistoryDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class StatusHistoryMapperDTO {
    private final ModelMapper mapper = new ModelMapper();

    public StatusHistory convertToEntity(Object dto) {
        return mapper.map(dto, StatusHistory.class);
    }

    public ResponseStatusHistoryDTO convertToDTO(StatusHistory statusHistory) {
        return mapper.map(statusHistory, ResponseStatusHistoryDTO.class);
    }

}
