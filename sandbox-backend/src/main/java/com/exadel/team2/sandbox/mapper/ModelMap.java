package com.exadel.team2.sandbox.mapper;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class ModelMap implements Convertor {
    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public <T, V> T convertTo(V v, Class destination) {
        if (v != null) {
            return (T) modelMapper.map(v, destination);
        }
        return null;
    }

}
