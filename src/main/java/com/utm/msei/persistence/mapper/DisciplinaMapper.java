package com.utm.msei.persistence.mapper;

import com.utm.msei.persistence.dto.DisciplinaDto;
import com.utm.msei.persistence.entity.DisciplinaEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@org.mapstruct.Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface DisciplinaMapper extends Mapper<DisciplinaDto, DisciplinaEntity> {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    DisciplinaEntity partialUpdate(DisciplinaDto disciplinaDto, @MappingTarget DisciplinaEntity disciplinaEntity);
}
