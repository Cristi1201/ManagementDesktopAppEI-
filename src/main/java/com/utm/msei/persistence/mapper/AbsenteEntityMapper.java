package com.utm.msei.persistence.mapper;

import com.utm.msei.persistence.dto.AbsenteEntityDto;
import com.utm.msei.persistence.entity.AbsenteEntity;
import org.mapstruct.Mapper;

@Mapper
public interface AbsenteEntityMapper extends EntityMapper<AbsenteEntityDto, AbsenteEntity> {

    AbsenteEntityDto toDto(AbsenteEntity absenteEntity);

    AbsenteEntity toEntity(AbsenteEntityDto absenteEntityDto);
}
