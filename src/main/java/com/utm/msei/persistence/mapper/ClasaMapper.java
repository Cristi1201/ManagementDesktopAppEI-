package com.utm.msei.persistence.mapper;

import com.utm.msei.persistence.dto.ClasaDto;
import com.utm.msei.persistence.entity.ClasaEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@org.mapstruct.Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ClasaMapper extends Mapper<ClasaDto, ClasaEntity> {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ClasaEntity partialUpdate(ClasaDto clasaDto, @MappingTarget ClasaEntity clasaEntity);
}
