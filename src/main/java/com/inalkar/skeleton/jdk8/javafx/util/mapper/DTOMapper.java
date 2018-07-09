package com.inalkar.skeleton.jdk8.javafx.util.mapper;

import java.util.List;
import java.util.stream.Collectors;

public interface DTOMapper<E, D> {

    E convertToEntity(D dto) throws MapperException;
    D convertToDTO(E entity) throws MapperException;

    default List<E> convertToEntityList(List<D> dtoList) throws MapperException {
        return dtoList.stream()
                .map(this::convertToEntity)
                .collect(Collectors.toList());
    }

    default List<D> convertToDTOList(List<E> entityList) throws MapperException {
        return entityList.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

}
