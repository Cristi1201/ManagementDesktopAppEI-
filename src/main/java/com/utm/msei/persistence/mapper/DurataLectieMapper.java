package com.utm.msei.persistence.mapper;

import com.utm.msei.persistence.dto.DurataLectieDto;
import com.utm.msei.persistence.entity.DurataLectieEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@org.mapstruct.Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface DurataLectieMapper extends Mapper<DurataLectieDto, DurataLectieEntity> {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    DurataLectieEntity partialUpdate(DurataLectieDto durataLectieDto, @MappingTarget DurataLectieEntity durataLectieEntity);
}
