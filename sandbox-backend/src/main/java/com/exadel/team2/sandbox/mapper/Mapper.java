package com.exadel.team2.sandbox.mapper;

public interface Mapper<E, D> {
    D convertEntityToDto(E entity);
    E convertDtoToEntity(Object dto);

   }
