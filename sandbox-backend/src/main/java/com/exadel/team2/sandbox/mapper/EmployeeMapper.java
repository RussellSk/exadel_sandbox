package com.exadel.team2.sandbox.mapper;

import com.exadel.team2.sandbox.entity.EmployeeEntity;
import com.exadel.team2.sandbox.web.GeneralDto;
import com.exadel.team2.sandbox.web.employee.ResponseEmployeeDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper implements Mapper<EmployeeEntity, ResponseEmployeeDto> {

    private final ModelMapper modelMapper = new ModelMapper();

    public ResponseEmployeeDto convertEntityToDto(EmployeeEntity entity) {
        return modelMapper.map(entity, ResponseEmployeeDto.class);
    }

    public EmployeeEntity convertDtoToEntity(Object dto) {
        return modelMapper.map(dto, EmployeeEntity.class);
    }
}