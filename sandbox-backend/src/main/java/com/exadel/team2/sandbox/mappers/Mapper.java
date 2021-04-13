package com.exadel.team2.sandbox.mappers;

public interface Mapper<E, D> {
    E convertToEntity(Object dto);

    D convertToDto(E entity);
}
