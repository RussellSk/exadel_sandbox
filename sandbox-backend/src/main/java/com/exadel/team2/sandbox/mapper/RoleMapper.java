package com.exadel.team2.sandbox.mapper;

import com.exadel.team2.sandbox.entity.RoleEntity;
import com.exadel.team2.sandbox.web.role.ResponseRoleDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper implements Mapper<RoleEntity, ResponseRoleDto> {

    private final ModelMapper modelMapper = new ModelMapper();

    public ResponseRoleDto convertEntityToDto(RoleEntity entity) {
        return modelMapper.map(entity, ResponseRoleDto.class);
    }

    public RoleEntity convertDtoToEntity(Object dto) {
        return modelMapper.map(dto, RoleEntity.class);
    }
}
