package com.exadel.team2.sandbox.mapper;

import com.exadel.team2.sandbox.entity.PermissionEntity;
import com.exadel.team2.sandbox.web.permission.ResponsePermissionDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PermissionMapper implements Mapper<PermissionEntity, ResponsePermissionDto> {

    private final ModelMapper modelMapper = new ModelMapper();

    public ResponsePermissionDto convertEntityToDto(PermissionEntity entity) {
        return modelMapper.map(entity, ResponsePermissionDto.class);
    }

    public PermissionEntity convertDtoToEntity(Object dto) {
        return modelMapper.map(dto, PermissionEntity.class);
    }

}
