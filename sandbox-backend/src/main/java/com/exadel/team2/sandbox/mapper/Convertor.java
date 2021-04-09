package com.exadel.team2.sandbox.mapper;

public interface Convertor {
    <T, V> T convertTo(V v, Class destination);
}
