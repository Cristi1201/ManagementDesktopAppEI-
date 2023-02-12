package com.utm.msei.persistence.mapper;

import com.utm.msei.persistence.dto.AbsenteDto;
import com.utm.msei.persistence.entity.AbsenteEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@org.mapstruct.Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface AbsenteMapper extends Mapper<AbsenteDto, AbsenteEntity> {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    AbsenteEntity partialUpdate(AbsenteDto absenteDto, @MappingTarget AbsenteEntity absenteEntity);
}
