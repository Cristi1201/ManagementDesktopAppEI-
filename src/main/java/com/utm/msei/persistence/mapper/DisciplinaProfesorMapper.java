package com.utm.msei.persistence.mapper;

import com.utm.msei.persistence.dto.DisciplinaProfesorDto;
import com.utm.msei.persistence.entity.DisciplinaProfesorEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@org.mapstruct.Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface DisciplinaProfesorMapper extends Mapper<DisciplinaProfesorDto, DisciplinaProfesorEntity> {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    DisciplinaProfesorEntity partialUpdate(DisciplinaProfesorDto disciplinaProfesorDto, @MappingTarget DisciplinaProfesorEntity disciplinaProfesorEntity);
}
