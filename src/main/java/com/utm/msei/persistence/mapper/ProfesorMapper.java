package com.utm.msei.persistence.mapper;

import com.utm.msei.persistence.dto.ProfesorDto;
import com.utm.msei.persistence.entity.ProfesorEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@org.mapstruct.Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ProfesorMapper extends Mapper<ProfesorDto, ProfesorEntity> {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ProfesorEntity partialUpdate(ProfesorDto profesorDto, @MappingTarget ProfesorEntity profesorEntity);
}
