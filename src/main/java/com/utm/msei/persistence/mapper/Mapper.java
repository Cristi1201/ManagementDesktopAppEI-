package com.utm.msei.persistence.mapper;

import java.util.List;

public interface Mapper<D, E> {

    E toEntity(D dto);

    D toDto(E entity);

    List<E> toEntity(List<D> dtoList);

    List<D> toDto(List<E> entityList);
}
