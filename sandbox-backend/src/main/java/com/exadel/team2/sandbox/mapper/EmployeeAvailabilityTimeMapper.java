package com.exadel.team2.sandbox.mapper;

import com.exadel.team2.sandbox.entity.EmployeeAvailabilityEntity;
import com.exadel.team2.sandbox.web.employee_availability_time.ResponseEmployeeAvailabilityTimeDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class EmployeeAvailabilityTimeMapper implements Mapper<EmployeeAvailabilityEntity, ResponseEmployeeAvailabilityTimeDto> {

    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public ResponseEmployeeAvailabilityTimeDto convertEntityToDto(EmployeeAvailabilityEntity entity) {
        return modelMapper.map(entity, ResponseEmployeeAvailabilityTimeDto.class);
    }

    @Override
    public EmployeeAvailabilityEntity convertDtoToEntity(Object dto) {
        return modelMapper.map(dto, EmployeeAvailabilityEntity.class);
    }
}
